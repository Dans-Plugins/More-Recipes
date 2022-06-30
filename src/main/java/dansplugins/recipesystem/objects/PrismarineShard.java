package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.PRISMARINE_SHARD;

public class PrismarineShard {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public PrismarineShard(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public ItemStack getItemStack(int amount) {
        return itemStackService.createItemStack(amount, PRISMARINE_SHARD, "Prismarine Shard", "Used for a an assortment of contruction materials.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(moreRecipes, "more_recipes_prismarine_shard");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(8));
        recipe.shape("D0D", "EBE", "EBE");
        recipe.setIngredient('D', Material.KELP);
        recipe.setIngredient('B', Material.IRON_NUGGET);
        recipe.setIngredient('E', Material.BRICK);
        Bukkit.addRecipe(recipe);
    }

}
