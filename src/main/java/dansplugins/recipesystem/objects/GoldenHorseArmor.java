package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.GOLDEN_HORSE_ARMOR;

public class GoldenHorseArmor {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public GoldenHorseArmor(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, GOLDEN_HORSE_ARMOR, "Golden Horse Armor", "Horse Armor crafted out of Pure Gold");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_golden_horse_armor");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("III", "III", "S0S");
        recipe.setIngredient('I', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }
}
