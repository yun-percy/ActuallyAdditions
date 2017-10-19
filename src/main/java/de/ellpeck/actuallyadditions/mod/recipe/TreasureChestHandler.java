/*
 * This file ("TreasureChestHandler.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.recipe;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheJams;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class TreasureChestHandler{

    public static void init(){
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.DIAMOND), 10, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.IRON_INGOT), 60, 1, 5);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.GOLD_NUGGET), 100, 1, 8);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.GOLD_INGOT), 70, 1, 3);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.ENDER_PEARL), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.EMERALD), 20, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.EXPERIENCE_BOTTLE), 10, 3, 6);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemSolidifiedExperience), 30, 3, 6);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_11), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_13), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_BLOCKS), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_CAT), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_CHIRP), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_FAR), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_MALL), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_MELLOHI), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_STAL), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_STRAD), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_WARD), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.RECORD_WAIT), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.SADDLE), 10, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.NAME_TAG), 40, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemJams, 1, TheJams.CU_BA_RA.ordinal()), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemJams, 1, TheJams.GRA_KI_BA.ordinal()), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemJams, 1, TheJams.PL_AP_LE.ordinal()), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemJams, 1, TheJams.CH_AP_CI.ordinal()), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemJams, 1, TheJams.HO_ME_KI.ordinal()), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(InitItems.itemJams, 1, TheJams.PI_CO.ordinal()), 20, 1, 2);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.FISH), 50, 1, 3);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.FISH, 1, 1), 50, 1, 3);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.FISH, 1, 2), 20, 1, 1);
        ActuallyAdditionsAPI.addTreasureChestLoot(new ItemStack(Items.FISH, 1, 3), 20, 1, 2);
    }

}
