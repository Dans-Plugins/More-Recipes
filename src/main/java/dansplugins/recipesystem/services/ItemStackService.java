package dansplugins.recipesystem.services;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.objects.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackService {
    private final MoreRecipes moreRecipes;

    public ItemStackService(MoreRecipes moreRecipes) {
        this.moreRecipes = moreRecipes;
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
            Saddle saddle = new Saddle(this, moreRecipes);
            return saddle.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("NameTag")) {
            NameTag nametag = new NameTag(this, moreRecipes);
            return nametag.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("TotemOfUndying")) {
            TotemOfUndying totemOfUndying = new TotemOfUndying(this, moreRecipes);
            return totemOfUndying.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GrassBlock")) {
            GrassBlock grassBlock = new GrassBlock(this, moreRecipes);
            return grassBlock.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("String")) {
            StringItem string = new StringItem(this, moreRecipes);
            return string.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Lead")) {
            Lead lead = new Lead(this, moreRecipes);
            return lead.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("BlazeRod")) {
            BlazeRod blazeRod = new BlazeRod(this, moreRecipes);
            return blazeRod.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailHelmet")) {
            ChainmailHelmet chainmailHelmet = new ChainmailHelmet(this, moreRecipes);
            return chainmailHelmet.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailChestplate")) {
            ChainmailChestplate chainmailChestplate = new ChainmailChestplate(this, moreRecipes);
            return chainmailChestplate.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailLeggings")) {
            ChainmailLeggings chainmailLeggings = new ChainmailLeggings(this, moreRecipes);
            return chainmailLeggings.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailBoots")) {
            ChainmailBoots chainmailBoots = new ChainmailBoots(this, moreRecipes);
            return chainmailBoots.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("IronHorseArmor")) {
            IronHorseArmor ironHorseArmor = new IronHorseArmor(this, moreRecipes);
            return ironHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GoldenHorseArmor")) {
            GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor(this, moreRecipes);
            return goldenHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("DiamondHorseArmor")) {
            DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor(this, moreRecipes);
            return diamondHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Gunpowder")) {
            Gunpowder gunpowder = new Gunpowder(this, moreRecipes);
            return gunpowder.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Cobweb")) {
            Cobweb cobweb = new Cobweb(this, moreRecipes);
            return cobweb.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("SlimeBall")) {
            SlimeBall slimeBall = new SlimeBall(this, moreRecipes);
            return slimeBall.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("PrismarineShard")) {
            PrismarineShard prismarineShard = new PrismarineShard(this, moreRecipes);
            return prismarineShard.getItemStack(amount);
        }

        return null;
    }

}
