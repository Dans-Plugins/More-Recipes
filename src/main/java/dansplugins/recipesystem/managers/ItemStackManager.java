package dansplugins.recipesystem.managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import dansplugins.recipesystem.objects.*;
import dansplugins.recipesystem.MoreRecipes;

import java.util.ArrayList;
import java.util.List;

public class ItemStackManager {

    MoreRecipes moreRecipes = null;

    public ItemStackManager(MoreRecipes plugin) {
        moreRecipes = plugin;
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

        if (itemName.equalsIgnoreCase("Saddle")) {
            Saddle saddle = new Saddle(moreRecipes);
            return saddle.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("NameTag")) {
            NameTag nametag = new NameTag(moreRecipes);
            return nametag.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("TotemOfUndying")) {
            TotemOfUndying totemOfUndying = new TotemOfUndying(moreRecipes);
            return totemOfUndying.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GrassBlock")) {
            GrassBlock grassBlock = new GrassBlock(moreRecipes);
            return grassBlock.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("String")) {
            StringItem string = new StringItem(moreRecipes);
            return string.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Lead")) {
            Lead lead = new Lead(moreRecipes);
            return lead.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("BlazeRod")) {
            BlazeRod blazeRod = new BlazeRod(moreRecipes);
            return blazeRod.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailHelmet")) {
            ChainmailHelmet chainmailHelmet = new ChainmailHelmet(moreRecipes);
            return chainmailHelmet.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailChestplate")) {
            ChainmailChestplate chainmailChestplate = new ChainmailChestplate(moreRecipes);
            return chainmailChestplate.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailLeggings")) {
            ChainmailLeggings chainmailLeggings = new ChainmailLeggings(moreRecipes);
            return chainmailLeggings.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailBoots")) {
            ChainmailBoots chainmailBoots = new ChainmailBoots(moreRecipes);
            return chainmailBoots.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("IronHorseArmor")) {
            IronHorseArmor ironHorseArmor = new IronHorseArmor(moreRecipes);
            return ironHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GoldenHorseArmor")) {
            GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor(moreRecipes);
            return goldenHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("DiamondHorseArmor")) {
            DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor(moreRecipes);
            return diamondHorseArmor.getItemStack(amount);
        }

        return null;
    }

}
