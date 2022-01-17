package com.momo.morecows.util;

import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemStackUtil {

    // don't matches amount
    public static boolean areItemStackEqual(ItemStack stackA, ItemStack stackB) {
        if (stackA.isEmpty() && stackB.isEmpty()) {
            return true;
        } else {
            return isItemStackEqual(stackA, stackB);
        }
    }

    public static boolean isItemStackEqual(ItemStack stackA, ItemStack stackB) {
        if (stackA.getItem() != stackB.getItem()) {
            return false;
        } else if (stackA.getItemDamage() != stackB.getItemDamage()) {
            return false;
        } else if (stackA.getTagCompound() == null && stackB.getTagCompound() != null) {
            return false;
        } else {
            return (stackA.getTagCompound() == null || stackB.getTagCompound() == null || stackA.getTagCompound().equals(stackB.getTagCompound())) && stackA.areCapsCompatible(stackB);
        }
    }

    public static boolean areItemStackListEqual(List<ItemStack> stacksA, List<ItemStack> stacksB) {
        if (stacksA.isEmpty() || stacksB.isEmpty() || stacksA.size() != stacksB.size()) {
            return false;
        } else {
            for (int i = 0; i < stacksA.size(); i++) {
                if (!areItemStackEqual(stacksA.get(i), stacksB.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}
