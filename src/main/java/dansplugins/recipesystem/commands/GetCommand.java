package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.services.LocalItemStackService;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class GetCommand extends AbstractPluginCommand {

    public GetCommand() {
        super(new ArrayList<>(Arrays.asList("get")), new ArrayList<>(Arrays.asList("morerecipes.get")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /morerecipes get (itemName) (amount)");
        return false;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can't be used in the console.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Usage: /morerecipes get (itemName) (amount)");
        }

        String itemToGet = args[1];
        int amount = Integer.parseInt(args[2]);

        ItemStack item = LocalItemStackService.getInstance().getItemStack(itemToGet, amount);

        if (item == null) {
            player.sendMessage(ChatColor.RED + "That isn't an item in More Recipes!");
        }

        // add to player's inventory
        // if player's inventory has space
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "Inventory full.");
            return false;
        }

        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN + "" + itemToGet + " created.");
        return true;
    }
}