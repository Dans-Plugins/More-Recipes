package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.SADDLE;

public class Saddle {

    MoreRecipes moreRecipes = null;

    public Saddle(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, SADDLE, "Saddle", "Used to ride certain animals.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_saddle");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("LLL", "L0L", "I0I");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

}
