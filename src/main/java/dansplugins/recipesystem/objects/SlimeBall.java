package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.SLIME_BALL;

public class SlimeBall {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public SlimeBall(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, SLIME_BALL, "Slime Ball", "Slimy.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_slime_ball");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("KKK", "KWK", "KKK");
        recipe.setIngredient('K', Material.KELP);
        recipe.setIngredient('W', Material.WATER_BUCKET);
        Bukkit.addRecipe(recipe);
    }

}
