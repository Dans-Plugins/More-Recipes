package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.NAME_TAG;

public class NameTag {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public NameTag(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, NAME_TAG, "Name Tag", "Used to give names to creatures.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_name_tag");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("PP0", "PP0", "00S");
        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

}
