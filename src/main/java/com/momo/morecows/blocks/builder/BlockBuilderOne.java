package com.momo.morecows.blocks.builder;

import com.momo.morecows.blocks.BlockBase;
import com.momo.morecows.init.ModCreativeTab;
import com.momo.morecows.blocks.tileEntity.builder.TileEntityBuilderOne;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuilderOne extends BlockBase implements ITileEntityProvider {

    public BlockBuilderOne(String name, Material material) {
        super(name, material);
        setCreativeTab(ModCreativeTab.MORECOWS_TAB);
        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(15.0F);
        setHarvestLevel("pickaxe", 3);
        setLightOpacity(1);
    }

    /**
     * Returns a new instance of a blockBush's tile entity class. Called on placing the blockBush.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntityBuilderOne t = new TileEntityBuilderOne();
        t.buildRatePerTick = 1f;
        return t;
    }
}
