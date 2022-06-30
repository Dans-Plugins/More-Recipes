package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.GUNPOWDER;

public class Gunpowder {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public Gunpowder(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, GUNPOWDER, "Gunpowder", "Explosive.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_gunpowder");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("SGS", "GSG", "SGS");
        recipe.setIngredient('S', Material.SAND);
        recipe.setIngredient('G', Material.GRAVEL);
        Bukkit.addRecipe(recipe);
    }

}
