package com.momo.morecows.recipe;

import com.momo.morecows.item.ModItems;
import com.momo.morecows.util.recipes.RecipesManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MilkWorkshop {
    public static void BaseMilkWorkshopRecipes() {
        RecipesManager.addMilkWorkshopRecipe(new ItemStack(Items.SUGAR), new ItemStack(Items.MILK_BUCKET)
                , new ItemStack(ModItems.CHEESE), new ItemStack(Items.BUCKET));

        RecipesManager.addMilkWorkshopRecipe(new ItemStack(Items.SUGAR), new ItemStack(ModItems.ROTTEN_MILK)
                , new ItemStack(ModItems.ROTTEN_CHEESE), new ItemStack(Items.BUCKET));

        RecipesManager.addMilkWorkshopRecipe(new ItemStack(Items.SNOWBALL), new ItemStack(Items.MILK_BUCKET)
                , new ItemStack(ModItems.MILK_BALL), new ItemStack(Items.BUCKET));

        RecipesManager.addMilkWorkshopRecipe(new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(ModItems.CHEESE)
                , new ItemStack(ModItems.CREAMER), ItemStack.EMPTY);

        RecipesManager.addMilkWorkshopRecipe(new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(ModItems.ROTTEN_CHEESE)
                , new ItemStack(ModItems.ROTTEN_CREAMER), ItemStack.EMPTY);
    }
}
