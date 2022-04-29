package dansplugins.recipesystem.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class ListItemsCommand extends AbstractPluginCommand {

    public ListItemsCommand() {
        super(new ArrayList<>(Arrays.asList("list")), new ArrayList<>(Arrays.asList("morerecipes.list")));
    }

    public boolean execute(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + " == Items provided by More Recipes == ");
        sender.sendMessage(ChatColor.AQUA + "BlazeRod");
        sender.sendMessage(ChatColor.AQUA + "ChainmailBoots");
        sender.sendMessage(ChatColor.AQUA + "ChainmailChestplate");
        sender.sendMessage(ChatColor.AQUA + "ChainmailHelmet");
        sender.sendMessage(ChatColor.AQUA + "ChainmailLeggings");
        sender.sendMessage(ChatColor.AQUA + "Cobweb");
        sender.sendMessage(ChatColor.AQUA + "DiamondHorseArmor");
        sender.sendMessage(ChatColor.AQUA + "GoldenHorseArmor");
        sender.sendMessage(ChatColor.AQUA + "GrassBlock");
        sender.sendMessage(ChatColor.AQUA + "Gunpowder");
        sender.sendMessage(ChatColor.AQUA + "IronHorseArmor");
        sender.sendMessage(ChatColor.AQUA + "Lead");
        sender.sendMessage(ChatColor.AQUA + "NameTag");
        sender.sendMessage(ChatColor.AQUA + "PrismarineShard");
        sender.sendMessage(ChatColor.AQUA + "Saddle");
        sender.sendMessage(ChatColor.AQUA + "SlimeBall");
        sender.sendMessage(ChatColor.AQUA + "String");
        sender.sendMessage(ChatColor.AQUA + "TotemOfUndying");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }

}
