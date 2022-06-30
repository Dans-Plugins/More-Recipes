package dansplugins.recipesystem.utils;

import dansplugins.recipesystem.MoreRecipes;
import dansplugins.recipesystem.objects.*;
import dansplugins.recipesystem.services.ItemStackService;

public class RecipeRegistry {
    private final ItemStackService itemStackService;
    private final MoreRecipes moreRecipes;

    public RecipeRegistry(ItemStackService itemStackService, MoreRecipes moreRecipes) {
        this.itemStackService = itemStackService;
        this.moreRecipes = moreRecipes;
    }

    public void registerRecipes() {
        Saddle saddle = new Saddle(itemStackService, moreRecipes);
        saddle.registerRecipe();

        NameTag nametag = new NameTag(itemStackService, moreRecipes);
        nametag.registerRecipe();

        TotemOfUndying totemOfUndying = new TotemOfUndying(itemStackService, moreRecipes);
        totemOfUndying.registerRecipe();

        GrassBlock grassBlock = new GrassBlock(itemStackService, moreRecipes);
        grassBlock.registerRecipe();

        StringItem string = new StringItem(itemStackService, moreRecipes);
        string.registerRecipe();

        Lead lead = new Lead(itemStackService, moreRecipes);
        lead.registerRecipe();

        BlazeRod blazeRod = new BlazeRod(itemStackService, moreRecipes);
        blazeRod.registerRecipe();

        ChainmailHelmet chainmailHelmet = new ChainmailHelmet(itemStackService, moreRecipes);
        chainmailHelmet.registerRecipe();

        ChainmailChestplate chainmailChestplate = new ChainmailChestplate(itemStackService, moreRecipes);
        chainmailChestplate.registerRecipe();

        ChainmailLeggings chainmailLeggings = new ChainmailLeggings(itemStackService, moreRecipes);
        chainmailLeggings.registerRecipe();

        ChainmailBoots chainmailBoots = new ChainmailBoots(itemStackService, moreRecipes);
        chainmailBoots.registerRecipe();

        IronHorseArmor ironHorseArmor = new IronHorseArmor(itemStackService, moreRecipes);
        ironHorseArmor.registerRecipe();

        GoldenHorseArmor goldenHorseArmor = new GoldenHorseArmor(itemStackService, moreRecipes);
        goldenHorseArmor.registerRecipe();

        DiamondHorseArmor diamondHorseArmor = new DiamondHorseArmor(itemStackService, moreRecipes);
        diamondHorseArmor.registerRecipe();

        Gunpowder gunpowder = new Gunpowder(itemStackService, moreRecipes);
        gunpowder.registerRecipe();

        Cobweb cobweb = new Cobweb(itemStackService, moreRecipes);
        cobweb.registerRecipe();

        SlimeBall slimeBall = new SlimeBall(itemStackService, moreRecipes);
        slimeBall.registerRecipe();

        PrismarineShard prismarineShard = new PrismarineShard(itemStackService, moreRecipes);
        prismarineShard.registerRecipe();
    }

}
