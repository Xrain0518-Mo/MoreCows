package com.momo.morecows.network.MilkWorkshop;

import com.momo.morecows.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMilkWorkMilkIn extends SlotItemHandler {

    public SlotMilkWorkMilkIn(IItemHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) { return isMilk(stack); }

    public boolean isMilk(ItemStack stack)
    {
        Item item = stack.getItem();
        return (item == Items.MILK_BUCKET || item == ModItems.ROTTEN_MILK);
    }
}
