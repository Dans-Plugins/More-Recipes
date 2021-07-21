package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.GRASS_BLOCK;

public class GrassBlock {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, GRASS_BLOCK, "Grass Block", "Dirt with life on top.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_grass_block");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("000", "0G0", "0D0");
        recipe.setIngredient('G', Material.GRASS);
        recipe.setIngredient('D', Material.DIRT);
        Bukkit.addRecipe(recipe);
    }

}
