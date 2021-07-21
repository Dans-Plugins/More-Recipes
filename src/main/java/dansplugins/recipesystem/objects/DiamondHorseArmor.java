package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.DIAMOND_HORSE_ARMOR;

public class DiamondHorseArmor {

    MoreRecipes moreRecipes = null;

    public DiamondHorseArmor(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, DIAMOND_HORSE_ARMOR, "Diamond Horse Armor", "Horse Armor crafted out of Diamonds");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_diamond_horse_armor");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("III", "III", "S0S");
        recipe.setIngredient('I', Material.DIAMOND);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }
}
