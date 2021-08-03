package com.momo.morecows.events;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.entity.creatures.misc.EntityLavaCow;
import com.momo.morecows.entity.creatures.misc.EntityWaterCow;
import com.momo.morecows.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class InstantGrowth {
    @SubscribeEvent
    public static void Growth(PlayerInteractEvent.EntityInteractSpecific evt) {
        EntityPlayer player = evt.getEntityPlayer();
        Entity target = evt.getTarget();
        EnumHand hand = evt.getHand();

        ItemStack itemstack = player.getHeldItem(hand);

        if (target instanceof EntityCow) {
            if (itemstack.getItem() == ModItems.GOLDEN_WHEAT && ((EntityAnimal) target).isChild() && !target.world.isRemote){
                itemstack.shrink(1);
                ((EntityAnimal) target).setGrowingAge(1);
                ((net.minecraft.world.WorldServer) target.world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, false, target.posX, target.posY + (double) (target.height), target.posZ, 5, 0.15D, 0.15D, 0.15D, 0.0D);
            }
        }
    }
}
