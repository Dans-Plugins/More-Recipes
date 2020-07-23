package recipesystem.ItemStacks.Custom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import recipesystem.Main;

import java.util.ArrayList;
import java.util.List;

public class Salt {

    Main main = null;

    public Salt(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        ItemStack item = new ItemStack(Material.SUGAR, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Salt");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "Great for seasoning.");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_salt");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("DDD", "D0D", "DDD");
        recipe.setIngredient('D', Material.DIORITE);
        Bukkit.addRecipe(recipe);
    }

}
