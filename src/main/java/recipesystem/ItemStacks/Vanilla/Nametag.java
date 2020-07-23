package recipesystem.ItemStacks.Vanilla;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import recipesystem.Main;

import java.util.ArrayList;
import java.util.List;

public class Nametag {

    Main main = null;

    public Nametag(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        ItemStack item = new ItemStack(Material.NAME_TAG, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Nametag");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "Used to give names to creatures.");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_nametag");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("PP0", "PP0", "00S");
        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
