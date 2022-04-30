package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.LocalItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.PRISMARINE_SHARD;

public class PrismarineShard {

    public ItemStack getItemStack(int amount) {
        return LocalItemStackService.getInstance().createItemStack(amount, PRISMARINE_SHARD, "Prismarine Shard", "Used for a an assortment of contruction materials.");
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(MoreRecipes.getInstance(), "more_recipes_prismarine_shard");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(8));
        recipe.shape("D0D", "EBE", "EBE");
        recipe.setIngredient('D', Material.KELP);
        recipe.setIngredient('B', Material.IRON_NUGGET);
        recipe.setIngredient('E', Material.BRICK);
        Bukkit.addRecipe(recipe);
    }

}
