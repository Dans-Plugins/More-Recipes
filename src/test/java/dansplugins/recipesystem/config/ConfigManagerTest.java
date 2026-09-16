package dansplugins.recipesystem.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConfigManagerTest {
    private JavaPlugin plugin;
    private FileConfiguration config;
    private ConfigManager configManager;

    @BeforeEach
    void setUp() {
        plugin = mock(JavaPlugin.class);
        config = mock(FileConfiguration.class);
        when(plugin.getConfig()).thenReturn(config);
        configManager = new ConfigManager(plugin);
    }

    @Test
    void saveDefaultConfig_delegatesToPlugin() {
        configManager.saveDefaultConfig();

        verify(plugin).saveDefaultConfig();
    }

    @Test
    void usageReporting_readsThroughToTheBundledDefaultsWhenTheFileHasNoBlock() {
        // A server upgraded from before usage reporting has no usage-reporting
        // block in its config.yml. Bukkit's one-argument getters fall through
        // to the jar's defaults; the two-argument ones would return their
        // fallback and turn reporting off on every existing installation.
        when(config.getBoolean("usage-reporting.enabled")).thenReturn(true);
        when(config.getString("usage-reporting.endpoint")).thenReturn("https://trace.danielstephenson.dev");
        when(config.getString("usage-reporting.key")).thenReturn("bundled-key");

        assertTrue(configManager.isUsageReportingEnabled());
        assertEquals("https://trace.danielstephenson.dev", configManager.getUsageReportingEndpoint());
        assertEquals("bundled-key", configManager.getUsageReportingKey());
        verify(config, never()).getString(eq("usage-reporting.key"), anyString());
        verify(config, never()).getString(eq("usage-reporting.endpoint"), anyString());
        verify(config, never()).getBoolean(eq("usage-reporting.enabled"), anyBoolean());
    }

    @Test
    void usageReporting_isOffWithNoKeyAnywhere() {
        when(config.getString("usage-reporting.key")).thenReturn(null);
        when(config.getString("usage-reporting.endpoint")).thenReturn(null);

        assertEquals("", configManager.getUsageReportingKey(), "no key anywhere must read as off, not as null");
        assertEquals("https://trace.danielstephenson.dev", configManager.getUsageReportingEndpoint());
    }

    @Test
    void usageReporting_readsTheConfiguredValues() {
        when(config.getBoolean("usage-reporting.enabled")).thenReturn(false);
        when(config.getString("usage-reporting.endpoint")).thenReturn("http://localhost:8080");
        when(config.getString("usage-reporting.key")).thenReturn("abc");

        assertFalse(configManager.isUsageReportingEnabled());
        assertEquals("http://localhost:8080", configManager.getUsageReportingEndpoint());
        assertEquals("abc", configManager.getUsageReportingKey());
    }

    // The block-on-disk half of the upgrade story, against a real YamlConfiguration so
    // isSet() and getDefaults() behave as they do on a server, not as a mock says.

    @Test
    void writeUsageReportingBlockIfMissing_copiesTheBundledValuesAndSavesWhenTheFileHasNoBlock() {
        YamlConfiguration onDisk = YamlConfiguration.loadConfiguration(new StringReader("debugMode: false\n"));
        onDisk.setDefaults(YamlConfiguration.loadConfiguration(new StringReader(
                "usage-reporting:\n  enabled: true\n  endpoint: https://trace.danielstephenson.dev\n  key: bundled-key\n")));
        when(plugin.getConfig()).thenReturn(onDisk);

        configManager.writeUsageReportingBlockIfMissing();

        verify(plugin).saveConfig();
        YamlConfiguration reloaded = YamlConfiguration.loadConfiguration(new StringReader(onDisk.saveToString()));
        assertTrue(reloaded.isSet("usage-reporting.enabled"), "the block must now be in the file itself, not only in the defaults");
        assertTrue(reloaded.getBoolean("usage-reporting.enabled"));
        assertEquals("https://trace.danielstephenson.dev", reloaded.getString("usage-reporting.endpoint"));
        assertEquals("bundled-key", reloaded.getString("usage-reporting.key"));
        assertFalse(reloaded.getBoolean("debugMode"), "what was already there is kept");
    }

    @Test
    void writeUsageReportingBlockIfMissing_leavesAFileThatAlreadyHasTheBlockAlone() {
        YamlConfiguration onDisk = YamlConfiguration.loadConfiguration(new StringReader("usage-reporting:\n  enabled: false\n"));
        onDisk.setDefaults(YamlConfiguration.loadConfiguration(new StringReader(
                "usage-reporting:\n  enabled: true\n  endpoint: https://trace.danielstephenson.dev\n  key: bundled-key\n")));
        when(plugin.getConfig()).thenReturn(onDisk);

        configManager.writeUsageReportingBlockIfMissing();

        verify(plugin, never()).saveConfig();
        assertFalse(onDisk.getBoolean("usage-reporting.enabled"), "an operator's opt-out is not overwritten");
        assertFalse(onDisk.isSet("usage-reporting.key"), "nothing is added to a file that has the block");
    }

    @Test
    void writeUsageReportingBlockIfMissing_doesNothingWithoutBundledDefaults() {
        YamlConfiguration onDisk = YamlConfiguration.loadConfiguration(new StringReader("debugMode: false\n"));
        when(plugin.getConfig()).thenReturn(onDisk);

        configManager.writeUsageReportingBlockIfMissing();

        verify(plugin, never()).saveConfig();
        assertFalse(onDisk.isSet("usage-reporting"));
    }
}
