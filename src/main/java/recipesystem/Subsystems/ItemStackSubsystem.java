package recipesystem.Subsystems;

import org.bukkit.inventory.ItemStack;
import recipesystem.ItemStacks.Uncraftable.*;
import recipesystem.ItemStacks.Custom.Salt;
import recipesystem.Main;

public class ItemStackSubsystem {

    Main main = null;

    public ItemStackSubsystem(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(String itemName, int amount) {

        if (itemName.equalsIgnoreCase("Salt")) {
            Salt salt = new Salt(main);
            return salt.getItemStack(amount);
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

        return null;
    }

}
