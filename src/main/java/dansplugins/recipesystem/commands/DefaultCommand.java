package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.MoreRecipes;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class DefaultCommand extends AbstractPluginCommand {
    private final MoreRecipes moreRecipes;

    public DefaultCommand(MoreRecipes moreRecipes) {
        super(new ArrayList<>(Arrays.asList("default")), new ArrayList<>(Arrays.asList("morerecipes.default")));
        this.moreRecipes = moreRecipes;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.AQUA + "More Recipes " + moreRecipes.getVersion());
        commandSender.sendMessage(ChatColor.AQUA + "Developed by: Daniel McCoy Stephenson, Rykurock");
        commandSender.sendMessage(ChatColor.AQUA + "Wiki: https://github.com/Dans-Plugins/More-Recipes/wiki");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}