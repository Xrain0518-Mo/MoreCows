package com.momo.morecows.network.MilkWorkshop;

import com.momo.morecows.entity.tiles.blockTiles.TileEntityMilkWorkshop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerMilkWorkshop extends Container {
    private final World world;
    private final BlockPos pos;
    private int compressorProgress = 0;

    public ContainerMilkWorkshop(EntityPlayer player, World world, int x, int y, int z) {
        this.world = world;
        this.pos = new BlockPos(x, y, z);

        TileEntity tileEntity = world.getTileEntity(this.pos);
        if (tileEntity instanceof TileEntityMilkWorkshop) {
            ItemStackHandler inventory = ((TileEntityMilkWorkshop) tileEntity).inventory;

            this.addSlotToContainer(new SlotItemHandler(inventory, 0, 30, 27));
            this.addSlotToContainer(new SlotMilkWorkMilkIn(inventory, 1, 49, 47));
            this.addSlotToContainer(new SlotMilkWorkOut(inventory, 2, 114, 27));
            this.addSlotToContainer(new SlotMilkWorkOut(inventory, 3, 133, 47));

            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 9; ++j) {
                    this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
            }

            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(player.inventory, k, 8 + k * 18, 142));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.world.equals(this.world) && playerIn.getDistanceSq(this.pos) <= 64.0;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2 || index == 3) {
                if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (TileEntityMilkWorkshop.isMaterials(itemstack1, 0)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (TileEntityMilkWorkshop.isMaterials(itemstack1, 1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 4 && index < 31) {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    //CompressorProgress

    public int getCompressorProgress() {
        return this.compressorProgress;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        TileEntity tileEntity = world.getTileEntity(this.pos);
        if (tileEntity instanceof TileEntityMilkWorkshop) {
            int compressorProgress = ((TileEntityMilkWorkshop) tileEntity).getCompressorProgress();
            if (compressorProgress != this.compressorProgress) {
                this.compressorProgress = compressorProgress;
                for (IContainerListener listener : this.listeners)
                    listener.sendWindowProperty(this, 0, compressorProgress);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        if (id == 0) this.compressorProgress = data;
    }

}
