package dansplugins.recipesystem.commands;

import dansplugins.recipesystem.MoreRecipes;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DefaultCommandTest {
    /** Distinct from anything a real build would produce, so the banner is known to have come from the plugin. */
    private static final String VERSION = "v9.9.9-test";

    private static MoreRecipes moreRecipes;

    @BeforeAll
    static void stubPlugin() throws Exception {
        moreRecipes = stubPluginWithVersion(VERSION);
    }

    @Test
    void execute_sendsTheVersionBanner() {
        DefaultCommand defaultCommand = new DefaultCommand(moreRecipes);
        CommandSender sender = mock(CommandSender.class);

        boolean result = defaultCommand.execute(sender);

        assertTrue(result);
        verify(sender).sendMessage(contains("More Recipes " + VERSION));
    }

    @Test
    void execute_sendsTheCreditsAndWikiLines() {
        DefaultCommand defaultCommand = new DefaultCommand(moreRecipes);
        CommandSender sender = mock(CommandSender.class);

        defaultCommand.execute(sender);

        verify(sender).sendMessage(contains("Developed by: Daniel McCoy Stephenson, Rykurock"));
        verify(sender).sendMessage(contains("Wiki: https://github.com/Dans-Plugins/More-Recipes/wiki"));
        verify(sender, times(3)).sendMessage(anyString());
    }

    @Test
    void execute_fromConsole_sendsTheBanner() {
        DefaultCommand defaultCommand = new DefaultCommand(moreRecipes);
        ConsoleCommandSender console = mock(ConsoleCommandSender.class);

        boolean result = defaultCommand.execute(console);

        assertTrue(result);
        verify(console).sendMessage(contains("More Recipes " + VERSION));
        verify(console, never()).sendMessage(contains("can't be used in the console"));
    }

    @Test
    void execute_withArguments_sendsTheSameBanner() {
        DefaultCommand defaultCommand = new DefaultCommand(moreRecipes);
        CommandSender sender = mock(CommandSender.class);

        boolean result = defaultCommand.execute(sender, new String[]{"unexpected", "arguments"});

        assertTrue(result);
        verify(sender).sendMessage(contains("More Recipes " + VERSION));
        verify(sender).sendMessage(contains("Developed by: Daniel McCoy Stephenson, Rykurock"));
        verify(sender).sendMessage(contains("Wiki: https://github.com/Dans-Plugins/More-Recipes/wiki"));
        verify(sender, times(3)).sendMessage(anyString());
    }

    /**
     * Builds the plugin instance the banner takes its version from.
     *
     * {@link MoreRecipes} is final, so Mockito's default mock maker cannot subclass it (see
     * {@code RecipeRegistryTest#stubPlugin}). The instance is allocated without running a
     * constructor — the one {@link org.bukkit.plugin.java.JavaPlugin} declares refuses to run
     * outside a server's plugin class loader — which also skips the field initializer that
     * derives the version from the plugin description, so the version is installed directly.
     * @param version The value {@link MoreRecipes#getVersion()} is to return.
     * @return The plugin instance.
     * @throws Exception If the version cannot be installed.
     */
    private static MoreRecipes stubPluginWithVersion(String version) throws Exception {
        MoreRecipes plugin = new ObjenesisStd().newInstance(MoreRecipes.class);

        Field pluginVersion = MoreRecipes.class.getDeclaredField("pluginVersion");
        pluginVersion.setAccessible(true);
        pluginVersion.set(plugin, version);

        return plugin;
    }
}
