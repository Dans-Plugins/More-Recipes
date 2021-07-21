package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.LEAD;

public class Lead {

    MoreRecipes moreRecipes = null;

    public Lead(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, LEAD, "Lead", "Used to lead animals or keep them in one place.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_lead");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("SS0", "SI0", "00S");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

}
