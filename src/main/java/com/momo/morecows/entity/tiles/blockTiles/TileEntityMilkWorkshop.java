package com.momo.morecows.entity.tiles.blockTiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityMilkWorkshop extends TileEntity {
    public static final String ID = "milk_workshop";

    private final ItemStackHandler materialIn = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot){ TileEntityMilkWorkshop.this.markDirty();}
    };

    private final ItemStackHandler milkIn = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot){ TileEntityMilkWorkshop.this.markDirty();}
    };

    private final ItemStackHandler materialOut = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot){ TileEntityMilkWorkshop.this.markDirty();}
    };

    private final ItemStackHandler bucketOut = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot){ TileEntityMilkWorkshop.this.markDirty();}
    };

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        this.materialIn.deserializeNBT(compound.getCompoundTag("MaterialIn"));
        this.milkIn.deserializeNBT(compound.getCompoundTag("MilkIn"));
        this.materialOut.deserializeNBT(compound.getCompoundTag("MaterialOut"));
        this.bucketOut.deserializeNBT(compound.getCompoundTag("BucketOut"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag("MaterialIn",this.materialIn.serializeNBT());
        compound.setTag("MilkIn",this.milkIn.serializeNBT());
        compound.setTag("MaterialOut",this.materialOut.serializeNBT());
        compound.setTag("bucketOut",this.bucketOut.serializeNBT());
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
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        if(itemHandlerCapability.equals(capability))
        {
            if(EnumFacing.UP.equals(facing)) return itemHandlerCapability.cast(this.materialIn);
            if(EnumFacing.WEST.equals(facing) || EnumFacing.EAST.equals(facing) || EnumFacing.NORTH.equals(facing) || EnumFacing.SOUTH.equals(facing)) return itemHandlerCapability.cast(this.milkIn);
            if(EnumFacing.DOWN.equals(facing)) return itemHandlerCapability.cast(this.materialOut);
        }
        return super.getCapability(capability, facing);
    }
}
