package recipesystem.Subsystems;

import recipesystem.ItemStacks.Salt;
import recipesystem.Main;

public class RecipeSubsystem {

    Main main = null;

    public RecipeSubsystem(Main plugin) {
        main = plugin;
    }

    public void registerRecipes() {
        Salt salt = new Salt(main);
        salt.registerRecipe();
    }

}
