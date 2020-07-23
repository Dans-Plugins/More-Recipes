package recipesystem.Subsystems;

import recipesystem.ItemStacks.Vanilla.Nametag;
import recipesystem.ItemStacks.Vanilla.Saddle;
import recipesystem.ItemStacks.Custom.Salt;
import recipesystem.ItemStacks.Vanilla.TotemOfUndying;
import recipesystem.Main;

public class RecipeSubsystem {

    Main main = null;

    public RecipeSubsystem(Main plugin) {
        main = plugin;
    }

    public void registerRecipes() {

        // custom items
        Salt salt = new Salt(main);
        salt.registerRecipe();

        // vanilla items
        Saddle saddle = new Saddle(main);
        saddle.registerRecipe();

        Nametag nametag = new Nametag(main);
        nametag.registerRecipe();

        TotemOfUndying totemOfUndying = new TotemOfUndying(main);
        totemOfUndying.registerRecipe();
    }

}
