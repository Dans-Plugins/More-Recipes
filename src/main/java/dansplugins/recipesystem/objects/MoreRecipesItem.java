package dansplugins.recipesystem.objects;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.services.ItemStackService;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * The single source of truth for the items that More Recipes provides.
 *
 * Recipe registration, {@code /mr get} lookups and the {@code /mr list} listing all iterate this
 * catalog, so an item added here reaches all three at once. Adding a class to the {@code objects}
 * package without adding it here leaves the item entirely absent rather than partially wired, which
 * is the failure this catalog exists to prevent.
 *
 * Constants are declared in the alphabetical order that {@code /mr list} presents them in.
 */
public enum MoreRecipesItem {
    BLAZE_ROD("BlazeRod",
            (itemStackService, moreRecipes, amount) -> new BlazeRod(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new BlazeRod(itemStackService, moreRecipes).registerRecipe()),
    CHAINMAIL_BOOTS("ChainmailBoots",
            (itemStackService, moreRecipes, amount) -> new ChainmailBoots(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new ChainmailBoots(itemStackService, moreRecipes).registerRecipe()),
    CHAINMAIL_CHESTPLATE("ChainmailChestplate",
            (itemStackService, moreRecipes, amount) -> new ChainmailChestplate(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new ChainmailChestplate(itemStackService, moreRecipes).registerRecipe()),
    CHAINMAIL_HELMET("ChainmailHelmet",
            (itemStackService, moreRecipes, amount) -> new ChainmailHelmet(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new ChainmailHelmet(itemStackService, moreRecipes).registerRecipe()),
    CHAINMAIL_LEGGINGS("ChainmailLeggings",
            (itemStackService, moreRecipes, amount) -> new ChainmailLeggings(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new ChainmailLeggings(itemStackService, moreRecipes).registerRecipe()),
    COBWEB("Cobweb",
            (itemStackService, moreRecipes, amount) -> new Cobweb(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new Cobweb(itemStackService, moreRecipes).registerRecipe()),
    DIAMOND_HORSE_ARMOR("DiamondHorseArmor",
            (itemStackService, moreRecipes, amount) -> new DiamondHorseArmor(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new DiamondHorseArmor(itemStackService, moreRecipes).registerRecipe()),
    GOLDEN_HORSE_ARMOR("GoldenHorseArmor",
            (itemStackService, moreRecipes, amount) -> new GoldenHorseArmor(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new GoldenHorseArmor(itemStackService, moreRecipes).registerRecipe()),
    GRASS_BLOCK("GrassBlock",
            (itemStackService, moreRecipes, amount) -> new GrassBlock(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new GrassBlock(itemStackService, moreRecipes).registerRecipe()),
    GUNPOWDER("Gunpowder",
            (itemStackService, moreRecipes, amount) -> new Gunpowder(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new Gunpowder(itemStackService, moreRecipes).registerRecipe()),
    IRON_HORSE_ARMOR("IronHorseArmor",
            (itemStackService, moreRecipes, amount) -> new IronHorseArmor(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new IronHorseArmor(itemStackService, moreRecipes).registerRecipe()),
    LEAD("Lead",
            (itemStackService, moreRecipes, amount) -> new Lead(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new Lead(itemStackService, moreRecipes).registerRecipe()),
    NAME_TAG("NameTag",
            (itemStackService, moreRecipes, amount) -> new NameTag(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new NameTag(itemStackService, moreRecipes).registerRecipe()),
    PRISMARINE_SHARD("PrismarineShard",
            (itemStackService, moreRecipes, amount) -> new PrismarineShard(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new PrismarineShard(itemStackService, moreRecipes).registerRecipe()),
    SADDLE("Saddle",
            (itemStackService, moreRecipes, amount) -> new Saddle(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new Saddle(itemStackService, moreRecipes).registerRecipe()),
    SLIME_BALL("SlimeBall",
            (itemStackService, moreRecipes, amount) -> new SlimeBall(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new SlimeBall(itemStackService, moreRecipes).registerRecipe()),
    STRING("String",
            (itemStackService, moreRecipes, amount) -> new StringItem(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new StringItem(itemStackService, moreRecipes).registerRecipe()),
    TOTEM_OF_UNDYING("TotemOfUndying",
            (itemStackService, moreRecipes, amount) -> new TotemOfUndying(itemStackService, moreRecipes).getItemStack(amount),
            (itemStackService, moreRecipes) -> new TotemOfUndying(itemStackService, moreRecipes).registerRecipe());

    private final String itemName;
    private final ItemStackFactory itemStackFactory;
    private final RecipeRegistrar recipeRegistrar;

    MoreRecipesItem(String itemName, ItemStackFactory itemStackFactory, RecipeRegistrar recipeRegistrar) {
        this.itemName = itemName;
        this.itemStackFactory = itemStackFactory;
        this.recipeRegistrar = recipeRegistrar;
    }

    /**
     * @return The name players type when referring to this item, for example in {@code /mr get}.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Builds a stack of this item.
     * @param itemStackService The service used to build the underlying stack.
     * @param moreRecipes The plugin instance.
     * @param amount The number of items the stack should hold.
     * @return The item stack.
     */
    public ItemStack getItemStack(ItemStackService itemStackService, MoreRecipes moreRecipes, int amount) {
        return itemStackFactory.create(itemStackService, moreRecipes, amount);
    }

    /**
     * Registers this item's crafting recipe with the server.
     * @param itemStackService The service used to build the recipe's result.
     * @param moreRecipes The plugin instance, which namespaces the recipe key.
     */
    public void registerRecipe(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        recipeRegistrar.register(itemStackService, moreRecipes);
    }

    /**
     * Finds the item a player named, ignoring case.
     * @param itemName The name the player typed.
     * @return The matching item, or null if no item goes by that name.
     */
    public static MoreRecipesItem findByName(String itemName) {
        for (MoreRecipesItem item : values()) {
            if (item.itemName.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * @return The name of every item in the catalog, in the order players are shown them.
     */
    public static List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (MoreRecipesItem item : values()) {
            itemNames.add(item.itemName);
        }
        return itemNames;
    }

    /**
     * Builds a stack of a catalog item. Kept separate from {@link RecipeRegistrar} because the two
     * operations a catalog entry supports differ in both arguments and return type.
     */
    @FunctionalInterface
    interface ItemStackFactory {
        ItemStack create(ItemStackService itemStackService, MoreRecipes moreRecipes, int amount);
    }

    /**
     * Registers a catalog item's crafting recipe with the server.
     */
    @FunctionalInterface
    interface RecipeRegistrar {
        void register(ItemStackService itemStackService, MoreRecipes moreRecipes);
    }
}
