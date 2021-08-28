package com.momo.morecows.events;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.enchantments.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class EnchantmentsEvent {
    @SubscribeEvent
    public static void holyStrike(LivingHurtEvent event){
        Entity source = event.getSource().getTrueSource();
        if(source instanceof EntityPlayer && !source.world.isRemote){
            EntityPlayer player = (EntityPlayer) source;
            ItemStack heldItemMainhand = player.getHeldItemMainhand();
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.HOLY_STRIKE, heldItemMainhand);
            if(level > 0){
                Entity target = event.getEntity();
                if(target instanceof EntityLivingBase) ((EntityLivingBase) target).curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            }
        }
    }
}
