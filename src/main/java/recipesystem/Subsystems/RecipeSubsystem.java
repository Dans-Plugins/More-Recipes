package recipesystem.Subsystems;

import recipesystem.ItemStacks.*;
import recipesystem.Main;

public class RecipeSubsystem {

    Main main = null;

    public RecipeSubsystem(Main plugin) {
        main = plugin;
    }

    public void registerRecipes() {
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

        ChainmailChestplate chainmailChestplate = new ChainmailChestplate(main);
        chainmailChestplate.registerRecipe();

        ChainmailLeggings chainmailLeggings = new ChainmailLeggings(main);
        chainmailLeggings.registerRecipe();

        ChainmailBoots chainmailBoots = new ChainmailBoots(main);
        chainmailBoots.registerRecipe();

        IronHorseArmor ironHorseArmor = new IronHorseArmor(main);
        ironHorseArmor.registerRecipe();

        GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor(main);
        goldenHorseArmor.registerRecipe();

        DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor(main);
        diamondHorseArmor.registerRecipe();
    }

}
