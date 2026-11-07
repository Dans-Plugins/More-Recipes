package dansplugins.recipesystem;

import com.sun.net.httpserver.HttpServer;
import dansplugins.recipesystem.trace.TraceClient;
import dansplugins.recipesystem.trace.TraceClientTestEnvironment;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@code CONFIG.md}, the README and the bundled {@code config.yml} tell server owners that a
 * {@code command} event is sent only when a player uses a command. A real client reports to a
 * loopback server here, since {@link TraceClient} is final and cannot be mocked. Closing the client
 * waits for everything it queued, so an empty server means nothing was sent, not nothing yet.
 */
class MoreRecipesCommandReportingTest {
    private final List<String> received = new CopyOnWriteArrayList<>();
    private HttpServer server;
    private Function<String, String> realEnvironment;

    @BeforeEach
    void startServer() throws IOException {
        realEnvironment = TraceClientTestEnvironment.clear();
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            received.add(new String(readAll(exchange.getRequestBody()), StandardCharsets.UTF_8));
            exchange.sendResponseHeaders(201, -1);
            exchange.close();
        });
        server.start();
    }

    @AfterEach
    void stopServer() {
        server.stop(0);
        TraceClientTestEnvironment.restore(realEnvironment);
    }

    @Test
    void reportCommandUse_sendsTheCommandNameAndNothingElseForAPlayer() {
        assertEquals(
                Collections.singletonList("{\"application\":\"More-Recipes\",\"name\":\"command\","
                        + "\"tags\":{\"name\":\"morerecipes\",\"version\":\"1.2.3\"}}"),
                eventsSentFor(mock(Player.class)));
    }

    @Test
    void reportCommandUse_sendsNothingForTheConsole() {
        assertEquals(Collections.emptyList(), eventsSentFor(mock(ConsoleCommandSender.class)));
    }

    @Test
    void reportCommandUse_sendsNothingForACommandBlock() {
        assertEquals(Collections.emptyList(), eventsSentFor(mock(BlockCommandSender.class)));
    }

    /**
     * @param sender The sender to report a use of {@code /morerecipes} for.
     * @return The body of each event that reached the server.
     */
    private List<String> eventsSentFor(CommandSender sender) {
        TraceClient client = TraceClient.builder(
                "http://127.0.0.1:" + server.getAddress().getPort(), "More-Recipes", "1.2.3").key("k").build();
        assertTrue(client.isEnabled(), "a disabled client would send nothing for any sender");
        Command command = mock(Command.class);
        when(command.getName()).thenReturn("morerecipes");

        MoreRecipes.reportCommandUse(client, sender, command);

        client.close();
        return received;
    }

    private static byte[] readAll(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        return out.toByteArray();
    }
}
