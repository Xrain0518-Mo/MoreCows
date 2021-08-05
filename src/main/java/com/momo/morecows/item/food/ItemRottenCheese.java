package com.momo.morecows.item.food;

import com.momo.morecows.util.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;


public class ItemRottenCheese extends ItemFoodBase implements IHasModel {
    public ItemRottenCheese(String name, int amount, float saturation, boolean isWolfFood){
        super(name, amount,saturation, isWolfFood);
        setMaxStackSize(16);
        setAlwaysEdible();
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote)
        {
            ItemDrinkBase.addDuration(entityLiving);

            entityLiving.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 60*20, 0, false, true));
        }

        super.onItemUseFinish(stack, worldIn, entityLiving);

        return stack;
    }
}
