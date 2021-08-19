package com.momo.morecows.blocks.Machine;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.entity.tiles.blockTiles.TileEntityMilkWorkshop;
import com.momo.morecows.network.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class MilkWorkshop extends MachineBase{
    public MilkWorkshop(String name, Material material)
    {
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

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityMilkWorkshop();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

        IItemHandler materialIn = tileEntity.getCapability(itemHandlerCapability, EnumFacing.UP);
        IItemHandler milkIn = tileEntity.getCapability(itemHandlerCapability, EnumFacing.NORTH);
        IItemHandler bucketOut = tileEntity.getCapability(itemHandlerCapability, EnumFacing.DOWN);
        IItemHandler materialOut = tileEntity.getCapability(itemHandlerCapability, EnumFacing.SOUTH);

        Block.spawnAsEntity(worldIn, pos, materialIn.getStackInSlot(0));
        Block.spawnAsEntity(worldIn, pos, milkIn.getStackInSlot(0));
        Block.spawnAsEntity(worldIn, pos, bucketOut.getStackInSlot(0));
        Block.spawnAsEntity(worldIn, pos, materialOut.getStackInSlot(0));

        super.breakBlock(worldIn, pos, state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels()
    {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().build());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(this), 0, TileEntityMilkWorkshop.class);
    }
}
