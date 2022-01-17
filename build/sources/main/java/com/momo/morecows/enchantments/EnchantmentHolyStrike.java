package com.momo.morecows.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentHolyStrike extends ModEnchantmentBase {

    public EnchantmentHolyStrike() {
        super("holy_strike", Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        setMaxLevel(1);
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench){
        return super.canApplyTogether(ench) && Enchantments.SWEEPING != ench;
    }
}
