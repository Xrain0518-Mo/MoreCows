package com.momo.morecows.network.MilkWorkshop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerMilkWorkshop extends Container {
    private final World world;
    private final BlockPos pos;

    public ContainerMilkWorkshop(EntityPlayer player, World world, int x, int y, int z){
        this.world = world;
        this.pos = new BlockPos(x, y, z);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return playerIn.world.equals(this.world) && playerIn.getDistanceSq(this.pos) <= 64.0;
    }
}
