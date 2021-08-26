package com.momo.morecows.recipe;

import com.momo.morecows.util.ItemStackUtil;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CheckRecipe {

    private final List<ItemStack> inputs;
    private final List<ItemStack> outputs;
    private final int progress;

    public CheckRecipe(List<ItemStack> inputs, List<ItemStack> outputs, int progress) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.progress = progress;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public List<ItemStack> getOutputs() { return outputs; }

    public int getProgress() {
        return progress;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CheckRecipe that = (CheckRecipe) object;

        return ItemStackUtil.areItemStackListEqual(that.getOutputs(), getOutputs()) && ItemStackUtil.areItemStackListEqual(that.getInputs(), getInputs());
    }
}
