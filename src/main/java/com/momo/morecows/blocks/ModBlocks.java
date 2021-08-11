package com.momo.morecows.blocks;

import com.momo.morecows.blocks.Machine.MachineBase;
import com.momo.morecows.blocks.Machine.MilkWorkshop;
import com.momo.morecows.blocks.blockBush.BlockPlant;
import com.momo.morecows.init.ModCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block BEEFROOT_BEETROOT = new BlockPlant("beefroot_beetroot", Material.PLANTS);
	public static final Block MILK_WORKSHOP = new MilkWorkshop("milk_workshop", Material.ROCK).setCreativeTab(ModCreativeTab.MORECOWS_TAB);
	
	/*
	 * To add a blockBush, put a line here,
	 * -Create a json at assets.eo.blockstates
	 * -Create a json at assets.eo.models.blockBush
	 * -Create a json at assets.eo.models.item
	 * -Add corresponding texture png
	 */

	//public static final Block GRID_BLOCK_1 = new BlockBase("test", Material.CLAY).setCreativeTab(ModCreativeTab.IDL_MISC).setHardness(15f);
}
