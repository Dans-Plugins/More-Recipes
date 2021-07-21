package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.COBWEB;

public class Cobweb {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, COBWEB, "Cobweb", "Sticky.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_cobweb");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("S0S", "0S0", "S0S");
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
