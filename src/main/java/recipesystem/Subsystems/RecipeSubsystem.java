package recipesystem.Subsystems;

import recipesystem.ItemStacks.Saddle;
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

        Saddle saddle = new Saddle(main);
        saddle.registerRecipe();
    }

}
