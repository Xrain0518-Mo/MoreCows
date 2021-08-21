package com.momo.morecows.blocks.Machine;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.entity.tiles.blockTiles.TileEntityMilkWorkshop;
import com.momo.morecows.network.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

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
        final TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityMilkWorkshop){
            final ItemStackHandler inventory = ((TileEntityMilkWorkshop)tileEntity).inventory;

            for (int i = 0; i < inventory.getSlots(); i++) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
            }
        }
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
