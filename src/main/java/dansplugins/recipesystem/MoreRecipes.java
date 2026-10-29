package dansplugins.recipesystem;

import dansplugins.recipesystem.bstats.Metrics;
import dansplugins.recipesystem.commands.DefaultCommand;
import dansplugins.recipesystem.commands.GetCommand;
import dansplugins.recipesystem.commands.HelpCommand;
import dansplugins.recipesystem.commands.ListCommand;
import dansplugins.recipesystem.config.ConfigManager;
import dansplugins.recipesystem.services.ItemStackService;
import dansplugins.recipesystem.trace.TraceClient;
import dansplugins.recipesystem.utils.RecipeRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;
import preponderous.ponder.minecraft.bukkit.abs.PonderBukkitPlugin;
import preponderous.ponder.minecraft.bukkit.services.CommandService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class MoreRecipes extends PonderBukkitPlugin {
    private final String pluginVersion = "v" + getDescription().getVersion();

    private final CommandService commandService = new CommandService(getPonder());
    private final ItemStackService itemStackService = new ItemStackService(this);
    private final RecipeRegistry recipeRegistry = new RecipeRegistry(itemStackService, this);
    private final ConfigManager configManager = new ConfigManager(this);

    // A no-op until the config has been read, so a command arriving before
    // onEnable() finishes has something safe to report to.
    private TraceClient trace = TraceClient.disabled();

    @Override
    public void onEnable() {
        // writes the bundled config.yml on first run; never overwrites an existing one
        configManager.saveDefaultConfig();
        configManager.writeUsageReportingBlockIfMissing();

        recipeRegistry.registerRecipes();
        handlebStatsIntegration();
        initializeCommandService();

        // usage reporting: one event now, one per command; see config.yml
        String usageReportingEndpoint = configManager.getUsageReportingEndpoint();
        trace = TraceClient.builder(usageReportingEndpoint, getName(), getDescription().getVersion())
                .key(configManager.getUsageReportingKey())
                .enabled(configManager.isUsageReportingEnabled())
                .serverWideConfig(getDataFolder().getParentFile())
                .logger(getLogger())
                .build();
        announceUsageReporting(usageReportingEndpoint);
        trace.report("startup");
    }

    /**
     * The startup line that says, on every enable, whether usage reporting is on and how to turn it
     * off, or why it is off. The reason comes from the client so it matches what it actually did.
     * @param endpoint The endpoint the client was built with, so the notice names where events go.
     */
    private void announceUsageReporting(String endpoint) {
        if (trace.isEnabled()) {
            getLogger().info(usageReportingOnNotice(getName(), endpoint));
        } else {
            getLogger().info("Usage reporting is off (" + trace.disabledReason() + ").");
        }
    }

    /**
     * The startup line logged when usage reporting is on.
     * @param pluginName The name the plugin reports under.
     * @param endpoint The endpoint events are sent to, from {@code usage-reporting.endpoint}.
     * @return The notice, naming the endpoint given rather than the default one.
     */
    static String usageReportingOnNotice(String pluginName, String endpoint) {
        return "Usage reporting is on: " + pluginName + " sends its name, version and command names to " + endpoint + ", plus a random server ID (server-id in plugins/trace/config.yml) - nothing about players. Turn it off with usage-reporting.enabled: false in this plugin's config.yml, or for every plugin with enabled: false in plugins/trace/config.yml. Details: https://danielstephenson.dev/usage-reporting";
    }

    @Override
    public void onDisable() {
        trace.close();
    }

    /**
     * This method handles commands sent to the minecraft server and interprets them if the label matches one of the core commands.
     * @param sender The sender of the command.
     * @param cmd The command that was sent. Its name is reported as a usage event.
     * @param label The core command that has been invoked.
     * @param args Arguments of the core command. Often sub-commands.
     * @return A boolean indicating whether the execution of the command was successful.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Console, command-block and plugin-dispatched commands are not counted.
        if (sender instanceof Player) {
            trace.report("command", null, Collections.singletonMap("name", cmd.getName()));
        }
        if (args.length == 0) {
            DefaultCommand defaultCommand = new DefaultCommand(this);
            return defaultCommand.execute(sender);
        }
        return commandService.interpretAndExecuteCommand(sender, label, args);
    }

    public String getVersion() {
        return pluginVersion;
    }

    private void handlebStatsIntegration() {
        int pluginId = 12140;
        new Metrics(this, pluginId);
    }

    /**
     * Initializes Ponder's command service with the plugin's commands.
     */
    private void initializeCommandService() {
        commandService.initialize(createCommands(itemStackService), "That command wasn't found.");
    }

    /**
     * The sub-commands registered with Ponder's command service. Bare {@code /mr} is handled
     * separately, by {@link DefaultCommand} in {@link #onCommand}.
     * @param itemStackService The service {@link GetCommand} builds items with.
     * @return A new instance of each registered command.
     */
    static ArrayList<AbstractPluginCommand> createCommands(ItemStackService itemStackService) {
        return new ArrayList<AbstractPluginCommand>(Arrays.asList(
                new HelpCommand(),
                new GetCommand(itemStackService),
                new ListCommand()
        ));
    }
}