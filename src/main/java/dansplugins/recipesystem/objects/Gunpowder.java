package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.BLAZE_ROD;
import static org.bukkit.Material.GUNPOWDER;

public class Gunpowder {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, GUNPOWDER, "Gunpowder", "Explosive.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_gunpowder");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("SGS", "GSG", "SGS");
        recipe.setIngredient('S', Material.SAND);
        recipe.setIngredient('G', Material.GRAVEL);
        Bukkit.addRecipe(recipe);
    }

}
