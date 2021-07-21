package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.managers.ItemStackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import dansplugins.recipesystem.MoreRecipes;

import static org.bukkit.Material.CHAINMAIL_CHESTPLATE;

public class ChainmailChestplate {

    public ItemStack getItemStack(int amount) {
        return ItemStackManager.getInstance().createItemStack(amount, CHAINMAIL_CHESTPLATE, "Chainmail Chestplate", "A chestplate of interlocking metal links.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_chainmail_chestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("I0I", "III", "III");
        recipe.setIngredient('I', Material.IRON_BARS);
        Bukkit.addRecipe(recipe);
    }

}

