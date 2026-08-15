package dansplugins.recipesystem.services;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.objects.MoreRecipesItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackService {
    private final MoreRecipes moreRecipes;

    public ItemStackService(MoreRecipes moreRecipes) {
        this.moreRecipes = moreRecipes;
    }

    public ItemStack createItemStack(int amount, Material type, String name, String description) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + name);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + description);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Builds a stack of the named catalog item.
     * @param itemName The name of the item, matched against the catalog ignoring case.
     * @param amount The number of items the stack should hold.
     * @return The item stack, or null if no catalog item goes by that name.
     */
    public ItemStack getItemStack(String itemName, int amount) {
        MoreRecipesItem item = MoreRecipesItem.findByName(itemName);

        if (item == null) {
            return null;
        }

        return item.getItemStack(this, moreRecipes, amount);
    }

}
