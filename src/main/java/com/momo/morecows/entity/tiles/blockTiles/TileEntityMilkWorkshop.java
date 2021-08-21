package com.momo.morecows.entity.tiles.blockTiles;

import com.google.common.collect.Lists;
import com.momo.morecows.util.ItemStackUtil;
import com.momo.morecows.util.recipes.MilkWorkshopRecipe;
import com.momo.morecows.util.recipes.RecipesManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class TileEntityMilkWorkshop extends TileEntity implements ITickable {

    public static final String ID = "milk_workshop";
    public final ItemStackHandler inventory = new ItemStackHandler(4);
    private int compressorProgress = 0;

    public int getCompressorProgress() {
        return this.compressorProgress;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.compressorProgress = compound.getInteger("Progress");
        this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("Progress", this.compressorProgress);
        compound.setTag("Inventory", this.inventory.serializeNBT());
        this.markDirty();
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        if (!this.world.isRemote) {

            List<ItemStack> inputs = Lists.newArrayList(inventory.getStackInSlot(0), inventory.getStackInSlot(1));
            List<ItemStack> outputs = Lists.newArrayList(inventory.getStackInSlot(2), inventory.getStackInSlot(3));
            MilkWorkshopRecipe recipeTrue =
                    RecipesManager.getMilkWorkshopRecipe(new MilkWorkshopRecipe(inputs, outputs, 0));

            if (recipeTrue != null) {
                this.compressorProgress += 1;
                if (this.compressorProgress >= recipeTrue.getProgress()) {
                    this.inventory.extractItem(0, 1, false);
                    this.inventory.extractItem(1, 1, false);
                    this.inventory.insertItem(2, recipeTrue.getOutputs().get(0).copy(), false);
                    this.inventory.insertItem(3, recipeTrue.getOutputs().get(1).copy(), false);
                    this.compressorProgress = 0;
                }
                this.markDirty();
            } else if (this.compressorProgress > 0) {
                this.compressorProgress = 0;
                this.markDirty();
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        return itemHandlerCapability.equals(capability) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
        public int getSlots() {
            return inventory.getSlots();
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return inventory.getStackInSlot(slot);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (slot == 0 && facing == EnumFacing.UP) {
                return inventory.insertItem(0, stack, simulate);
            } else if (slot == 1 && isMaterials(stack, 1)) {
                if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
                    return inventory.insertItem(1, stack, simulate);
            }
            return stack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (facing == EnumFacing.DOWN) {
                return inventory.getStackInSlot(3).isEmpty() ? inventory.extractItem(2, amount, simulate) : inventory.extractItem(3, amount, simulate);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public int getSlotLimit(int slot) {
            return inventory.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return (slot == 0 || slot == 1);
        }
    }

    public static boolean isMaterials(ItemStack stack, int slot) {
        for (MilkWorkshopRecipe recipe : RecipesManager.getMilkWorkshopRecipe()) {
            if (ItemStackUtil.areItemStackEqual(recipe.getInputs().get(slot), stack)) {
                return true;
            }
        }
        return false;
    }
}
