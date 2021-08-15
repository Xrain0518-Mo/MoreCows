package com.momo.morecows.entity;

import com.momo.morecows.entity.creatures.misc.*;
import com.momo.morecows.entity.creatures.render.*;
import com.momo.morecows.entity.projectiles.EntityMilkBall;
import com.momo.morecows.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {

        RenderingRegistry.registerEntityRenderingHandler(EntityLavaCow.class, new IRenderFactory<EntityLavaCow>() {
            @Override
            public Render<? super EntityLavaCow> createRenderFor(RenderManager manager) {
                return new RenderLavaCow(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterCow.class, new IRenderFactory<EntityWaterCow>() {
            @Override
            public Render<? super EntityWaterCow> createRenderFor(RenderManager manager) {
                return new RenderWaterCow(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBeefroot.class, new IRenderFactory<EntityBeefroot>() {
            @Override
            public Render<? super EntityBeefroot> createRenderFor(RenderManager manager) {
                return new RenderBeefroot(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityZombieCow.class, new IRenderFactory<EntityZombieCow>() {
            @Override
            public Render<? super EntityZombieCow> createRenderFor(RenderManager manager) {
                return new RenderZombieCow(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCow.class, new IRenderFactory<EntitySkeletonCow>() {
            @Override
            public Render<? super EntitySkeletonCow> createRenderFor(RenderManager manager) {
                return new RenderSkeletonCow(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityMilkBall.class, manager ->
        {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityMilkBall>(manager, ModItems.MILK_BALL, renderItem);
        });
    }
}
