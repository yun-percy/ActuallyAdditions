/*
 * This file ("EmpowererHandler.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.recipe;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import de.ellpeck.actuallyadditions.api.recipe.EmpowererRecipe;
import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheCrystals;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import de.ellpeck.actuallyadditions.mod.util.RecipeUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public final class EmpowererHandler{

    public static final ArrayList<EmpowererRecipe> MAIN_PAGE_RECIPES = new ArrayList<EmpowererRecipe>();
    public static EmpowererRecipe recipeEmpoweredCanolaSeed;

    public static void init(){
        addCrystalEmpowering(TheCrystals.REDSTONE, "dyeGray", new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
        addCrystalEmpowering(TheCrystals.LAPIS, "dyeGray", new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
        addCrystalEmpowering(TheCrystals.DIAMOND, "dyeGray", new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));
        addCrystalEmpowering(TheCrystals.IRON, "dyeGray", new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));

        addCrystalEmpowering(TheCrystals.COAL, "dyeGray", new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1));

        List<ItemStack> balls = OreDictionary.getOres("slimeball");
        for(ItemStack ball : balls){
            addCrystalEmpowering(TheCrystals.EMERALD, "dyeGray", new ItemStack(Blocks.TALLGRASS, 1, 1), new ItemStack(Blocks.SAPLING), ball.copy());
        }

        ItemStack seed = new ItemStack(InitItems.itemCanolaSeed);
        ActuallyAdditionsAPI.addEmpowererRecipe(new ItemStack(InitItems.itemMisc, 1, TheMiscItems.CRYSTALLIZED_CANOLA_SEED.ordinal()), new ItemStack(InitItems.itemMisc, 1, TheMiscItems.EMPOWERED_CANOLA_SEED.ordinal()), seed, seed, seed, seed, 1000, 30, new float[]{1F, 91F/255F, 76F/255F});
        recipeEmpoweredCanolaSeed = RecipeUtil.lastEmpowererRecipe();
    }

    private static void addCrystalEmpowering(TheCrystals type, String dye, ItemStack modifier1, ItemStack modifier2, ItemStack modifier3){
        float[] color = type.conversionColorParticles;

        List<ItemStack> dyes = OreDictionary.getOres(dye);
        for(ItemStack dyeStack : dyes){
            ActuallyAdditionsAPI.addEmpowererRecipe(new ItemStack(InitItems.itemCrystal, 1, type.ordinal()), new ItemStack(InitItems.itemCrystalEmpowered, 1, type.ordinal()), dyeStack, modifier1, modifier2, modifier3, 500, 50, color);
            MAIN_PAGE_RECIPES.add(RecipeUtil.lastEmpowererRecipe());
            ActuallyAdditionsAPI.addEmpowererRecipe(new ItemStack(InitBlocks.blockCrystal, 1, type.ordinal()), new ItemStack(InitBlocks.blockCrystalEmpowered, 1, type.ordinal()), dyeStack, modifier1, modifier2, modifier3, 5000, 500, color);
            MAIN_PAGE_RECIPES.add(RecipeUtil.lastEmpowererRecipe());
        }
    }
}
