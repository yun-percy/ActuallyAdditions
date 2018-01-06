/*
 * This file ("BlockLampPowerer.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.blocks;


import de.ellpeck.actuallyadditions.mod.blocks.base.BlockBase;
import de.ellpeck.actuallyadditions.mod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockLampPowerer extends BlockBase{

    private static final PropertyInteger META = PropertyInteger.create("meta", 0, 5);

    public BlockLampPowerer(String name){
        super(Material.ROCK, name);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn){
        this.updateLamp(worldIn, pos);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state){
        this.updateLamp(world, pos);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack){
        int rotation = BlockPistonBase.getFacingFromEntity(pos, player).ordinal();
        world.setBlockState(pos, this.getStateFromMeta(rotation), 2);

        super.onBlockPlacedBy(world, pos, state, player, stack);
    }

    private void updateLamp(World world, BlockPos pos){
        if(!world.isRemote){
            IBlockState state = world.getBlockState(pos);
            BlockPos coords = pos.offset(WorldUtil.getDirectionByPistonRotation(state.getBlock().getMetaFromState(state)));
            this.updateLampsAtPos(world, coords, world.isBlockIndirectlyGettingPowered(pos) > 0, new ArrayList<BlockPos>());
        }
    }

    private void updateLampsAtPos(World world, BlockPos pos, boolean powered, List<BlockPos> updatedAlready){
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if(block instanceof BlockColoredLamp){
            boolean isOn = ((BlockColoredLamp)block).isOn;
            int meta = block.getMetaFromState(state);
            if(powered){
                if(!isOn){
                    world.setBlockState(pos, InitBlocks.blockColoredLampOn.getStateFromMeta(meta), 2);
                }
            }
            else{
                if(isOn){
                    world.setBlockState(pos, InitBlocks.blockColoredLamp.getStateFromMeta(meta), 2);
                }
            }

            this.updateSurrounding(world, pos, powered, updatedAlready);
        }
    }

    private void updateSurrounding(World world, BlockPos pos, boolean powered, List<BlockPos> updatedAlready){
        for(EnumFacing side : EnumFacing.values()){
            BlockPos offset = pos.offset(side);
            if(!updatedAlready.contains(offset)){
                updatedAlready.add(pos);
                this.updateLampsAtPos(world, offset, powered, updatedAlready);
            }
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack){
        return EnumRarity.COMMON;
    }

    @Override
    protected PropertyInteger getMetaProperty(){
        return META;
    }
}
