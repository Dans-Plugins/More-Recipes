package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.GOLDEN_HORSE_ARMOR;

public class GoldenHorseArmor {

    MoreRecipes moreRecipes = null;

    public GoldenHorseArmor(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, GOLDEN_HORSE_ARMOR, "Golden Horse Armor", "Horse Armor crafted out of Pure Gold");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_golden_horse_armor");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("III", "III", "S0S");
        recipe.setIngredient('I', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }
}
