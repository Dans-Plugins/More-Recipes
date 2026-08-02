package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetCommandTest {

    @Test
    void execute_withNonNumericAmount_returnsFalseWithoutLookingUpItem() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);

        boolean result = getCommand.execute(player, new String[]{"Saddle", "notANumber"});

        assertFalse(result);
        verify(itemStackService, never()).getItemStack(anyString(), anyInt());
    }

    @Test
    void execute_withValidNumericAmount_addsItemToInventory() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        ItemStack itemStack = mock(ItemStack.class);
        when(itemStackService.getItemStack("Saddle", 5)).thenReturn(itemStack);

        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);
        PlayerInventory inventory = mock(PlayerInventory.class);
        when(player.getInventory()).thenReturn(inventory);
        when(inventory.firstEmpty()).thenReturn(0);

        boolean result = getCommand.execute(player, new String[]{"Saddle", "5"});

        assertTrue(result);
        verify(inventory).addItem(itemStack);
    }
}
