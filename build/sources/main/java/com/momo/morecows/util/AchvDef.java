package com.momo.morecows.util;

import static com.momo.morecows.util.Reference.MOD_ID;

public class AchvDef {
    public final static String ELK_TRANSFORM = "elk_transform";
    public final static String GetAchvName(String key)
    {
        return MOD_ID + ":" + key;
    }
}
