package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.BLAZE_ROD;

public class BlazeRod {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, BLAZE_ROD, "Blaze Rod", "A rod of blazing fire.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_blaze_rod");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("ILI", "ILI", "ILI");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('L', Material.LAVA_BUCKET);
        Bukkit.addRecipe(recipe);
    }

}
