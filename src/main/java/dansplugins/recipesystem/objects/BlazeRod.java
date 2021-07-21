package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.BLAZE_ROD;

public class BlazeRod {

    MoreRecipes moreRecipes = null;

    public BlazeRod(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, BLAZE_ROD, "Blaze Rod", "A rod of blazing fire.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_blaze_rod");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("ILI", "ILI", "ILI");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('L', Material.LAVA_BUCKET);
        Bukkit.addRecipe(recipe);
    }

}
