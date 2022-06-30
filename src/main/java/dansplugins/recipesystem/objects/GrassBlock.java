package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.GRASS_BLOCK;

public class GrassBlock {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public GrassBlock(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, GRASS_BLOCK, "Grass Block", "Dirt with life on top.");
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
