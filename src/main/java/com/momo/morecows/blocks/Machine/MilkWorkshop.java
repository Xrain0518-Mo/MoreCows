package com.momo.morecows.blocks.Machine;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.network.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MilkWorkshop extends MachineBase{
    public MilkWorkshop(String name, Material material){
        super(name, material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            playerIn.openGui(IdlFramework.MODID, GuiHandler.MILK_WORKSHOP, worldIn, x, y, z);
        }
        return true;
    }
}
