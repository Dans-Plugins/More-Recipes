package recipesystem.ItemStacks.Uncraftable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import recipesystem.Main;

import java.util.ArrayList;
import java.util.List;

public class Lead {

    Main main = null;

    public Lead(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(int amount) {
        ItemStack item = new ItemStack(Material.LEAD, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Lead");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "Used to lead animals or keep them in one place.");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(main, "more_recipes_lead");
        ShapedRecipe recipe = new ShapedRecipe(key, getItemStack(1));
        recipe.shape("SS0", "SI0", "00S");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }

}
