/*
 * This file ("BlockRangedCollector.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.blocks;

import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.blocks.base.BlockContainerBase;
import de.ellpeck.actuallyadditions.mod.inventory.GuiHandler;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityRangedCollector;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRangedCollector extends BlockContainerBase{

    public BlockRangedCollector(String name){
        super(Material.ROCK, name);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
    }


    @Override
    public TileEntity createNewTileEntity(World world, int par2){
        return new TileEntityRangedCollector();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing par6, float par7, float par8, float par9){
        if(this.tryToggleRedstone(world, pos, player)){
            return true;
        }

        if(!world.isRemote){
            TileEntityRangedCollector breaker = (TileEntityRangedCollector)world.getTileEntity(pos);
            if(breaker != null){
                player.openGui(ActuallyAdditions.instance, GuiHandler.GuiTypes.RANGED_COLLECTOR.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack){
        return EnumRarity.COMMON;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
        if(!world.isRemote){
            TileEntity aTile = world.getTileEntity(pos);
            if(aTile instanceof TileEntityRangedCollector){
                TileEntityRangedCollector tile = (TileEntityRangedCollector)aTile;
                for(int i = 0; i < TileEntityRangedCollector.WHITELIST_START; i++){
                    this.dropSlotFromInventory(i, tile, world, pos);
                }
            }
        }
        super.breakBlock(world, pos, state);
    }
}
