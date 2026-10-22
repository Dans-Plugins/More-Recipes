package dansplugins.recipesystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The stub server tests install so that code reaching for {@code Bukkit} statically resolves without
 * a running Spigot server. Bukkit permits its server singleton to be set only once per JVM, and
 * Surefire reuses one JVM across test classes, so every test class that needs it shares this one.
 */
public final class StubServer {

    private StubServer() {
    }

    /**
     * Installs the stub server if no server has been installed yet.
     * @return The installed server.
     */
    public static Server install() {
        if (Bukkit.getServer() == null) {
            Bukkit.setServer(create());
        }
        return Bukkit.getServer();
    }

    /**
     * @return A server that records the recipes registered against it and hands out throwaway item
     *         meta, which is all the recipe results need to be built.
     */
    @SuppressWarnings("deprecation")
    private static Server create() {
        Server server = mock(Server.class);

        ItemFactory itemFactory = mock(ItemFactory.class);
        when(itemFactory.getItemMeta(any(Material.class))).thenAnswer(invocation -> mock(ItemMeta.class));
        when(server.getItemFactory()).thenReturn(itemFactory);

        // Copying an item stack into a recipe asks the server for the material's pre-1.13 form. Every
        // material this plugin uses is a modern one with no legacy equivalent, which is what the
        // stub's default answer of null says.
        when(server.getUnsafe()).thenReturn(mock(UnsafeValues.class));

        // Read back by Bukkit.setServer, which announces the server it was handed.
        when(server.getLogger()).thenReturn(Logger.getLogger(StubServer.class.getName()));
        when(server.getName()).thenReturn("a stub server");
        when(server.getVersion()).thenReturn("test");
        when(server.getBukkitVersion()).thenReturn("test");

        return server;
    }
}
