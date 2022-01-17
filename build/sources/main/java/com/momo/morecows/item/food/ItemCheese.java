package com.momo.morecows.item.food;

import com.momo.morecows.util.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCheese extends ItemFoodBase implements IHasModel {

    public ItemCheese(String name, int amount, float saturation, boolean isWolfFood){
        super(name, amount,saturation, isWolfFood);
        setMaxStackSize(16);
        setAlwaysEdible();
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote) entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

        super.onItemUseFinish(stack, worldIn, entityLiving);

        return stack;
    }
}
