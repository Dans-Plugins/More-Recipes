package dansplugins.recipesystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import dansplugins.recipesystem.managers.ItemStackManager;

public final class MoreRecipes extends JavaPlugin {

    // version
    public static String version = "v1.4";

    // subsystems
    public ItemStackManager itemstacks = new ItemStackManager(this);
    public RecipeRegistry recipes = new RecipeRegistry(this);

    @Override
    public void onEnable() {
        recipes.registerRecipes();
    }

    @Override
    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandInterpreter commandInterpreter = new CommandInterpreter(this);
        return commandInterpreter.interpretCommand(sender, label, args);
    }
}
