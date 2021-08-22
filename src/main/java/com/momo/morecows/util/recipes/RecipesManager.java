package com.momo.morecows.util.recipes;

import com.google.common.collect.Lists;
import com.momo.morecows.util.ItemStackUtil;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipesManager {

    private static final List<MilkWorkshopRecipe> milkWorkshopRecipes = new ArrayList<>();

    public static List<MilkWorkshopRecipe> getMilkWorkshopRecipe() {
        return milkWorkshopRecipes;
    }

    public static void addMilkWorkshopRecipe(ItemStack inputUp, ItemStack inputDown, ItemStack outputA, ItemStack outputB, int progress) {
        addMilkWorkshopRecipe(Lists.newArrayList(inputUp, inputDown), Lists.newArrayList(outputA, outputB), progress);
    }

    public static void addMilkWorkshopRecipe(ItemStack inputUp, ItemStack inputDown, ItemStack outputA, ItemStack outputB) {
        addMilkWorkshopRecipe(Lists.newArrayList(inputUp, inputDown), Lists.newArrayList(outputA, outputB));
    }

    public static void addMilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs) {
        addMilkWorkshopRecipe(inputs, outputs, 200);
    }

    public static void addMilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs, int progress) {
        if (inputs.size() != 2 || outputs.size() != 2) {
            throw new IllegalArgumentException("recipe input or output size must be equal to 2");
        } else {
            MilkWorkshopRecipe recipeTemp = new MilkWorkshopRecipe(inputs, outputs, progress);
            if (checkMilkWorkshopRecipeRepeat(recipeTemp)) {
                milkWorkshopRecipes.add(recipeTemp);
            }
        }
    }

    // has output
    public static boolean checkMilkWorkshopRecipeRepeat(MilkWorkshopRecipe recipeTemp) {
        return milkWorkshopRecipes.stream().noneMatch(recipeTemp::equals);
    }

    // don't output
    public static MilkWorkshopRecipe getMilkWorkshopRecipe(MilkWorkshopRecipe recipeTemp) {
        for (MilkWorkshopRecipe recipe : milkWorkshopRecipes) {
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
