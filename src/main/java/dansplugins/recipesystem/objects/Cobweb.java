package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.COBWEB;

public class Cobweb {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public Cobweb(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, COBWEB, "Cobweb", "Sticky.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_cobweb");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("S0S", "0S0", "S0S");
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
