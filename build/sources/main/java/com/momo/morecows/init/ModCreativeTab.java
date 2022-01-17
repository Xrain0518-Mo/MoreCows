package com.momo.morecows.init;

import com.momo.morecows.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab {
	public static final CreativeTabs MORECOWS_TAB = new CreativeTabs(CreativeTabs.getNextID(), "morecows")
	{
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.GOLDEN_WHEAT);
        }
    };

}
