package dansplugins.recipesystem;

import dansplugins.recipesystem.objects.*;

public class RecipeRegistry {
    
    private static RecipeRegistry instance;
    
    private RecipeRegistry() {
        
    }
    
    public static RecipeRegistry getInstance() {
        if (instance == null) {
            instance = new RecipeRegistry();
        }
        return instance;
    }

    public void registerRecipes() {
        Saddle saddle = new Saddle();
        saddle.registerRecipe();

        NameTag nametag = new NameTag();
        nametag.registerRecipe();

        TotemOfUndying totemOfUndying = new TotemOfUndying();
        totemOfUndying.registerRecipe();

        GrassBlock grassBlock = new GrassBlock();
        grassBlock.registerRecipe();

        StringItem string = new StringItem();
        string.registerRecipe();

        Lead lead = new Lead();
        lead.registerRecipe();

        BlazeRod blazeRod = new BlazeRod();
        blazeRod.registerRecipe();

        ChainmailHelmet chainmailHelmet = new ChainmailHelmet();
        chainmailHelmet.registerRecipe();

        ChainmailChestplate chainmailChestplate = new ChainmailChestplate();
        chainmailChestplate.registerRecipe();

        ChainmailLeggings chainmailLeggings = new ChainmailLeggings();
        chainmailLeggings.registerRecipe();

        ChainmailBoots chainmailBoots = new ChainmailBoots();
        chainmailBoots.registerRecipe();

        IronHorseArmor ironHorseArmor = new IronHorseArmor();
        ironHorseArmor.registerRecipe();

        GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor();
        goldenHorseArmor.registerRecipe();

        DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor();
        diamondHorseArmor.registerRecipe();
    }

}
