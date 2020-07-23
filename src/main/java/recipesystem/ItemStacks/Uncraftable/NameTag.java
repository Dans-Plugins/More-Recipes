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

import static org.bukkit.Material.LEAD;
import static org.bukkit.Material.NAME_TAG;

public class NameTag {

    Main main = null;

    public NameTag(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return main.itemstacks.createItemStack(amount, NAME_TAG, "Name Tag", "Used to give names to creatures.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_name_tag");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("PP0", "PP0", "00S");
        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
