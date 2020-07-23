package recipesystem.Subsystems;

import org.bukkit.inventory.ItemStack;
import recipesystem.ItemStacks.Vanilla.Nametag;
import recipesystem.ItemStacks.Vanilla.Saddle;
import recipesystem.ItemStacks.Custom.Salt;
import recipesystem.ItemStacks.Vanilla.TotemOfUndying;
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

        if (itemName.equalsIgnoreCase("Nametag")) {
            Nametag nametag = new Nametag(main);
            return nametag.getItemStack(amount);
        }

        if (itemName.equalsIgnoreCase("TotemOfUndying")) {
            TotemOfUndying totemOfUndying = new TotemOfUndying(main);
            return totemOfUndying.getItemStack(amount);
        }

        return null;
    }

}
