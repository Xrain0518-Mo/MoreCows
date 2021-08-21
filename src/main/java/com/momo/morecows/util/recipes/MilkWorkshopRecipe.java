package com.momo.morecows.util.recipes;

import com.momo.morecows.util.ItemStackUtil;
import net.minecraft.item.ItemStack;

import java.util.List;

public class MilkWorkshopRecipe {

    private final List<ItemStack> inputs;
    private final List<ItemStack> outputs;
    private final int progress;

    public MilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs, int progress) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.progress = progress;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    public int getProgress() {
        return progress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MilkWorkshopRecipe that = (MilkWorkshopRecipe) o;

        return ItemStackUtil.areItemStackListEqual(that.getOutputs(), getOutputs()) || ItemStackUtil.areItemStackListEqual(that.getInputs(), getInputs());
    }
}
