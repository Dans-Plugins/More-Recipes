package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.NAME_TAG;

public class NameTag {

    MoreRecipes moreRecipes = null;

    public NameTag(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, NAME_TAG, "Name Tag", "Used to give names to creatures.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_name_tag");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("PP0", "PP0", "00S");
        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
