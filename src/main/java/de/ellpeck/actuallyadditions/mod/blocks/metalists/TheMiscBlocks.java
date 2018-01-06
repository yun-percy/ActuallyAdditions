/*
 * This file ("TheMiscBlocks.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.blocks.metalists;

import net.minecraft.item.EnumRarity;

public enum TheMiscBlocks{

    QUARTZ_PILLAR("BlackQuartzPillar", EnumRarity.COMMON),
    QUARTZ_CHISELED("BlackQuartzChiseled", EnumRarity.COMMON),
    QUARTZ("BlackQuartz", EnumRarity.COMMON),
    ORE_QUARTZ("OreBlackQuartz", EnumRarity.COMMON),
    WOOD_CASING("WoodCasing", EnumRarity.COMMON),
    CHARCOAL_BLOCK("Charcoal", EnumRarity.COMMON),
    ENDERPEARL_BLOCK("Enderpearl", EnumRarity.COMMON),
    LAVA_FACTORY_CASE("LavaFactoryCase", EnumRarity.COMMON),
    ENDER_CASING("EnderCasing", EnumRarity.COMMON),
    IRON_CASING("IronCasing", EnumRarity.COMMON);

    public final String name;
    public final EnumRarity rarity;

    TheMiscBlocks(String name, EnumRarity rarity){
        this.name = name;
        this.rarity = rarity;
    }
}