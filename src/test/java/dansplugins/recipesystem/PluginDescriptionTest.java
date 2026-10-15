package dansplugins.recipesystem;

import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Guards the copies of {@code plugin.yml}'s permission declarations carried by the documentation.
 *
 * {@code plugin.yml} is what the server reads, so a node renamed or re-defaulted there takes effect
 * with no build-time signal, while the permissions table in {@code USER_GUIDE.md} and the permission
 * column in {@code COMMANDS.md} — what a server operator writes their permission configuration
 * from — go on describing the old node. The same holds for the node each registered command checks.
 */
class PluginDescriptionTest {

    @Test
    void userGuide_documentsExactlyThePermissionsDeclared() throws IOException {
        Map<String, String> declared = declaredPermissionDefaults();
        assertFalse(declared.isEmpty(), "plugin.yml declares no permissions");

        assertEquals(declared, documentedPermissionDefaults());
    }

    /**
     * Bare {@code /mr} is listed with no permission, which is the one row skipped here; see #187.
     */
    @Test
    void commandsReference_namesExactlyThePermissionsDeclared() throws IOException {
        assertEquals(new TreeSet<>(declaredPermissionDefaults().keySet()), permissionsNamedInCommandsReference());
    }

    /**
     * The permission each command checks is what the server enforces, so a node missing from
     * {@code plugin.yml} is one no operator can find documented; {@code /mr list} once checked the
     * undeclared {@code morerecipes.list}. {@link dansplugins.recipesystem.commands.DefaultCommand}'s
     * {@code morerecipes.default} is not covered, since it is not registered here; see #187.
     */
    @Test
    void registeredCommands_checkOnlyDeclaredPermissions() throws IOException {
        Set<String> declared = declaredPermissionDefaults().keySet();

        List<AbstractPluginCommand> commands = MoreRecipes.createCommands(mock(ItemStackService.class));
        assertFalse(commands.isEmpty(), "no commands are registered");
        for (AbstractPluginCommand command : commands) {
            for (String permission : command.getPermissions()) {
                assertTrue(declared.contains(permission),
                        command.getClass().getSimpleName() + " checks " + permission + ", which plugin.yml does not declare");
            }
        }
    }

    /**
     * @return Each permission node {@code plugin.yml} declares, in the order declared, mapped to its
     *         {@code default} value as written ({@code true}, {@code op}, ...).
     */
    private static Map<String, String> declaredPermissionDefaults() throws IOException {
        File pluginYml = new File("src/main/resources/plugin.yml");
        assertTrue(pluginYml.isFile(), "plugin.yml was not found at " + pluginYml.getAbsolutePath());

        // Permission nodes contain dots, which Bukkit would otherwise read as nested sections.
        YamlConfiguration yaml = new YamlConfiguration();
        yaml.options().pathSeparator('/');
        try {
            yaml.load(pluginYml);
        } catch (InvalidConfigurationException e) {
            throw new IOException("plugin.yml is not valid YAML", e);
        }

        ConfigurationSection permissions = yaml.getConfigurationSection("permissions");
        assertNotNull(permissions, "plugin.yml has no permissions section");

        Map<String, String> defaults = new LinkedHashMap<>();
        for (String node : permissions.getKeys(false)) {
            defaults.put(node, String.valueOf(permissions.get(node + "/default")));
        }
        return defaults;
    }

    /**
     * Reads the rows of the table under the user guide's "Permissions" heading.
     * @return Each documented permission node, in the order it appears, mapped to its documented default.
     * @throws IOException If the user guide cannot be read.
     */
    private static Map<String, String> documentedPermissionDefaults() throws IOException {
        Map<String, String> defaults = new LinkedHashMap<>();
        for (List<String> cells : tableRowsUnder("USER_GUIDE.md", "## Permissions")) {
            defaults.put(unquote(cells.get(0)), unquote(cells.get(1)));
        }
        return defaults;
    }

    /**
     * @return Every permission node in the command reference's Permission column, other than {@code None}.
     * @throws IOException If the command reference cannot be read.
     */
    private static TreeSet<String> permissionsNamedInCommandsReference() throws IOException {
        TreeSet<String> permissions = new TreeSet<>();
        for (List<String> cells : tableRowsUnder("COMMANDS.md", "# More Recipes Commands")) {
            String permission = cells.get(cells.size() - 1);
            if (!permission.equals("None")) {
                permissions.add(unquote(permission));
            }
        }
        return permissions;
    }

    /**
     * Reads the body rows of the table that follows a heading, up to the next heading.
     * @param path The Markdown file, relative to the project root.
     * @param heading The heading line the table sits under, exactly as written.
     * @return The cells of each row after the header and separator rows, trimmed.
     * @throws IOException If the file cannot be read.
     */
    private static List<List<String>> tableRowsUnder(String path, String heading) throws IOException {
        File file = new File(path);
        assertTrue(file.isFile(), path + " was not found at " + file.getAbsolutePath());

        List<List<String>> rows = new ArrayList<>();
        boolean inSection = false;
        boolean pastHeader = false;
        for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
            if (line.startsWith("#")) {
                inSection = line.trim().equals(heading);
                pastHeader = false;
                continue;
            }
            if (!inSection || !line.startsWith("|")) {
                continue;
            }
            if (line.startsWith("|--") || line.startsWith("| --")) {
                pastHeader = true;
                continue;
            }
            if (!pastHeader) {
                continue;
            }
            List<String> cells = new ArrayList<>();
            for (String cell : line.trim().substring(1).split("\\|")) {
                cells.add(cell.trim());
            }
            rows.add(cells);
        }
        assertFalse(rows.isEmpty(), path + " has no table under " + heading);
        return rows;
    }

    /**
     * @param cell A table cell.
     * @return The cell with surrounding backticks removed: {@code `op`} becomes {@code op}.
     */
    private static String unquote(String cell) {
        return cell.replaceAll("^`|`$", "");
    }
}
