package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.CHAINMAIL_CHESTPLATE;

public class ChainmailChestplate {

    MoreRecipes moreRecipes = null;

    public ChainmailChestplate(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, CHAINMAIL_CHESTPLATE, "Chainmail Chestplate", "A chestplate of interlocking metal links.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_chainmail_chestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("I0I", "III", "III");
        recipe.setIngredient('I', Material.IRON_BARS);
        Bukkit.addRecipe(recipe);
    }

}

