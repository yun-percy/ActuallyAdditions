/*
 * This file ("BlockSmileyCloud.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.blocks;


import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.achievement.TheAchievements;
import de.ellpeck.actuallyadditions.mod.blocks.base.BlockContainerBase;
import de.ellpeck.actuallyadditions.mod.inventory.GuiHandler;
import de.ellpeck.actuallyadditions.mod.tile.TileEntitySmileyCloud;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSmileyCloud extends BlockContainerBase{

    private static final PropertyInteger META = PropertyInteger.create("meta", 0, 7);

    public BlockSmileyCloud(String name){
        super(Material.CLOTH, name);
        this.setHardness(0.5F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.CLOTH);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand){
        if(world.rand.nextInt(30) == 0){
            for(int i = 0; i < 2; i++){
                double d = world.rand.nextGaussian()*0.02D;
                double d1 = world.rand.nextGaussian()*0.02D;
                double d2 = world.rand.nextGaussian()*0.02D;
                world.spawnParticle(EnumParticleTypes.HEART, pos.getX()+world.rand.nextFloat(), pos.getY()+0.65+world.rand.nextFloat(), pos.getZ()+world.rand.nextFloat(), d, d1, d2);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing f6, float f7, float f8, float f9){
        if(!world.isRemote){
            TileEntity tile = world.getTileEntity(pos);
            if(tile instanceof TileEntitySmileyCloud){
                player.openGui(ActuallyAdditions.instance, GuiHandler.GuiTypes.CLOUD.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());

                TheAchievements.NAME_SMILEY_CLOUD.get(player);
            }
        }
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntitySmileyCloud();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
        this.dropInventory(world, pos);
        super.breakBlock(world, pos, state);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack){
        return EnumRarity.COMMON;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack){
        int rotation = MathHelper.floor_double((double)(player.rotationYaw*4.0F/360.0F)+0.5D) & 3;

        if(rotation == 0){
            world.setBlockState(pos, this.getStateFromMeta(0), 2);
        }
        if(rotation == 1){
            world.setBlockState(pos, this.getStateFromMeta(3), 2);
        }
        if(rotation == 2){
            world.setBlockState(pos, this.getStateFromMeta(1), 2);
        }
        if(rotation == 3){
            world.setBlockState(pos, this.getStateFromMeta(2), 2);
        }

        super.onBlockPlacedBy(world, pos, state, player, stack);
    }

    @Override
    protected PropertyInteger getMetaProperty(){
        return META;
    }
}
