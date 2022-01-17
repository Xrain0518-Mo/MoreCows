package com.momo.morecows.item.potions;


import com.momo.morecows.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static com.momo.morecows.potion.ModPotion.DELAYED;
import static com.momo.morecows.potion.ModPotion.PURIFY;

@Mod.EventBusSubscriber
public class ModPotionType {
    public static final List<PotionType> POTION_TYPES = new ArrayList<>();

    public static final PotionType POTION_TYPE_DELAYED = new PotionTypeBase("delayed", new PotionEffect(DELAYED, 1)).setRegistryName("delayed");
    public static final PotionType POTION_TYPE_LONG_DELAYED = new PotionTypeBase("delayed", new PotionEffect(DELAYED, 1, 1)).setRegistryName("long_delayed");

    public static final PotionType POTION_TYPE_PURIFY = new PotionTypeBase("purify", new PotionEffect(PURIFY, 1)).setRegistryName("purify");
    public static final PotionType POTION_TYPE_STRONG_PURIFY = new PotionTypeBase("purify", new PotionEffect(PURIFY, 1, 1)).setRegistryName("strong_purify");

    public static void register()
    {
        //delayed
        PotionHelper.addMix(POTION_TYPE_DELAYED, Items.GLOWSTONE_DUST, POTION_TYPE_LONG_DELAYED);
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.ROTTEN_CREAMER, POTION_TYPE_DELAYED);

        //purify
        PotionHelper.addMix(POTION_TYPE_PURIFY, Items.GLOWSTONE_DUST, POTION_TYPE_STRONG_PURIFY);
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.CREAMER, POTION_TYPE_PURIFY);
    }
}
