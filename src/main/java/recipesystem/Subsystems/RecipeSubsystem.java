package recipesystem.Subsystems;

import recipesystem.ItemStacks.Uncraftable.*;
import recipesystem.ItemStacks.Custom.Salt;
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

        // uncraftable items
        Saddle saddle = new Saddle(main);
        saddle.registerRecipe();

        NameTag nametag = new NameTag(main);
        nametag.registerRecipe();

        TotemOfUndying totemOfUndying = new TotemOfUndying(main);
        totemOfUndying.registerRecipe();

        GrassBlock grassBlock = new GrassBlock(main);
        grassBlock.registerRecipe();

        StringItem string = new StringItem(main);
        string.registerRecipe();

        Lead lead = new Lead(main);
        lead.registerRecipe();

        BlazeRod blazeRod = new BlazeRod(main);
        blazeRod.registerRecipe();

        ChainmailHelmet chainmailHelmet = new ChainmailHelmet(main);
        chainmailHelmet.registerRecipe();
    }

}
