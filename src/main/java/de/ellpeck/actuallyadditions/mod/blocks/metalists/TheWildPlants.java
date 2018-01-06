/*
 * This file ("TheWildPlants.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.blocks.metalists;

import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;

public enum TheWildPlants{

    CANOLA("Canola", EnumRarity.COMMON, InitBlocks.blockCanola),
    FLAX("Flax", EnumRarity.COMMON, InitBlocks.blockFlax),
    RICE("Rice", EnumRarity.COMMON, InitBlocks.blockRice),
    COFFEE("Coffee", EnumRarity.COMMON, InitBlocks.blockCoffee);

    public final String name;
    public final EnumRarity rarity;
    public final Block wildVersionOf;

    TheWildPlants(String name, EnumRarity rarity, Block wildVersionOf){
        this.name = name;
        this.rarity = rarity;
        this.wildVersionOf = wildVersionOf;
    }
}