package com.momo.morecows.util.recipes;

import com.google.common.collect.Lists;
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

    public static void addMilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs, int progress) {
        if (inputs.size() != 2 || outputs.size() != 2) {
           throw new IllegalArgumentException();
        } else {
            MilkWorkshopRecipe recipeTemp = new MilkWorkshopRecipe(inputs, outputs, progress);
            if (getMilkWorkshopRecipe(recipeTemp) == null) {
                milkWorkshopRecipes.add(recipeTemp);
            }
        }
    }

    public static void addMilkWorkshopRecipe(List<ItemStack> inputs, List<ItemStack> outputs) {
        addMilkWorkshopRecipe(inputs, outputs, 200);
    }

    public static MilkWorkshopRecipe getMilkWorkshopRecipe(MilkWorkshopRecipe recipeTemp) {
        for (MilkWorkshopRecipe recipe : milkWorkshopRecipes) {
            if (recipe.equals(recipeTemp)) {
                return recipe;
            }
        }
        return null;
    }

}
