package com.momo.morecows.events;

import com.momo.morecows.IdlFramework;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class AddedEntityDrop {

    @SubscribeEvent
    public static void MobDrop(LivingDropsEvent evt) {
        EntityLivingBase deathOne = evt.getEntityLiving();

        if (deathOne.isChild()) {
            return;
        }

    }
}