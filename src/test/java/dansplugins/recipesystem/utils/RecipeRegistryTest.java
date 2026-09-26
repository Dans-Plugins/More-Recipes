package dansplugins.recipesystem.utils;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.objects.MoreRecipesItem;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.objenesis.ObjenesisStd;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercises recipe registration against a stub server and inspects the recipe objects that reach
 * {@code Bukkit.addRecipe}.
 *
 * The failures these tests exist to catch leave no trace at build time. A shape character that was
 * never given an ingredient becomes an empty slot rather than an error; a key shared by two recipes
 * makes the server keep only one of them; a catalog entry wired to another item's class registers a
 * perfectly good recipe for the wrong item; a registrar that registers nothing leaves the item
 * uncraftable. None of these fail the build, and a player sees only a recipe that does not work.
 *
 * A shape that is not rectangular, or that has too many rows or columns, is the exception: Bukkit
 * rejects those itself, when the recipe is built. They are asserted below anyway, as a statement of
 * the shape Bukkit accepts, but they are not what these tests are for.
 */
class RecipeRegistryTest {

    /**
     * The character every recipe in this plugin uses for a slot that must be left empty. It is
     * deliberately never given an ingredient, so Bukkit leaves it mapped to null.
     */
    private static final char EMPTY_SLOT = '0';

    private static final String PLUGIN_NAME = "More-Recipes";

    private static Server server;

    private MoreRecipes moreRecipes;
    private ItemStackService itemStackService;

    /**
     * Installs a stub server so that {@code Bukkit.addRecipe} and the item factory the recipe
     * results are built through resolve without a running Spigot server. Bukkit permits its server
     * singleton to be set only once per JVM, and Surefire reuses one JVM across test classes.
     */
    @BeforeAll
    static void installStubServer() {
        if (Bukkit.getServer() == null) {
            Bukkit.setServer(stubServer());
        }
        server = Bukkit.getServer();
    }

    @BeforeEach
    void setUp() throws Exception {
        clearInvocations(server);
        moreRecipes = stubPlugin();
        itemStackService = new ItemStackService(moreRecipes);
    }

    @Test
    void registerRecipes_registersOneRecipePerCatalogItem() {
        new RecipeRegistry(itemStackService, moreRecipes).registerRecipes();

        assertEquals(MoreRecipesItem.values().length, registeredRecipes().size());
    }

    /**
     * The check neither the compiler nor Bukkit makes: every slot a shape asks for has something to
     * put in it.
     */
    @Test
    void registerRecipes_registersOnlyWellFormedShapedRecipes() {
        new RecipeRegistry(itemStackService, moreRecipes).registerRecipes();

        for (Recipe recipe : registeredRecipes()) {
            assertTrue(recipe instanceof ShapedRecipe,
                    describe(recipe) + " is not a shaped recipe, so this test does not check that it is well formed");
            assertWellFormed((ShapedRecipe) recipe);
        }
    }

    /**
     * A recipe key is what the server files the recipe under, so a copied-and-pasted key silently
     * discards one of the two recipes that share it.
     */
    @Test
    void registerRecipes_givesEveryRecipeAKeyOfItsOwnUnderThePluginsNamespace() {
        new RecipeRegistry(itemStackService, moreRecipes).registerRecipes();

        Set<NamespacedKey> keys = new HashSet<>();
        for (Recipe recipe : registeredRecipes()) {
            assertTrue(recipe instanceof Keyed, describe(recipe) + " was registered without a key");
            NamespacedKey key = ((Keyed) recipe).getKey();

            assertEquals(PLUGIN_NAME.toLowerCase(Locale.ROOT), key.getNamespace(), key + " is not namespaced to the plugin");
            assertTrue(keys.add(key), key + " is used by more than one recipe");
        }
    }

    /**
     * Catches a catalog entry wired to another item's class, which the item name alone cannot
     * reveal: the entry registers a perfectly valid recipe, just for the wrong item.
     */
    @Test
    void registerRecipe_registersOneRecipeYieldingTheEntrysOwnItem() {
        for (MoreRecipesItem item : MoreRecipesItem.values()) {
            clearInvocations(server);

            item.registerRecipe(itemStackService, moreRecipes);

            List<Recipe> recipes = registeredRecipes();
            assertEquals(1, recipes.size(), item.getItemName() + " did not register exactly one recipe");

            ItemStack result = recipes.get(0).getResult();
            assertNotNull(result, item.getItemName() + " registered a recipe with no result");
            assertEquals(item.getItemStack(itemStackService, moreRecipes, 1).getType(), result.getType(),
                    item.getItemName() + " registered a recipe yielding a different item");
            assertTrue(result.getAmount() > 0, item.getItemName() + " registered a recipe yielding nothing");
        }
    }

    /**
     * Guards the recipe table in {@code USER_GUIDE.md}, the only place a player can learn what a
     * recipe asks for. It cannot be generated from the registrars, so a changed ingredient, shape or
     * yield would otherwise leave the guide describing a recipe the server no longer accepts.
     */
    @Test
    void userGuide_documentsEveryRecipeAsRegistered() throws IOException {
        Map<String, List<String>> documented = recipesDocumentedInUserGuide();
        assertEquals(MoreRecipesItem.getItemNames(), new ArrayList<>(documented.keySet()));

        for (MoreRecipesItem item : MoreRecipesItem.values()) {
            clearInvocations(server);

            item.registerRecipe(itemStackService, moreRecipes);

            Recipe recipe = registeredRecipes().get(0);
            assertTrue(recipe instanceof ShapedRecipe, describe(recipe) + " is not a shaped recipe, which the guide's table assumes");
            assertEquals(asDocumented((ShapedRecipe) recipe), documented.get(item.getItemName()),
                    "USER_GUIDE.md does not describe the recipe " + item.getItemName() + " registers");
        }
    }

    /**
     * @param recipe The registered recipe.
     * @return The recipe as a row of the guide's table would put it: one cell per shape row, naming
     *         each slot's ingredient or {@code —} for an empty slot, followed by the amount it yields.
     */
    private static List<String> asDocumented(ShapedRecipe recipe) {
        Map<Character, ItemStack> ingredients = recipe.getIngredientMap();
        List<String> cells = new ArrayList<>();
        for (String row : recipe.getShape()) {
            List<String> slots = new ArrayList<>();
            for (char slot : row.toCharArray()) {
                ItemStack ingredient = ingredients.get(slot);
                slots.add(ingredient == null ? "—" : displayName(ingredient.getType()));
            }
            cells.add(String.join(", ", slots));
        }
        cells.add(String.valueOf(recipe.getResult().getAmount()));
        return cells;
    }

    /**
     * @param material An ingredient's material.
     * @return The material's name in the guide's wording: {@code IRON_BARS} becomes {@code Iron Bars}.
     */
    private static String displayName(Material material) {
        List<String> words = new ArrayList<>();
        for (String word : material.name().toLowerCase(Locale.ROOT).split("_")) {
            words.add(Character.toUpperCase(word.charAt(0)) + word.substring(1));
        }
        return String.join(" ", words);
    }

    /**
     * Reads the rows of the table under the user guide's "Recipes" heading.
     * @return Each documented item name, in the order it appears, mapped to the rest of its row.
     * @throws IOException If the user guide cannot be read.
     */
    private static Map<String, List<String>> recipesDocumentedInUserGuide() throws IOException {
        File userGuide = new File("USER_GUIDE.md");
        assertTrue(userGuide.isFile(), "USER_GUIDE.md was not found at " + userGuide.getAbsolutePath());

        Map<String, List<String>> recipes = new LinkedHashMap<>();
        boolean inRecipes = false;
        for (String line : Files.readAllLines(userGuide.toPath(), StandardCharsets.UTF_8)) {
            if (line.startsWith("## ")) {
                inRecipes = line.trim().equals("## Recipes");
                continue;
            }
            if (!inRecipes || !line.startsWith("| ")) {
                continue;
            }
            List<String> cells = new ArrayList<>();
            for (String cell : line.trim().substring(1).split("\\|")) {
                cells.add(cell.trim());
            }
            if (!cells.get(0).equals("Item")) {
                recipes.put(cells.get(0), cells.subList(1, cells.size()));
            }
        }
        return recipes;
    }

    /**
     * Asserts the properties of a well-formed shaped recipe. The one Bukkit does not check for
     * itself is the last: an unmapped shape character is silently an empty slot, not an error.
     * @param recipe The registered recipe.
     */
    private static void assertWellFormed(ShapedRecipe recipe) {
        String description = describe(recipe);
        String[] shape = recipe.getShape();

        assertTrue(shape.length >= 1 && shape.length <= 3, description + " has " + shape.length + " shape rows");

        int width = shape[0].length();
        assertTrue(width >= 1 && width <= 3, description + " has rows of " + width + " columns");
        for (String row : shape) {
            assertEquals(width, row.length(), description + " has rows of differing widths");
        }

        Map<Character, RecipeChoice> ingredients = recipe.getChoiceMap();
        int filledSlots = 0;
        for (String row : shape) {
            for (char slot : row.toCharArray()) {
                if (slot == EMPTY_SLOT) {
                    assertNull(ingredients.get(slot), description + " gives the empty slot an ingredient");
                    continue;
                }
                assertNotNull(ingredients.get(slot), description + " uses '" + slot + "' in its shape without an ingredient");
                filledSlots++;
            }
        }
        assertTrue(filledSlots > 0, description + " asks for no ingredients at all");
    }

    /**
     * @param recipe The registered recipe.
     * @return A description naming the recipe by its key, for assertion messages.
     */
    private static String describe(Recipe recipe) {
        if (recipe instanceof Keyed) {
            return "Recipe " + ((Keyed) recipe).getKey();
        }
        return "Recipe yielding " + recipe.getResult().getType();
    }

    /**
     * @return Every recipe handed to the server since the last {@code clearInvocations}.
     */
    private static List<Recipe> registeredRecipes() {
        ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
        verify(server, atLeastOnce()).addRecipe(captor.capture());
        return captor.getAllValues();
    }

    /**
     * Builds the plugin instance the registrars namespace their recipe keys against.
     *
     * {@link MoreRecipes} is final, so Mockito's default mock maker cannot subclass it, and its
     * inline mock maker cannot instrument the plugin's class hierarchy on every JDK this project is
     * built under. The instance is therefore allocated without running a constructor — the one
     * {@link JavaPlugin} declares refuses to run outside a server's plugin class loader — and given
     * the only thing recipe registration asks of it: a description to take its name from.
     * @return The plugin instance.
     * @throws Exception If the plugin's description cannot be installed.
     */
    private static MoreRecipes stubPlugin() throws Exception {
        MoreRecipes plugin = new ObjenesisStd().newInstance(MoreRecipes.class);

        Field description = descriptionField();
        description.setAccessible(true);
        description.set(plugin, new PluginDescriptionFile(PLUGIN_NAME, "test", MoreRecipes.class.getName()));

        return plugin;
    }

    /**
     * Located by type rather than by name so that the test states what it needs — somewhere to put a
     * plugin description — instead of depending on Bukkit's choice of field name.
     * @return The field {@link JavaPlugin} keeps its description in.
     */
    private static Field descriptionField() {
        for (Field field : JavaPlugin.class.getDeclaredFields()) {
            if (field.getType() == PluginDescriptionFile.class) {
                return field;
            }
        }
        throw new IllegalStateException("JavaPlugin no longer holds a " + PluginDescriptionFile.class.getSimpleName());
    }

    /**
     * @return A server that records the recipes registered against it and hands out throwaway item
     *         meta, which is all the recipe results need to be built.
     */
    @SuppressWarnings("deprecation")
    private static Server stubServer() {
        Server server = mock(Server.class);

        ItemFactory itemFactory = mock(ItemFactory.class);
        when(itemFactory.getItemMeta(any(Material.class))).thenAnswer(invocation -> mock(ItemMeta.class));
        when(server.getItemFactory()).thenReturn(itemFactory);

        // Copying an item stack into a recipe asks the server for the material's pre-1.13 form. Every
        // material this plugin uses is a modern one with no legacy equivalent, which is what the
        // stub's default answer of null says.
        when(server.getUnsafe()).thenReturn(mock(UnsafeValues.class));

        // Read back by Bukkit.setServer, which announces the server it was handed.
        when(server.getLogger()).thenReturn(Logger.getLogger(RecipeRegistryTest.class.getName()));
        when(server.getName()).thenReturn("a stub server");
        when(server.getVersion()).thenReturn("test");
        when(server.getBukkitVersion()).thenReturn("test");

        return server;
    }
}
