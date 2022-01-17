package com.momo.morecows.network.MilkWorkshop;

import com.momo.morecows.entity.tiles.blockTiles.TileEntityMilkWorkshop;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMilkWorkMilkIn extends SlotItemHandler {

    public SlotMilkWorkMilkIn(IItemHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) { return TileEntityMilkWorkshop.isMaterials(stack, 1); }
}
