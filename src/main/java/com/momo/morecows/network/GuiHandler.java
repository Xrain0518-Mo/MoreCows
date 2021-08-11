package com.momo.morecows.network;

import com.momo.morecows.network.MilkWorkshop.ContainerMilkWorkshop;
import com.momo.morecows.network.MilkWorkshop.GuiMilkWorkshop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int MILK_WORKSHOP = 1;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
        if(id == MILK_WORKSHOP) return new ContainerMilkWorkshop(player, world, x, y, z);
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
        if(id == MILK_WORKSHOP) return new GuiMilkWorkshop(player, world, x, y, z);
        return null;
    }



}
