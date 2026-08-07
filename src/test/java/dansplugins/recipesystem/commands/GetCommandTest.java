package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
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
        when(inventory.addItem(itemStack)).thenReturn(new HashMap<>());

        boolean result = getCommand.execute(player, new String[]{"Saddle", "5"});

        assertTrue(result);
        verify(inventory).addItem(itemStack);
    }

    @Test
    void execute_withZeroAmount_returnsFalseWithoutLookingUpItem() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);

        boolean result = getCommand.execute(player, new String[]{"Saddle", "0"});

        assertFalse(result);
        verify(itemStackService, never()).getItemStack(anyString(), anyInt());
        verify(player).sendMessage(contains("Amount must be at least 1."));
    }

    @Test
    void execute_withNegativeAmount_returnsFalseWithoutLookingUpItem() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);

        boolean result = getCommand.execute(player, new String[]{"Saddle", "-5"});

        assertFalse(result);
        verify(itemStackService, never()).getItemStack(anyString(), anyInt());
        verify(player).sendMessage(contains("Amount must be at least 1."));
    }

    @Test
    void execute_whenSomeItemsDoNotFit_reportsTheAmountThatWasNotAdded() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        ItemStack itemStack = mock(ItemStack.class);
        when(itemStackService.getItemStack("Saddle", 100)).thenReturn(itemStack);

        ItemStack leftoverStack = mock(ItemStack.class);
        when(leftoverStack.getAmount()).thenReturn(37);
        HashMap<Integer, ItemStack> leftover = new HashMap<>();
        leftover.put(0, leftoverStack);

        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);
        PlayerInventory inventory = mock(PlayerInventory.class);
        when(player.getInventory()).thenReturn(inventory);
        when(inventory.addItem(itemStack)).thenReturn(leftover);

        boolean result = getCommand.execute(player, new String[]{"Saddle", "100"});

        assertTrue(result);
        verify(player).sendMessage(contains("63 Saddle created. 37 didn't fit in your inventory."));
    }

    @Test
    void execute_whenNothingFits_reportsAFullInventory() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        ItemStack itemStack = mock(ItemStack.class);
        when(itemStackService.getItemStack("Saddle", 3)).thenReturn(itemStack);

        ItemStack leftoverStack = mock(ItemStack.class);
        when(leftoverStack.getAmount()).thenReturn(3);
        HashMap<Integer, ItemStack> leftover = new HashMap<>();
        leftover.put(0, leftoverStack);

        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);
        PlayerInventory inventory = mock(PlayerInventory.class);
        when(player.getInventory()).thenReturn(inventory);
        when(inventory.addItem(itemStack)).thenReturn(leftover);

        boolean result = getCommand.execute(player, new String[]{"Saddle", "3"});

        assertFalse(result);
        verify(player).sendMessage(contains("Inventory full."));
    }

    @Test
    void execute_whenNoSlotIsEmptyButAPartialStackHasRoom_stillAddsTheItem() {
        ItemStackService itemStackService = mock(ItemStackService.class);
        ItemStack itemStack = mock(ItemStack.class);
        when(itemStackService.getItemStack("Saddle", 5)).thenReturn(itemStack);

        GetCommand getCommand = new GetCommand(itemStackService);
        Player player = mock(Player.class);
        PlayerInventory inventory = mock(PlayerInventory.class);
        when(player.getInventory()).thenReturn(inventory);
        // no empty slot, but addItem still merges into an existing partial stack
        when(inventory.firstEmpty()).thenReturn(-1);
        when(inventory.addItem(itemStack)).thenReturn(new HashMap<>());

        boolean result = getCommand.execute(player, new String[]{"Saddle", "5"});

        assertTrue(result);
        verify(inventory).addItem(itemStack);
        verify(player).sendMessage(contains("Saddle created."));
    }
}
