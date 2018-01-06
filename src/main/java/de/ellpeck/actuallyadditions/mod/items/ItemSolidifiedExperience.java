/*
 * This file ("ItemSolidifiedExperience.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.items;

import de.ellpeck.actuallyadditions.mod.config.values.ConfigBoolValues;
import de.ellpeck.actuallyadditions.mod.items.base.ItemBase;
import de.ellpeck.actuallyadditions.mod.util.ModUtil;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSolidifiedExperience extends ItemBase{

    public static final int SOLID_XP_AMOUNT = 8;

    public ItemSolidifiedExperience(String name){
        super(name);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityDropEvent(LivingDropsEvent event){
        if(ConfigBoolValues.DO_XP_DROPS.isEnabled()){
            if(event.getEntityLiving().worldObj != null && !event.getEntityLiving().worldObj.isRemote && event.getSource().getEntity() instanceof EntityPlayer){
                //Drop Solidified XP
                if(event.getEntityLiving() instanceof EntityCreature){
                    if(event.getEntityLiving().worldObj.rand.nextInt(10) <= event.getLootingLevel()*2){
                        event.getDrops().add(new EntityItem(event.getEntityLiving().worldObj, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, new ItemStack(InitItems.itemSolidifiedExperience, event.getEntityLiving().worldObj.rand.nextInt(2+event.getLootingLevel())+1)));
                    }
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
        if(!world.isRemote){
            int amount;
            if(!player.isSneaking()){
                amount = SOLID_XP_AMOUNT;
                if(!player.capabilities.isCreativeMode){
                    stack.stackSize--;
                }
            }
            else{
                amount = SOLID_XP_AMOUNT*StackUtil.getStackSize(stack);
                if(!player.capabilities.isCreativeMode){
                    stack.stackSize = 0;
                }
            }

            EntityXPOrb orb = new EntityXPOrb(world, player.posX+0.5, player.posY+0.5, player.posZ+0.5, amount);
            orb.getEntityData().setBoolean(ModUtil.MOD_ID+"FromSolidified", true);
            world.spawnEntityInWorld(orb);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }


    @Override
    public EnumRarity getRarity(ItemStack stack){
        return EnumRarity.COMMON;
    }
}
