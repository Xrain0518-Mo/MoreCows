package com.momo.morecows.potion;


import com.momo.morecows.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;
import java.util.Collection;

public class PotionBase extends Potion {


    public PotionBase(String name, boolean isBadEffect, int color) {
        super(isBadEffect, color);
        this.setRegistryName(name);
        this.setPotionName("effect." + name);

        ModPotion.POTIONS.add(this);
    }

    public boolean hasEffect(EntityLivingBase entity) {
        return hasEffect(entity, this);
    }

    public boolean hasEffect(EntityLivingBase entity, Potion potion) {
        return entity.getActivePotionEffect(potion) != null;
    }

    //continuousPotion Time trigger
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    //instantPotion
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health)
    {
        if(this == ModPotion.DELAYED)
        {
            entityLivingBaseIn.removePotionEffect(this);

            Collection<PotionEffect> activePotionEffects = entityLivingBaseIn.getActivePotionEffects();

            for (int i = 0; i < activePotionEffects.size(); i++) {
                PotionEffect effect = (PotionEffect)activePotionEffects.toArray()[i];
                entityLivingBaseIn.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration() + (120 + amplifier*180)*20, effect.getAmplifier(), effect.getIsAmbient(), true));
            }
        }

        else if(this == ModPotion.PURIFY)
        {
            for (int i = 0; i < 3 + amplifier*2; i++) EntityUtil.TryRemoveDebuff(entityLivingBaseIn);
        }
    }


    //continuousPotion
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {

    }


}
