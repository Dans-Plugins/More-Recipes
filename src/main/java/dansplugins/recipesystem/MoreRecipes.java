package dansplugins.recipesystem;

import dansplugins.recipesystem.bstats.Metrics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoreRecipes extends JavaPlugin {

    private static MoreRecipes instance;

    // version
    public final String version = "v1.5";

    public static MoreRecipes getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        RecipeRegistry.getInstance().registerRecipes();

        // bStats
        int pluginId = 12140;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandInterpreter commandInterpreter = new CommandInterpreter();
        return commandInterpreter.interpretCommand(sender, label, args);
    }
}
