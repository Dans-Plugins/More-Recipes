package dansplugins.recipesystem;

import dansplugins.recipesystem.config.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Guards the bundled {@code config.yml} against the code that reads it and the page that documents it.
 *
 * {@code config.yml} is read by key at runtime, so a key renamed there and not in {@link ConfigManager}
 * reads as absent, which turns usage reporting off on every new installation with no build-time signal,
 * while the table in {@code CONFIG.md} goes on describing the old key or default.
 */
class BundledConfigTest {
    private static final String ENABLED_KEY = "usage-reporting.enabled";
    private static final String ENDPOINT_KEY = "usage-reporting.endpoint";

    @Test
    void configReference_listsExactlyTheBundledKeys() throws IOException {
        assertEquals(bundledKeys(), new TreeSet<>(documentedDefaults().keySet()));
    }

    /**
     * {@code usage-reporting.key} is documented in words rather than by value, so it is not compared.
     */
    @Test
    void configReference_givesTheBundledDefaults() throws IOException {
        Map<String, String> documented = documentedDefaults();
        YamlConfiguration bundled = bundledConfig();

        assertEquals(String.valueOf(bundled.get(ENABLED_KEY)), documented.get(ENABLED_KEY));
        assertEquals(bundled.getString(ENDPOINT_KEY), documented.get(ENDPOINT_KEY));
    }

    @Test
    void configManager_readsTheBundledValues() {
        ConfigManager configManager = new ConfigManager(pluginWith(bundledConfig()));

        assertTrue(configManager.isUsageReportingEnabled());
        assertEquals(bundledConfig().getString(ENDPOINT_KEY), configManager.getUsageReportingEndpoint());
        assertFalse(configManager.getUsageReportingKey().isEmpty(), "reporting would be off out of the box");
    }

    @Test
    void configManager_fallsBackToTheBundledEndpoint() {
        ConfigManager configManager = new ConfigManager(pluginWith(new YamlConfiguration()));

        assertEquals(bundledConfig().getString(ENDPOINT_KEY), configManager.getUsageReportingEndpoint());
    }

    /**
     * The block is written key by key, so a key added to {@code config.yml} would otherwise never reach
     * an upgraded server's file.
     */
    @Test
    void writeUsageReportingBlockIfMissing_writesEveryBundledKey() {
        YamlConfiguration onDisk = new YamlConfiguration();
        onDisk.setDefaults(bundledConfig());

        new ConfigManager(pluginWith(onDisk)).writeUsageReportingBlockIfMissing();

        assertEquals(bundledKeys(), leafKeys(onDisk));
    }

    private static JavaPlugin pluginWith(YamlConfiguration config) {
        JavaPlugin plugin = mock(JavaPlugin.class);
        when(plugin.getConfig()).thenReturn(config);
        return plugin;
    }

    private static YamlConfiguration bundledConfig() {
        File file = new File("src/main/resources/config.yml");
        assertTrue(file.isFile(), "config.yml was not found at " + file.getAbsolutePath());
        return YamlConfiguration.loadConfiguration(file);
    }

    private static TreeSet<String> bundledKeys() {
        return leafKeys(bundledConfig());
    }

    /**
     * @param config The configuration to read.
     * @return Every key holding a value, leaving out the sections that only contain others.
     */
    private static TreeSet<String> leafKeys(YamlConfiguration config) {
        TreeSet<String> keys = new TreeSet<>();
        for (String key : config.getKeys(true)) {
            if (!config.isConfigurationSection(key)) {
                keys.add(key);
            }
        }
        return keys;
    }

    /**
     * @return Each key in {@code CONFIG.md}'s table, mapped to its Default cell without backticks.
     * @throws IOException If the configuration reference cannot be read.
     */
    private static Map<String, String> documentedDefaults() throws IOException {
        Map<String, String> defaults = new LinkedHashMap<>();
        for (List<String> cells : PluginDescriptionTest.tableRowsUnder("CONFIG.md", "# More Recipes Configuration")) {
            defaults.put(PluginDescriptionTest.unquote(cells.get(0)), PluginDescriptionTest.unquote(cells.get(1)));
        }
        return defaults;
    }
}
