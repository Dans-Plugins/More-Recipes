package recipesystem.ItemStacks.Custom.ConquestTextures;

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

public class SteelIngot {

    Main main = null;

    public SteelIngot(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Steel Ingot");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "A durable refined alloy");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_steel_ingot");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(2));
        recipe.shape("CCC", "III", "CCC");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.COAL);
        Bukkit.addRecipe(recipe);
    }

}
