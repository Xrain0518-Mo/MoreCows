package com.momo.morecows.entity.tiles.blockTiles;

import javax.annotation.Nonnull;

import com.momo.morecows.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
public class TileEntityMilkWorkshop extends TileEntity implements ITickable {

    public static final String ID = "milk_workshop";
    public final ItemStackHandler inventory = new ItemStackHandler(4);
    private int compressorProgress = 0;

    public int getCompressorProgress(){return this.compressorProgress;}

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        this.compressorProgress = compound.getInteger("Progress");
        this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("Progress", this.compressorProgress);
        compound.setTag("Inventory",this.inventory.serializeNBT());
        this.markDirty();
        return super.writeToNBT(compound);
    }

    @Override
    public void update()
    {
        if (!this.world.isRemote) {
            boolean canProgress = (Items.SUGAR.equals(this.inventory.extractItem(0, 1, true).getItem()) && Items.MILK_BUCKET.equals(this.inventory.extractItem(1, 1, true).getItem()));
            boolean canOutPut = (this.inventory.insertItem(2, new ItemStack(ModItems.CHEESE), true).isEmpty() && this.inventory.insertItem(3, new ItemStack(Items.BUCKET), true).isEmpty());

            if (canProgress && canOutPut)
            {
                this.compressorProgress += 1;
                if (this.compressorProgress >= 200) {
                    this.inventory.insertItem(2, new ItemStack(ModItems.CHEESE), false);
                    this.inventory.insertItem(3, new ItemStack(Items.BUCKET), false);
                    this.inventory.extractItem(0, 1, false);
                    this.inventory.extractItem(1, 1, false);
                    this.compressorProgress = 0;
                }
                this.markDirty();
            }
            else if(this.compressorProgress > 0) {
                this.compressorProgress = 0;
                this.markDirty();
            }
        }
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

        // 0 -> up -> upIn
        // 1 -> other -> downIn
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
            }
            else if (slot == 1 && isDownMaterials(stack))
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
                return inventory.getStackInSlot(3).isEmpty() ? inventory.extractItem(2, amount, simulate) : inventory.extractItem(3, amount, simulate);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public int getSlotLimit(int slot)
        {
            return inventory.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack){
            return (slot == 0 || slot == 1);
        }
    }

    public static boolean isDownMaterials(ItemStack stack)
    {
        Item item = stack.getItem();
        return (item.equals(Items.MILK_BUCKET) || item.equals(ModItems.ROTTEN_MILK) || item.equals(ModItems.CHEESE) || item.equals(ModItems.ROTTEN_CHEESE));
    }

    public static boolean isUpMaterials(ItemStack stack)
    {
        Item item = stack.getItem();
        return (item.equals(Items.SUGAR) || item.equals(Items.GLOWSTONE_DUST));
    }
}
