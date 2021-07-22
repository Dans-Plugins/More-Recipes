package dansplugins.recipesystem;

import dansplugins.recipesystem.commands.GetCommand;
import dansplugins.recipesystem.commands.HelpCommand;
import dansplugins.recipesystem.commands.ListItemsCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandInterpreter {

    public boolean interpretCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("morerecipes") || label.equalsIgnoreCase("mr")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.AQUA + " == More Recipes " + MoreRecipes.getInstance().version + " == ");
                sender.sendMessage(ChatColor.AQUA + "Author: DanTheTechMan");
                return true;
            }

            if (args[0].equalsIgnoreCase("help")) {
                HelpCommand command = new HelpCommand();
                command.execute(sender);
                return true;
            }

            if (args[0].equalsIgnoreCase("listitems")) {
                ListItemsCommand command = new ListItemsCommand();
                command.execute(sender);
                return true;
            }

            if (args[0].equalsIgnoreCase("get")) {
                GetCommand command = new GetCommand();
                command.execute(sender, args);
                return true;
            }

        }

        sender.sendMessage(ChatColor.RED + "More Recipes doesn't recognize that command.");
        return false;
    }

}
