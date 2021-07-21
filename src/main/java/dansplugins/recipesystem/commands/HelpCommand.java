package dansplugins.recipesystem.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand {

    public void execute(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("morerecipes.help")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return;
            }
        }
        sender.sendMessage(ChatColor.AQUA + "/mr help - View a list of helpful commands.");
        sender.sendMessage(ChatColor.AQUA + "/mr listitems - List the items that can be crafted.");
        sender.sendMessage(ChatColor.AQUA + "/mr get (name) (amount) - Get an certain amount of a specified item.");
    }

}
