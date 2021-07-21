package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.GRASS_BLOCK;

public class GrassBlock {

    MoreRecipes moreRecipes = null;

    public GrassBlock(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, GRASS_BLOCK, "Grass Block", "Dirt with life on top.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_grass_block");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("000", "0G0", "0D0");
        recipe.setIngredient('G', Material.GRASS);
        recipe.setIngredient('D', Material.DIRT);
        Bukkit.addRecipe(recipe);
    }

}
