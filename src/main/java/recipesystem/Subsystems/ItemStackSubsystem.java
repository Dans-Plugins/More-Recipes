package recipesystem.Subsystems;

import org.bukkit.inventory.ItemStack;
import recipesystem.ItemStacks.Salt;
import recipesystem.Main;

public class ItemStackSubsystem {

    Main main = null;

    public ItemStackSubsystem(Main plugin) {
        main = plugin;
    }

    public ItemStack getItemStack(String itemName, int amount) {

        if (itemName.equalsIgnoreCase("Salt")) {
            return Salt.getItemStack(amount);
        }
        return null;
    }

}
