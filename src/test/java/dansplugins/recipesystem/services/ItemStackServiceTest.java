package dansplugins.recipesystem.services;

import dansplugins.recipesystem.commands.ListCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ItemStackServiceTest {

    @Test
    void getItemStack_withUnknownName_returnsNull() {
        RecordingItemStackService itemStackService = new RecordingItemStackService();

        assertNull(itemStackService.getItemStack("NotAnItem", 1));
    }

    @Test
    void getItemStack_matchesItemNamesCaseInsensitively() {
        RecordingItemStackService itemStackService = new RecordingItemStackService();

        assertNotNull(itemStackService.getItemStack("saddle", 1));
        assertNotNull(itemStackService.getItemStack("SADDLE", 1));
        assertNotNull(itemStackService.getItemStack("SaDdLe", 1));
    }

    @Test
    void getItemStack_buildsTheItemWithTheRequestedAmountAndName() {
        RecordingItemStackService itemStackService = new RecordingItemStackService();

        itemStackService.getItemStack("Saddle", 7);

        assertEquals(7, itemStackService.lastAmount);
        assertEquals(Material.SADDLE, itemStackService.lastType);
        assertEquals("Saddle", itemStackService.lastName);
    }

    /**
     * Guards the invariant that the item list players are shown by {@code /mr list} and the item names
     * {@code /mr get} accepts stay in step; an item added to one and not the other fails silently.
     */
    @Test
    void getItemStack_resolvesEveryItemAdvertisedByListCommand() {
        RecordingItemStackService itemStackService = new RecordingItemStackService();

        for (String itemName : itemNamesAdvertisedByListCommand()) {
            assertNotNull(itemStackService.getItemStack(itemName, 1), itemName + " is listed by /mr list but /mr get does not recognise it");
        }
    }

    /**
     * Runs the list command against a mock sender and collects the item names it advertises.
     * @return The item names, with colour codes removed and the header line discarded.
     */
    private static List<String> itemNamesAdvertisedByListCommand() {
        CommandSender sender = mock(CommandSender.class);
        new ListCommand().execute(sender);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(sender, atLeastOnce()).sendMessage(captor.capture());

        List<String> itemNames = new ArrayList<>();
        for (String message : captor.getAllValues()) {
            String stripped = ChatColor.stripColor(message).trim();
            if (stripped.startsWith("==")) {
                continue;
            }
            itemNames.add(stripped);
        }
        return itemNames;
    }

    /**
     * An item stack service that records what it was asked to build and returns a stub, so that item
     * lookups can be exercised without a running server to supply an item factory.
     */
    private static class RecordingItemStackService extends ItemStackService {
        private int lastAmount;
        private Material lastType;
        private String lastName;

        RecordingItemStackService() {
            super(null);
        }

        @Override
        public ItemStack createItemStack(int amount, Material type, String name, String description) {
            lastAmount = amount;
            lastType = type;
            lastName = name;
            return mock(ItemStack.class);
        }
    }
}
