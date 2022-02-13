package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.services.LocalItemStackService;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCommand {

    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("morerecipes.get") || player.hasPermission("morerecipes.admin")) {

                if (args.length > 2) {

                    String itemToGet = args[1];
                    int amount = Integer.parseInt(args[2]);

                    ItemStack item = LocalItemStackService.getInstance().getItemStack(itemToGet, amount);

                    if (item != null) {
                        // add to player's inventory
                        // if player's inventory has space
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(item);
                            player.sendMessage(ChatColor.GREEN + "" + itemToGet + " created.");
                        }
                        else { // player's inventory is full
                            player.sendMessage(ChatColor.RED + "Inventory full.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "That isn't an item in More Recipes!");
                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /morerecipes get (itemName) (amount)");
                }

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'morerecipes.get'");
            }

        }

    }

}
