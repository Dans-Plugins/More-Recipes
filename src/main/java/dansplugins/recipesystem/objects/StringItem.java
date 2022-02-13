package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.LocalItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.STRING;

public class StringItem {

    public ItemStack getItemStack(int amount) {
        return LocalItemStackService.getInstance().createItemStack(amount, STRING, "String", "Used to craft bows, fishing poles and wool.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_string");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("GG0", "0G0", "0GG");
        recipe.setIngredient('G', Material.GRASS);
        Bukkit.addRecipe(recipe);
    }

}
