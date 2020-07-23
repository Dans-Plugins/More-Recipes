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

import static org.bukkit.Material.BLAZE_ROD;

public class BlazeRod {

    Main main = null;

    public BlazeRod(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return main.itemstacks.createItemStack(amount, BLAZE_ROD, "Blaze Rod", "A rod of blazing fire.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_blaze_rod");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("ILI", "ILI", "ILI");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('L', Material.LAVA_BUCKET);
        Bukkit.addRecipe(recipe);
    }

}
