package dansplugins.recipesystem.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.CHAINMAIL_BOOTS;

public class ChainmailBoots {

    MoreRecipes moreRecipes = null;

    public ChainmailBoots(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public ItemStack getItemStack(int amount) {
        return moreRecipes.itemstacks.createItemStack(amount, CHAINMAIL_BOOTS, "Chainmail Boots", "A pair of boots made of interlocking metal links.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_chainmail_boots");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("000", "I0I", "I0I");
        recipe.setIngredient('I', Material.IRON_BARS);
        Bukkit.addRecipe(recipe);
    }
}
