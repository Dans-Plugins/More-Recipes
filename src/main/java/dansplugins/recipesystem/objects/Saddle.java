package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.SADDLE;

public class Saddle {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public Saddle(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, SADDLE, "Saddle", "Used to ride certain animals.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_saddle");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("LLL", "L0L", "I0I");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

}
