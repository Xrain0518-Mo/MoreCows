package com.momo.morecows.entity.tiles.blockTiles;

import javax.annotation.Nonnull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
public class TileEntityMilkWorkshop extends TileEntity {
    public static final String ID = "milk_workshop";

    private final ItemStackHandler inventory = new ItemStackHandler(4);

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag("Inventory",this.inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        return itemHandlerCapability.equals(capability) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new IItemHandlerWithMilkWorkshop(facing));
        }
        return super.getCapability(capability, facing);
    }

    public class IItemHandlerWithMilkWorkshop implements IItemHandler {

        // 0 -> up -> materialIn
        // 1 -> other -> bucketIn
        // 2,3 -> down materialOut bucketOut

        private final EnumFacing facing;

        public IItemHandlerWithMilkWorkshop(EnumFacing facing) {
            this.facing = facing;
        }

        @Override
        public int getSlots()
        {
            return inventory.getSlots();
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot)
        {
            return inventory.getStackInSlot(slot);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
        {
            if (slot == 0 && facing == EnumFacing.UP)
            {
                return inventory.insertItem(0, stack, simulate);
            } else if (slot == 1 && stack.getItem() == Items.BUCKET)
            {
                if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
                    return inventory.insertItem(1, stack, simulate);
            }
            return stack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate)
        {
            if (facing == EnumFacing.DOWN)
            {
                return inventory.getStackInSlot(4).isEmpty() ? inventory.extractItem(3, amount, simulate) : inventory.extractItem(4, amount, simulate);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public int getSlotLimit(int slot)
        {
            return 0;
        }
    }
}
