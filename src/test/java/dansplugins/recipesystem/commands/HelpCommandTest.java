package dansplugins.recipesystem.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class HelpCommandTest {

    @Test
    void execute_fromConsole_sendsTheCommandList() {
        HelpCommand helpCommand = new HelpCommand();
        ConsoleCommandSender console = mock(ConsoleCommandSender.class);

        boolean result = helpCommand.execute(console);

        assertTrue(result);
        verify(console).sendMessage(contains("=== More Recipes Commands ==="));
        verify(console).sendMessage(contains("/mr help"));
        verify(console).sendMessage(contains("/mr list"));
        verify(console).sendMessage(contains("/mr get (name) (amount) - Get a certain amount of a specified item."));
        verify(console, never()).sendMessage(contains("can't be used in the console"));
    }

    @Test
    void execute_withArguments_sendsTheCommandList() {
        HelpCommand helpCommand = new HelpCommand();
        CommandSender sender = mock(CommandSender.class);

        boolean result = helpCommand.execute(sender, new String[]{"help"});

        assertTrue(result);
        verify(sender).sendMessage(contains("=== More Recipes Commands ==="));
    }
}
