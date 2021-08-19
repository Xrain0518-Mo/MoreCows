package com.momo.morecows.entity;

import com.momo.morecows.entity.creatures.misc.*;
import com.momo.morecows.entity.tiles.EntityMilkBall;
import com.momo.morecows.util.Reference;
import com.momo.morecows.IdlFramework;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;
    public static void registerEntities()
    {
        //Examples
//        registerEntity("idealland_whitetower_core", EntityIDLWhiteTowerCore.class, ENTITY_NEXT_ID, 128, 0xeeee00, 0xffffff);
        registerEntity("lava_cow", EntityLavaCow.class,0x800000, 0xff4500);
        registerEntity("water_cow", EntityWaterCow.class,0x00008b, 0x87CEFA);
        registerEntity("beefroot", EntityBeefroot.class,0x8B2323, 0x808080);
        registerEntity("zombie_cow", EntityZombieCow.class,0x363636, 0x2E8B57);
        registerEntity("skeleton_cow", EntitySkeletonCow.class,0x363636, 0xF5F5F5);

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
                entity, name, id, IdlFramework.instance, range, 1, true, color1, color2);
        ENTITY_NEXT_ID++;
    }

    //tiles registry

    public static final EntityEntry MILK_BALL =
            EntityEntryBuilder.create().entity(EntityMilkBall.class).id(EntityMilkBall.ID, 6).name(EntityMilkBall.NAME)
                    .tracker(64, 10, true).build();

    @SubscribeEvent
    public static void onProjectilesRegistry(RegistryEvent.Register<EntityEntry>event)
    {
        IForgeRegistry<EntityEntry> registry = event.getRegistry();
        registry.register(MILK_BALL);
    }
}
