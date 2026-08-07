package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class GetCommand extends AbstractPluginCommand {
    private final ItemStackService itemStackService;

    public GetCommand(ItemStackService itemStackService) {
        super(new ArrayList<>(Arrays.asList("get")), new ArrayList<>(Arrays.asList("morerecipes.get")));
        this.itemStackService = itemStackService;
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
            return false;
        }

        String itemToGet = args[0];
        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Usage: /morerecipes get (itemName) (amount)");
            return false;
        }

        if (amount < 1) {
            player.sendMessage(ChatColor.RED + "Amount must be at least 1.");
            return false;
        }

        ItemStack item = itemStackService.getItemStack(itemToGet, amount);

        if (item == null) {
            player.sendMessage(ChatColor.RED + "That isn't an item in More Recipes!");
            return false;
        }

        // add to player's inventory, keeping whatever didn't fit
        Map<Integer, ItemStack> leftover = player.getInventory().addItem(item);
        int amountNotAdded = countLeftoverItems(leftover);

        if (amountNotAdded >= amount) {
            player.sendMessage(ChatColor.RED + "Inventory full.");
            return false;
        }

        if (amountNotAdded > 0) {
            player.sendMessage(ChatColor.YELLOW + "" + (amount - amountNotAdded) + " " + itemToGet
                    + " created. " + amountNotAdded + " didn't fit in your inventory.");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "" + itemToGet + " created.");
        return true;
    }

    /**
     * Sums the amounts of the item stacks that an inventory could not accept.
     * @param leftover The leftover map returned by Inventory#addItem.
     * @return The total number of items that were not added.
     */
    private int countLeftoverItems(Map<Integer, ItemStack> leftover) {
        if (leftover == null) {
            return 0;
        }
        int total = 0;
        for (ItemStack remaining : leftover.values()) {
            total += remaining.getAmount();
        }
        return total;
    }
}