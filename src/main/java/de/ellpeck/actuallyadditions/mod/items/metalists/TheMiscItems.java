/*
 * This file ("TheMiscItems.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.items.metalists;

import de.ellpeck.actuallyadditions.mod.util.Util;
import net.minecraft.item.EnumRarity;

public enum TheMiscItems{

    PAPER_CONE("PaperCone", EnumRarity.COMMON),
    MASHED_FOOD("MashedFood", EnumRarity.COMMON),
    KNIFE_BLADE("KnifeBlade", EnumRarity.COMMON),
    KNIFE_HANDLE("KnifeHandle", EnumRarity.COMMON),
    DOUGH("Dough", EnumRarity.COMMON),
    QUARTZ("BlackQuartz", EnumRarity.COMMON),
    RING("Ring", EnumRarity.COMMON),
    COIL("Coil", EnumRarity.COMMON),
    COIL_ADVANCED("CoilAdvanced", EnumRarity.COMMON),
    RICE_DOUGH("RiceDough", EnumRarity.COMMON),
    TINY_COAL("TinyCoal", EnumRarity.COMMON),
    TINY_CHAR("TinyCharcoal", EnumRarity.COMMON),
    RICE_SLIME("RiceSlime", EnumRarity.COMMON),
    CANOLA("Canola", EnumRarity.COMMON),
    CUP("Cup", EnumRarity.COMMON),
    BAT_WING("BatWing", EnumRarity.COMMON),
    DRILL_CORE("DrillCore", EnumRarity.COMMON),
    BLACK_DYE("BlackDye", EnumRarity.COMMON),
    LENS("Lens", EnumRarity.COMMON),
    ENDER_STAR("EnderStar", EnumRarity.COMMON),
    SPAWNER_SHARD("SpawnerShard", EnumRarity.COMMON),
    BIOMASS("Biomass", EnumRarity.COMMON),
    BIOCOAL("Biocoal", EnumRarity.COMMON),
    CRYSTALLIZED_CANOLA_SEED("CrystallizedCanolaSeed", EnumRarity.COMMON),
    EMPOWERED_CANOLA_SEED("EmpoweredCanolaSeed", EnumRarity.COMMON),
    YOUTUBE_ICON("YoutubeIcon", Util.FALLBACK_RARITY);

    public final String name;
    public final EnumRarity rarity;

    TheMiscItems(String name, EnumRarity rarity){
        this.name = name;
        this.rarity = rarity;
    }
}