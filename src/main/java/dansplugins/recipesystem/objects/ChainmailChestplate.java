package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.CHAINMAIL_CHESTPLATE;

public class ChainmailChestplate {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public ChainmailChestplate(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, CHAINMAIL_CHESTPLATE, "Chainmail Chestplate", "A chestplate of interlocking metal links.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_chainmail_chestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("I0I", "III", "III");
        recipe.setIngredient('I', Material.IRON_BARS);
        Bukkit.addRecipe(recipe);
    }

}

