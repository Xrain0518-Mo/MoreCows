package com.momo.morecows.network.MilkWorkshop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerMilkWorkshop extends Container {
    private final World world;
    private final BlockPos pos;

    private final IItemHandler up;
    private final IItemHandler down;
    private final IItemHandler east;
    private final IItemHandler south;
    private final IItemHandler north;
    private final IItemHandler west;

    public ContainerMilkWorkshop(EntityPlayer player, World world, int x, int y, int z){
        this.world = world;
        this.pos = new BlockPos(x, y, z);

        TileEntity tileEntity = world.getTileEntity(this.pos);
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

        this.up = tileEntity.getCapability(itemHandlerCapability, EnumFacing.UP);
        this.down = tileEntity.getCapability(itemHandlerCapability, EnumFacing.DOWN);
        this.east = tileEntity.getCapability(itemHandlerCapability, EnumFacing.EAST);
        this.west = tileEntity.getCapability(itemHandlerCapability, EnumFacing.WEST);
        this.north = tileEntity.getCapability(itemHandlerCapability, EnumFacing.NORTH);
        this.south = tileEntity.getCapability(itemHandlerCapability, EnumFacing.SOUTH);

        int[] range = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};

        for(int i : range)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + 18 * i, 142));
            this.addSlotToContainer(new Slot(player.inventory, i + 9, 8 + 18 * i, 84));
            this.addSlotToContainer(new Slot(player.inventory, i + 19, 8 + 18 * i, 102));
            this.addSlotToContainer(new Slot(player.inventory, i + 27, 8 + 18 * i, 120));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return playerIn.world.equals(this.world) && playerIn.getDistanceSq(this.pos) <= 64.0;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
        return ItemStack.EMPTY;
    }
}
