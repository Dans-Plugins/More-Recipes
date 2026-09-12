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

        recipeRegistry.registerRecipes();
        handlebStatsIntegration();
        initializeCommandService();

        // usage reporting: one event now, one per command; see config.yml
        trace = TraceClient.builder(configManager.getUsageReportingEndpoint(), getName())
                .key(configManager.getUsageReportingKey())
                .enabled(configManager.isUsageReportingEnabled())
                .logger(getLogger())
                .build();
        trace.report("startup", null, Collections.singletonMap("version", getDescription().getVersion()));
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
        trace.report("command", null, Collections.singletonMap("name", cmd.getName()));
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
        ArrayList<AbstractPluginCommand> commands = new ArrayList<AbstractPluginCommand>(Arrays.asList(
                new HelpCommand(),
                new GetCommand(itemStackService),
                new ListCommand()
        ));
        commandService.initialize(commands, "That command wasn't found.");
    }
}