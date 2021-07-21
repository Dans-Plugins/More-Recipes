package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.SLIME_BALL;

public class SlimeBall {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, SLIME_BALL, "Slime Ball", "Slimy.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_slime_ball");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("KKK", "KWK", "KKK");
        recipe.setIngredient('K', Material.KELP);
        recipe.setIngredient('W', Material.WATER_BUCKET);
        Bukkit.addRecipe(recipe);
    }

}
