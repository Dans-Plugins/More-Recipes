package dansplugins.recipesystem.utils;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.objects.MoreRecipesItem;
import dansplugins.recipesystem.services.ItemStackService;

public class RecipeRegistry {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public RecipeRegistry(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    /**
     * Registers the crafting recipe of every item in the catalog.
     */
    public void registerRecipes() {
        for (MoreRecipesItem item : MoreRecipesItem.values()) {
            item.registerRecipe(itemStackService, moreRecipes);
        }
    }

}
