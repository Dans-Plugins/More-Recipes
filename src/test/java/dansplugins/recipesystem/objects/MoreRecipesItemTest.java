package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class MoreRecipesItemTest {

    @Test
    void catalog_isNotEmpty() {
        assertFalse(MoreRecipesItem.getItemNames().isEmpty());
    }

    @Test
    void catalog_namesAreUnique() {
        Set<String> seen = new HashSet<>();

        for (String itemName : MoreRecipesItem.getItemNames()) {
            assertTrue(seen.add(itemName.toLowerCase(Locale.ROOT)), itemName + " appears in the catalog more than once");
        }
    }

    /**
     * The catalog's declaration order is the order {@code /mr list} presents items in, and that
     * listing is documented as alphabetical.
     */
    @Test
    void catalog_isDeclaredInAlphabeticalOrder() {
        List<String> itemNames = MoreRecipesItem.getItemNames();
        List<String> sorted = new ArrayList<>(itemNames);
        sorted.sort(String::compareTo);

        assertEquals(sorted, itemNames);
    }

    @Test
    void findByName_matchesIgnoringCase() {
        assertEquals(MoreRecipesItem.SADDLE, MoreRecipesItem.findByName("saddle"));
        assertEquals(MoreRecipesItem.SADDLE, MoreRecipesItem.findByName("SADDLE"));
        assertEquals(MoreRecipesItem.TOTEM_OF_UNDYING, MoreRecipesItem.findByName("totemofundying"));
    }

    @Test
    void findByName_withUnknownName_returnsNull() {
        assertNull(MoreRecipesItem.findByName("NotAnItem"));
    }

    @Test
    void findByName_findsEveryCatalogItemByItsOwnName() {
        for (String itemName : MoreRecipesItem.getItemNames()) {
            assertNotNull(MoreRecipesItem.findByName(itemName), itemName + " is in the catalog but cannot be found by name");
        }
    }

    /**
     * Exercises every entry's item stack factory, which is the only place a catalog entry can be
     * wired to the wrong item class. The display name each entry produces is compared against the
     * entry's own name so that a copied-and-pasted entry is caught rather than passing silently.
     */
    @Test
    void getItemStack_buildsTheEntrysOwnItemWithTheRequestedAmount() {
        RecordingItemStackService itemStackService = new RecordingItemStackService();

        for (MoreRecipesItem item : MoreRecipesItem.values()) {
            assertNotNull(item.getItemStack(itemStackService, null, 5), item.getItemName() + " built no item stack");

            assertEquals(5, itemStackService.lastAmount, item.getItemName() + " ignored the requested amount");
            assertTrue(item.getItemName().equalsIgnoreCase(itemStackService.lastName.replace(" ", "")),
                    item.getItemName() + " is wired to the item class of " + itemStackService.lastName);
        }
    }

    /**
     * Guards the copy of the item list carried by {@code USER_GUIDE.md}, which cannot be generated
     * from the catalog and so drifts silently when an item is added or removed.
     */
    @Test
    void userGuide_documentsExactlyTheCatalogItems() throws IOException {
        assertEquals(MoreRecipesItem.getItemNames(), itemNamesDocumentedInUserGuide());
    }

    /**
     * Reads the item names bulleted under the user guide's "Craftable Items" heading.
     * @return The documented item names, in the order they appear.
     * @throws IOException If the user guide cannot be read.
     */
    private static List<String> itemNamesDocumentedInUserGuide() throws IOException {
        File userGuide = new File("USER_GUIDE.md");
        assertTrue(userGuide.isFile(), "USER_GUIDE.md was not found at " + userGuide.getAbsolutePath());

        List<String> itemNames = new ArrayList<>();
        boolean inCraftableItems = false;
        for (String line : Files.readAllLines(userGuide.toPath(), StandardCharsets.UTF_8)) {
            if (line.startsWith("## ")) {
                inCraftableItems = line.trim().equals("## Craftable Items");
                continue;
            }
            if (inCraftableItems && line.startsWith("- ")) {
                itemNames.add(line.substring(2).trim());
            }
        }
        return itemNames;
    }

    /**
     * An item stack service that records what it was asked to build and returns a stub, so that the
     * catalog's factories can be exercised without a running server to supply an item factory.
     */
    private static class RecordingItemStackService extends ItemStackService {
        private int lastAmount;
        private String lastName;

        RecordingItemStackService() {
            super(null);
        }

        @Override
        public ItemStack createItemStack(int amount, Material type, String name, String description) {
            lastAmount = amount;
            lastName = name;
            return mock(ItemStack.class);
        }
    }
}
