/*
 * This file ("TileEntityInventoryBase.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.tile;

import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import java.util.Arrays;

public abstract class TileEntityInventoryBase extends TileEntityBase implements ISidedInventory{

    private final SidedInvWrapper[] invWrappers = new SidedInvWrapper[6];
    public ItemStack slots[];

    public TileEntityInventoryBase(int slots, String name){
        super(name);

        this.slots = new ItemStack[slots];
        Arrays.fill(this.slots, StackUtil.getNull());

        if(this.hasInvWrapperCapabilities()){
            this.getInvWrappers(this.invWrappers);
        }
    }

    public static void saveSlots(ItemStack[] slots, NBTTagCompound compound){
        if(slots != null && slots.length > 0){
            NBTTagList tagList = new NBTTagList();
            for(ItemStack slot : slots){
                NBTTagCompound tagCompound = new NBTTagCompound();
                if(StackUtil.isValid(slot)){
                    slot.writeToNBT(tagCompound);
                }
                tagList.appendTag(tagCompound);
            }
            compound.setTag("Items", tagList);
        }
    }

    public static void loadSlots(ItemStack[] slots, NBTTagCompound compound){
        if(slots != null && slots.length > 0){
            NBTTagList tagList = compound.getTagList("Items", 10);
            for(int i = 0; i < slots.length; i++){
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                slots[i] = tagCompound != null && tagCompound.hasKey("id") ? ItemStack.loadItemStackFromNBT(tagCompound) : StackUtil.getNull();
            }
        }
    }

    protected void getInvWrappers(SidedInvWrapper[] wrappers){
        for(int i = 0; i < wrappers.length; i++){
            wrappers[i] = new SidedInvWrapper(this, EnumFacing.values()[i]);
        }
    }

    @Override
    public int getComparatorStrength(){
        return Container.calcRedstoneFromInventory(this);
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, NBTType type){
        super.writeSyncableNBT(compound, type);
        if(type == NBTType.SAVE_TILE || (type == NBTType.SYNC && this.shouldSyncSlots())){
            saveSlots(this.slots, compound);
        }
    }

    public boolean shouldSyncSlots(){
        return false;
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, NBTType type){
        super.readSyncableNBT(compound, type);
        if(type == NBTType.SAVE_TILE || (type == NBTType.SYNC && this.shouldSyncSlots())){
            loadSlots(this.slots, compound);
        }
    }


    @Override
    public int[] getSlotsForFace(EnumFacing side){
        int invSize = this.getSizeInventory();
        if(invSize > 0){
            int[] theInt = new int[invSize];
            for(int i = 0; i < theInt.length; i++){
                theInt[i] = i;
            }
            return theInt;
        }
        else{
            return new int[0];
        }
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player){
        return this.canPlayerUse(player);
    }

    @Override
    public void openInventory(EntityPlayer player){

    }

    @Override
    public void closeInventory(EntityPlayer player){

    }

    @Override
    public int getField(int id){
        return 0;
    }

    @Override
    public void setField(int id, int value){

    }

    @Override
    public int getFieldCount(){
        return 0;
    }

    @Override
    public void clear(){
        for(int i = 0; i < this.slots.length; i++){
            this.removeStackFromSlot(i);
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack){
        this.slots[i] = stack;
        this.markDirty();
    }

    @Override
    public int getSizeInventory(){
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i){
        if(i < this.getSizeInventory()){
            return this.slots[i];
        }
        return StackUtil.getNull();
    }

    @Override
    public ItemStack decrStackSize(int i, int j){
        if(StackUtil.isValid(this.slots[i])){
            ItemStack stackAt;
            if(StackUtil.getStackSize(this.slots[i]) <= j){
                stackAt = this.slots[i];
                this.slots[i] = StackUtil.getNull();
                this.markDirty();
                return stackAt;
            }
            else{
                stackAt = this.slots[i].splitStack(j);
                if(StackUtil.getStackSize(this.slots[i]) <= 0){
                    this.slots[i] = StackUtil.getNull();
                }
                this.markDirty();
                return stackAt;
            }
        }
        return StackUtil.getNull();
    }

    @Override
    public ItemStack removeStackFromSlot(int index){
        ItemStack stack = this.slots[index];
        this.slots[index] = StackUtil.getNull();
        return stack;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public boolean hasCustomName(){
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing){
        if(this.hasInvWrapperCapabilities() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return (T)this.invWrappers[facing == null ? 0 : facing.ordinal()];
        }
        else{
            return super.getCapability(capability, facing);
        }
    }

    public boolean hasInvWrapperCapabilities(){
        return true;
    }
}
