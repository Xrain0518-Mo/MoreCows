package com.momo.morecows.util.recipes;

import com.google.common.collect.Lists;
import com.momo.morecows.util.ItemStackUtil;
import com.momo.morecows.recipe.CheckRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipesManager {

    private static final List<CheckRecipe> milkWorkshopRecipes = new ArrayList<>();

    public static List<CheckRecipe> getMilkWorkshopRecipe() {
        return milkWorkshopRecipes;
    }

    public static void addMilkWorkshopRecipe(ItemStack inputA, ItemStack inputB, ItemStack outputA, ItemStack outputB, int progress) {
        addMilkWorkshopRecipe(Lists.newArrayList(inputA, inputB), Lists.newArrayList(outputA, outputB), progress);
    }

    public static void addMilkWorkshopRecipe(ItemStack inputA, ItemStack inputB, ItemStack outputA, ItemStack outputB) {
        addMilkWorkshopRecipe(Lists.newArrayList(inputA, inputB), Lists.newArrayList(outputA, outputB));
    }

    public static void addMilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs) {
        addMilkWorkshopRecipe(inputs, outputs, 200);
    }

    public static void addMilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs, int progress) {
        if (inputs.size() != 2 || outputs.size() != 2) {
            throw new IllegalArgumentException("recipe input or output size must be equal to 2");
        } else {
            CheckRecipe recipeTemp = new CheckRecipe(inputs, outputs, progress);
            if (checkMilkWorkshopRecipeRepeat(recipeTemp)) {
                milkWorkshopRecipes.add(recipeTemp);
            }
        }
    }

    // has output
    public static boolean checkMilkWorkshopRecipeRepeat(CheckRecipe recipeTemp) {
        return milkWorkshopRecipes.stream().noneMatch(recipeTemp::equals);
    }

    // don't output
    public static CheckRecipe getMilkWorkshopRecipe(CheckRecipe recipeTemp) {
        for (CheckRecipe recipe : milkWorkshopRecipes) {
            if (recipeTemp.getOutputs().get(0) != ItemStack.EMPTY || recipeTemp.getOutputs().get(1) != ItemStack.EMPTY) {
                if (!checkMilkWorkshopRecipeRepeat(recipeTemp)) {
                    return recipe; // fix when current recipe has output, but input was another recipe
                }
            } else if (ItemStackUtil.areItemStackListEqual(recipe.getInputs(), recipeTemp.getInputs())) {
                return recipe;
            }
        }
        return null;
    }

}
