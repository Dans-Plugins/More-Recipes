package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.objects.Cobweb;
import dansplugins.recipesystem.objects.Gunpowder;
import dansplugins.recipesystem.objects.SlimeBall;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dansplugins.recipesystem.MoreRecipes;

public class ListItemsCommand {

    public void execute(CommandSender sender) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("morerecipes.listitems") || player.hasPermission("morerecipes.default")) {

                // title
                player.sendMessage(ChatColor.AQUA + " == More Recipes - Items == ");

                // items
                player.sendMessage(ChatColor.AQUA + "BlazeRod");
                player.sendMessage(ChatColor.AQUA + "ChainmailBoots");
                player.sendMessage(ChatColor.AQUA + "ChainmailChestplate");
                player.sendMessage(ChatColor.AQUA + "ChainmailHelmet");
                player.sendMessage(ChatColor.AQUA + "ChainmailLeggings");
                player.sendMessage(ChatColor.AQUA + "Cobweb");
                player.sendMessage(ChatColor.AQUA + "DiamondHorseArmor");
                player.sendMessage(ChatColor.AQUA + "GoldenHorseArmor");
                player.sendMessage(ChatColor.AQUA + "GrassBlock");
                player.sendMessage(ChatColor.AQUA + "Gunpowder");
                player.sendMessage(ChatColor.AQUA + "IronHorseArmor");
                player.sendMessage(ChatColor.AQUA + "Lead");
                player.sendMessage(ChatColor.AQUA + "NameTag");
                player.sendMessage(ChatColor.AQUA + "Saddle");
                player.sendMessage(ChatColor.AQUA + "SlimeBall");
                player.sendMessage(ChatColor.AQUA + "String");
                player.sendMessage(ChatColor.AQUA + "TotemOfUndying");

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'morerecipes.listitems'");
            }

        }

    }

}
