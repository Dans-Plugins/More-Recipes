package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.objects.MoreRecipesItem;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class ListCommand extends AbstractPluginCommand {

    public ListCommand() {
        super(new ArrayList<>(Arrays.asList("list")), new ArrayList<>(Arrays.asList("morerecipes.listitems")));
    }

    public boolean execute(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + " == Items provided by More Recipes == ");
        for (String itemName : MoreRecipesItem.getItemNames()) {
            sender.sendMessage(ChatColor.AQUA + itemName);
        }
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }

}
