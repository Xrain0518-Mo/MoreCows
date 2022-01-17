package com.momo.morecows.events;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.enchantments.ModEnchantments;
import com.momo.morecows.util.EntityUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class EnchantmentsEvent {
    @SubscribeEvent
    public static void livingHurt(LivingHurtEvent event){
        Entity source = event.getSource().getTrueSource();
        Entity target = event.getEntity();

        //player hurt entity
        if(source instanceof EntityPlayer && !source.world.isRemote){
            EntityPlayer player = (EntityPlayer) source;
            ItemStack heldItemMainhand = player.getHeldItemMainhand();
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.HOLY_STRIKE, heldItemMainhand);
            if(level > 0){
                if(target instanceof EntityLivingBase) ((EntityLivingBase) target).curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            }
        }
        //player being hurt
    }

    @SubscribeEvent
    public static void playerUpdate(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        if(!player.world.isRemote){
            ItemStack chestArmor = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.HOLY, chestArmor);
            if(level > 0){
                EntityUtil.TryRemoveDebuff(player);
            }
        }
    }
}
