package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.NAME_TAG;

public class NameTag {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, NAME_TAG, "Name Tag", "Used to give names to creatures.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_name_tag");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("PP0", "PP0", "00S");
        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
