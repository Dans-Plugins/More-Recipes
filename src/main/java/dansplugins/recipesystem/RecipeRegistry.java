package dansplugins.recipesystem;

import dansplugins.recipesystem.objects.*;

public class RecipeRegistry {

    MoreRecipes moreRecipes = null;

    public RecipeRegistry(MoreRecipes plugin) {
        moreRecipes = plugin;
    }

    public void registerRecipes() {
        Saddle saddle = new Saddle(moreRecipes);
        saddle.registerRecipe();

        NameTag nametag = new NameTag(moreRecipes);
        nametag.registerRecipe();

        TotemOfUndying totemOfUndying = new TotemOfUndying(moreRecipes);
        totemOfUndying.registerRecipe();

        GrassBlock grassBlock = new GrassBlock(moreRecipes);
        grassBlock.registerRecipe();

        StringItem string = new StringItem(moreRecipes);
        string.registerRecipe();

        Lead lead = new Lead(moreRecipes);
        lead.registerRecipe();

        BlazeRod blazeRod = new BlazeRod(moreRecipes);
        blazeRod.registerRecipe();

        ChainmailHelmet chainmailHelmet = new ChainmailHelmet(moreRecipes);
        chainmailHelmet.registerRecipe();

        ChainmailChestplate chainmailChestplate = new ChainmailChestplate(moreRecipes);
        chainmailChestplate.registerRecipe();

        ChainmailLeggings chainmailLeggings = new ChainmailLeggings(moreRecipes);
        chainmailLeggings.registerRecipe();

        ChainmailBoots chainmailBoots = new ChainmailBoots(moreRecipes);
        chainmailBoots.registerRecipe();

        IronHorseArmor ironHorseArmor = new IronHorseArmor(moreRecipes);
        ironHorseArmor.registerRecipe();

        GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor(moreRecipes);
        goldenHorseArmor.registerRecipe();

        DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor(moreRecipes);
        diamondHorseArmor.registerRecipe();
    }

}
