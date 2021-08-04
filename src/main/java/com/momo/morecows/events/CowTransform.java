package com.momo.morecows.events;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.entity.creatures.misc.*;
import com.momo.morecows.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class CowTransform  {

    @SubscribeEvent
    public static void selTransform(PlayerInteractEvent.EntityInteractSpecific evt){
        EntityPlayer player= evt.getEntityPlayer();
        Entity target = evt.getTarget();
        EnumHand hand = evt.getHand();
        ItemStack itemstack = player.getHeldItem(hand);

        if(target instanceof EntityCow && !((EntityCow) target).isChild() && !target.world.isRemote)
        {
            if (!(target instanceof EntityModCow) && !(target instanceof EntityMooshroom)) {
                //lava cow
                if (itemstack.getItem() == ModItems.CONDENSED_LAVA_WHEAT)
                {
                    if (!player.capabilities.isCreativeMode) { itemstack.shrink(1); }

                    transform(target, new EntityLavaCow(target.world));

                    target.playSound(SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0F, 1.0F);
                    ((net.minecraft.world.WorldServer) target.world).spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, false, target.posX, target.posY + (double) (target.height / 2.0F), target.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                }

                //water cow
                if (itemstack.getItem() == ModItems.CONDENSED_WATER_WHEAT)
                {
                    if (!player.capabilities.isCreativeMode) { itemstack.shrink(1); }

                    transform(target, new EntityWaterCow(target.world));

                    target.playSound(SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0F, 1.0F);
                    ((net.minecraft.world.WorldServer) target.world).spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, false, target.posX, target.posY + (double) (target.height / 2.0F), target.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                }

                //mooshroom
                if (itemstack.getItem() == ModItems.CONDENSED_MUSHROOM_WHEAT)
                {
                    if (!player.capabilities.isCreativeMode) { itemstack.shrink(1); }

                    transform(target, new EntityMooshroom(target.world));

                    target.playSound(SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0F, 1.0F);
                    ((net.minecraft.world.WorldServer) target.world).spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, false, target.posX, target.posY + (double) (target.height / 2.0F), target.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                }

                //beefroot
                if (itemstack.getItem() == ModItems.CONDENSED_BEETROOT_WHEAT)
                {
                    if (!player.capabilities.isCreativeMode) { itemstack.shrink(1); }

                    transform(target, new EntityBeefroot(target.world));

                    target.playSound(SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0F, 1.0F);
                    ((net.minecraft.world.WorldServer) target.world).spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, false, target.posX, target.posY + (double) (target.height / 2.0F), target.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                }
            }

            //ZombieCow
            if(!(target instanceof EntityZombieCow) && !(target instanceof EntitySkeletonCow)) {
                if (itemstack.getItem() == ModItems.CONDENSED_ROTTEN_WHEAT) {

                    if (!player.capabilities.isCreativeMode) { itemstack.shrink(1); }

                    transform(target, new EntityZombieCow(target.world));
                    target.world.addWeatherEffect(new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, true));
                }
            }

            //SkeletonCow
            if(!(target instanceof EntitySkeletonCow)) {
                if (itemstack.getItem() == ModItems.CONDENSED_BONE_WHEAT) {
                    if (!player.capabilities.isCreativeMode) { itemstack.shrink(1); }

                    transform(target, new EntitySkeletonCow(target.world));
                    target.world.addWeatherEffect(new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, true));
                }
            }
        }
    }

    private static void transform(Entity target, EntityCow newborn)
    {
        newborn.setLocationAndAngles(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
        newborn.setHealth(((EntityCow) target).getHealth());
        newborn.renderYawOffset = ((EntityCow) target).renderYawOffset;

        if (target.hasCustomName())
        {
            newborn.setCustomNameTag(target.getCustomNameTag());
        }

        target.world.spawnEntity(newborn);
        target.setDead();
    }
}
