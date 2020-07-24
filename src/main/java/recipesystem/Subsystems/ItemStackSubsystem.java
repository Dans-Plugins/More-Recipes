package recipesystem.Subsystems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import recipesystem.ItemStacks.Custom.SteelIngot;
import recipesystem.ItemStacks.Uncraftable.*;
import recipesystem.ItemStacks.Custom.Salt;
import recipesystem.Main;

import java.util.ArrayList;
import java.util.List;

public class ItemStackSubsystem {

    Main main = null;

    public ItemStackSubsystem(Main plugin) {
        main = plugin;
    }

    public ItemStack createItemStack(int amount, Material type, String name, String description) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + name);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + description);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getItemStack(String itemName, int amount) {

        if (itemName.equalsIgnoreCase("Salt")) {
            Salt salt = new Salt(main);
            return salt.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("SteelIngot")) {
            SteelIngot steelIngot = new SteelIngot(main);
            return steelIngot.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Saddle")) {
            Saddle saddle = new Saddle(main);
            return saddle.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("NameTag")) {
            NameTag nametag = new NameTag(main);
            return nametag.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("TotemOfUndying")) {
            TotemOfUndying totemOfUndying = new TotemOfUndying(main);
            return totemOfUndying.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GrassBlock")) {
            GrassBlock grassBlock = new GrassBlock(main);
            return grassBlock.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("String")) {
            StringItem string = new StringItem(main);
            return string.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Lead")) {
            Lead lead = new Lead(main);
            return lead.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("BlazeRod")) {
            BlazeRod blazeRod = new BlazeRod(main);
            return blazeRod.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailHelmet")) {
            ChainmailHelmet chainmailHelmet = new ChainmailHelmet(main);
            return chainmailHelmet.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailChestplate")) {
            ChainmailChestplate chainmailChestplate = new ChainmailChestplate(main);
            return chainmailChestplate.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailLeggings")) {
            ChainmailLeggings chainmailLeggings = new ChainmailLeggings(main);
            return chainmailLeggings.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailBoots")) {
            ChainmailBoots chainmailBoots = new ChainmailBoots(main);
            return chainmailBoots.getItemStack(amount);
        }

        return null;
    }

}
