package com.momo.morecows.entity;

import com.momo.morecows.entity.creatures.misc.EntityBeefroot;
import com.momo.morecows.entity.creatures.misc.EntityLavaCow;
import com.momo.morecows.entity.creatures.misc.EntityWaterCow;
import com.momo.morecows.entity.creatures.misc.EntityZombieCow;
import com.momo.morecows.util.Reference;
import com.momo.morecows.IdlFramework;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;
    public static void registerEntities()
    {
        //Examples
//        registerEntity("moroon_orbital_beacon", EntityMoroonBombBeacon.class);
//        registerEntity("idealland_whitetower_core", EntityIDLWhiteTowerCore.class, ENTITY_NEXT_ID, 128, 0xeeee00, 0xffffff);
        registerEntity("lava_cow", EntityLavaCow.class,0x800000, 0xff4500);
        registerEntity("water_cow", EntityWaterCow.class,0x00008b, 0x87CEFA);
        registerEntity("beefroot", EntityBeefroot.class,0x8B2323, 0x808080);
        registerEntity("zombie_cow", EntityZombieCow.class,0x8B2323, 0x2E8B57);

        //the bullet
        //registerEntity("bullet", EntityIdlProjectile.class);



        DataFixer datafixer = new DataFixer(1343);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, 0xff00ff, 0x000000);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int color1, int color2)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, color1, color2);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity,
                name,
                id,
                IdlFramework.instance,
                range,
                1,
                true,
                color1, color2
                );
        ENTITY_NEXT_ID++;
    }
}
