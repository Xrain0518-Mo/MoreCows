package com.momo.morecows.potion;

import net.minecraft.potion.Potion;

import java.util.ArrayList;
import java.util.List;

public class ModPotion {

    public static final List<Potion> POTIONS = new ArrayList<>();

    public static final Potion DELAYED = new InstantPotion("delayed", false , 0xFFF8DC);
    public static final Potion PURIFY = new InstantPotion("purify", false, 0xF8F8FF);
}
