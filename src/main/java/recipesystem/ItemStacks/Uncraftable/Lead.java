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

import static org.bukkit.Material.GRASS_BLOCK;
import static org.bukkit.Material.LEAD;

public class Lead {

    Main main = null;

    public Lead(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return main.itemstacks.createItemStack(amount, LEAD, "Lead", "Used to lead animals or keep them in one place.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_lead");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("SS0", "SI0", "00S");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

}
