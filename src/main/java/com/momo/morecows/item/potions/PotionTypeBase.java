package com.momo.morecows.item.potions;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

import javax.annotation.Nullable;

public class PotionTypeBase extends PotionType {
    public PotionTypeBase(@Nullable String name, PotionEffect... potionEffects)
    {
        super(name, potionEffects);

        ModPotionType.POTION_TYPES.add(this);
    }
}
