package recipesystem.ItemStacks.Uncraftable;

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

import static org.bukkit.Material.SADDLE;
import static org.bukkit.Material.STRING;

public class StringItem {

    Main main = null;

    public StringItem(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return main.itemstacks.createItemStack(amount, STRING, "String", "Used to craft bows, fishing poles and wool.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_string");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("GG0", "0G0", "0GG");
        recipe.setIngredient('G', Material.GRASS);
        Bukkit.addRecipe(recipe);
    }

}
