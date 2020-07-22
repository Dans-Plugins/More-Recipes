package recipesystem.ItemStacks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Salt {

    public static ItemStack getItemStack(int amount) {
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

}
