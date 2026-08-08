package dansplugins.recipesystem.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ListCommandTest {

    @Test
    void execute_sendsAHeaderFollowedByEveryItem() {
        ListCommand listCommand = new ListCommand();
        CommandSender sender = mock(CommandSender.class);

        boolean result = listCommand.execute(sender);

        assertTrue(result);
        verify(sender).sendMessage(contains("Items provided by More Recipes"));
        assertEquals(18, itemNamesSentTo(sender).size());
    }

    @Test
    void execute_listsTheItemsInAlphabeticalOrder() {
        ListCommand listCommand = new ListCommand();
        CommandSender sender = mock(CommandSender.class);

        listCommand.execute(sender);

        List<String> itemNames = itemNamesSentTo(sender);
        List<String> sorted = new ArrayList<>(itemNames);
        sorted.sort(String::compareTo);
        assertEquals(sorted, itemNames);
    }

    @Test
    void execute_withArguments_sendsTheSameList() {
        ListCommand listCommand = new ListCommand();
        CommandSender sender = mock(CommandSender.class);

        boolean result = listCommand.execute(sender, new String[]{"list"});

        assertTrue(result);
        assertTrue(itemNamesSentTo(sender).contains("Saddle"));
    }

    /**
     * Collects the item names that a list command sent to the given sender, with colour codes removed
     * and the header line discarded.
     * @param sender The mock sender the list command was executed against.
     * @return The item names, in the order they were sent.
     */
    private static List<String> itemNamesSentTo(CommandSender sender) {
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
}
