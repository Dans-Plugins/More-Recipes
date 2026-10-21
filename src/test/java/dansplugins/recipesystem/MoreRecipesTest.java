package dansplugins.recipesystem;

import dansplugins.recipesystem.commands.HelpCommand;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MoreRecipesTest {
    /** Not the default endpoint, so a notice still naming the default is caught. */
    private static final String CONFIGURED_ENDPOINT = "http://trace.example.test:8080";

    @Test
    void usageReportingOnNotice_namesTheConfiguredEndpoint() {
        String notice = MoreRecipes.usageReportingOnNotice("More-Recipes", CONFIGURED_ENDPOINT);

        assertTrue(notice.contains("command names to " + CONFIGURED_ENDPOINT + ","), notice);
        assertFalse(notice.contains("trace.danielstephenson.dev"), notice);
    }

    @Test
    void usageReportingOnNotice_namesThePluginAndHowToTurnItOff() {
        String notice = MoreRecipes.usageReportingOnNotice("More-Recipes", CONFIGURED_ENDPOINT);

        assertTrue(notice.startsWith("Usage reporting is on: More-Recipes sends"), notice);
        assertTrue(notice.contains("usage-reporting.enabled: false"), notice);
        assertTrue(notice.contains("enabled: false in plugins/trace/config.yml"), notice);
    }

    /**
     * A sub-command registered without a {@code /mr help} line or a {@code COMMANDS.md} row works but
     * cannot be discovered, and one removed while still listed is advertised but answers
     * {@code That command wasn't found.}; neither fails to compile.
     */
    @Test
    void registeredCommands_areExactlyTheOnesHelpLists() {
        assertEquals(registeredCommandNames(), commandNamesListedByHelp());
    }

    @Test
    void registeredCommands_areExactlyTheOnesTheCommandsReferenceLists() throws IOException {
        assertEquals(registeredCommandNames(), commandNamesListedInCommandsReference());
    }

    /**
     * @return Every name the command service answers to after {@code /mr}.
     */
    private static TreeSet<String> registeredCommandNames() {
        TreeSet<String> names = new TreeSet<>();
        for (AbstractPluginCommand command : MoreRecipes.createCommands(mock(ItemStackService.class))) {
            names.addAll(command.getNames());
        }
        assertFalse(names.isEmpty(), "no commands are registered");
        return names;
    }

    /**
     * @return The sub-command each {@code /mr <name> ...} line of {@code /mr help} describes.
     */
    private static TreeSet<String> commandNamesListedByHelp() {
        CommandSender sender = mock(CommandSender.class);
        new HelpCommand().execute(sender);

        ArgumentCaptor<String> messages = ArgumentCaptor.forClass(String.class);
        verify(sender, atLeastOnce()).sendMessage(messages.capture());
        TreeSet<String> names = new TreeSet<>();
        for (String message : messages.getAllValues()) {
            String line = ChatColor.stripColor(message);
            if (line.startsWith("/mr ")) {
                names.add(line.split(" ")[1]);
            }
        }
        return names;
    }

    /**
     * Bare {@code /mr} is handled outside the command service, so its row is not counted.
     * @return The sub-command named in each row of the command reference's table.
     * @throws IOException If the command reference cannot be read.
     */
    private static TreeSet<String> commandNamesListedInCommandsReference() throws IOException {
        TreeSet<String> names = new TreeSet<>();
        for (List<String> cells : PluginDescriptionTest.tableRowsUnder("COMMANDS.md", "# More Recipes Commands")) {
            String[] words = PluginDescriptionTest.unquote(cells.get(0)).split(" ");
            if (words.length > 1) {
                names.add(words[1]);
            }
        }
        return names;
    }
}
