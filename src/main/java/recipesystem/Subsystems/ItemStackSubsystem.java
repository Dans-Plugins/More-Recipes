package recipesystem.Subsystems;

import org.bukkit.inventory.ItemStack;
import recipesystem.ItemStacks.Salt;

public class ItemStackSubsystem {

    public ItemStack getItemStack(String itemName, int amount) {

        if (itemName.equalsIgnoreCase("Salt")) {
            return Salt.getItemStack(amount);
        }
        return null;
    }

}
