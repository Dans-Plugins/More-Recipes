package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.CHAINMAIL_LEGGINGS;

public class ChainmailLeggings {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public ChainmailLeggings(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, CHAINMAIL_LEGGINGS, "Chainmail Leggings", "A set of leggings of interlocking metal links.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_chainmail_leggings");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("III", "I0I", "I0I");
        recipe.setIngredient('I', Material.IRON_BARS);
        Bukkit.addRecipe(recipe);
    }
}
