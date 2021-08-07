package com.momo.morecows.potion;

public class InstantPotion extends PotionBase {
    public InstantPotion(String name, boolean isBadEffect, int color)
    {
        super(name, isBadEffect, color);
    }

    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    public boolean isInstant()
    {
        return true;
    }

    /**
     * checks if Potion effect is ready to be applied this tick.
     */
    public boolean isReady(int duration, int amplifier)
    {
        return duration >= 1;
    }
}
