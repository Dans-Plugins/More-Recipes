package dansplugins.recipesystem.managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import dansplugins.recipesystem.objects.*;

import java.util.ArrayList;
import java.util.List;

public class ItemStackManager {
    
    private static ItemStackManager instance;
    
    private ItemStackManager() {
        
    }
    
    public static ItemStackManager getInstance() {
        if (instance == null) {
            instance = new ItemStackManager();
        }
        return instance;
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
            Saddle saddle = new Saddle();
            return saddle.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("NameTag")) {
            NameTag nametag = new NameTag();
            return nametag.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("TotemOfUndying")) {
            TotemOfUndying totemOfUndying = new TotemOfUndying();
            return totemOfUndying.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GrassBlock")) {
            GrassBlock grassBlock = new GrassBlock();
            return grassBlock.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("String")) {
            StringItem string = new StringItem();
            return string.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Lead")) {
            Lead lead = new Lead();
            return lead.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("BlazeRod")) {
            BlazeRod blazeRod = new BlazeRod();
            return blazeRod.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailHelmet")) {
            ChainmailHelmet chainmailHelmet = new ChainmailHelmet();
            return chainmailHelmet.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailChestplate")) {
            ChainmailChestplate chainmailChestplate = new ChainmailChestplate();
            return chainmailChestplate.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailLeggings")) {
            ChainmailLeggings chainmailLeggings = new ChainmailLeggings();
            return chainmailLeggings.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("ChainmailBoots")) {
            ChainmailBoots chainmailBoots = new ChainmailBoots();
            return chainmailBoots.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("IronHorseArmor")) {
            IronHorseArmor ironHorseArmor = new IronHorseArmor();
            return ironHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("GoldenHorseArmor")) {
            GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor();
            return goldenHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("DiamondHorseArmor")) {
            DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor();
            return diamondHorseArmor.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Gunpowder")) {
            Gunpowder gunpowder = new Gunpowder();
            return gunpowder.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("Cobweb")) {
            Cobweb cobweb = new Cobweb();
            return cobweb.getItemStack(amount);
        }

        return null;
    }

}
