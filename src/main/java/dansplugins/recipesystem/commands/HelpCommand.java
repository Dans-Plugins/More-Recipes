package dansplugins.recipesystem.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class HelpCommand extends AbstractPluginCommand {

    public HelpCommand() {
        super(new ArrayList<>(Arrays.asList("help")), new ArrayList<>(Arrays.asList("morerecipes.help")));
    }

    @Override
    public boolean execute(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can't be used in the console.");
            return false;
        }
        sender.sendMessage(ChatColor.AQUA + "=== More Recipes Commands ===");
        sender.sendMessage(ChatColor.AQUA + "/mr help - View a list of helpful commands.");
        sender.sendMessage(ChatColor.AQUA + "/mr list - List the items that can be crafted.");
        sender.sendMessage(ChatColor.AQUA + "/mr get (name) (amount) - Get an certain amount of a specified item.");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}