package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.CHAINMAIL_HELMET;

public class ChainmailHelmet {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public ChainmailHelmet(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, CHAINMAIL_HELMET, "Chainmail Helmet", "A helmet of interlocking links.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_chainmail_helmet");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("III", "I0I", "000");
        recipe.setIngredient('I', Material.IRON_BARS);
        Bukkit.addRecipe(recipe);
    }

}
