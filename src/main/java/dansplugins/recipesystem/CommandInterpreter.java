package dansplugins.recipesystem;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import dansplugins.recipesystem.commands.GetCommand;
import dansplugins.recipesystem.commands.ListItemsCommand;

public class CommandInterpreter {

    MoreRecipes moreRecipes = null;

    public CommandInterpreter(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public boolean interpretCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("morerecipes") || label.equalsIgnoreCase("mr")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.AQUA + " == More Recipes " + moreRecipes.version + " == ");
                sender.sendMessage(ChatColor.AQUA + "Author: DanTheTechMan");
                return true;
            }

            if (args[0].equalsIgnoreCase("get")) {
                GetCommand command = new GetCommand(moreRecipes);
                command.getItem(sender, args);
                return true;
            }

            if (args[0].equalsIgnoreCase("listitems")) {
                ListItemsCommand command = new ListItemsCommand(moreRecipes);
                command.showListToPlayer(sender);
                return true;
            }

        }

        return false;
    }

}
