package com.momo.morecows.entity.creatures.render;


import com.momo.morecows.entity.creatures.misc.EntityZombieCow;
import com.momo.morecows.util.Reference;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieCow extends RenderLiving<EntityZombieCow> {

    private static final ResourceLocation ZOMBIE_COW_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/zombie_cow.png");

    public RenderZombieCow(RenderManager manager){ super(manager, new ModelCow(), 0.7f); }

    protected ResourceLocation getEntityTexture(EntityZombieCow entity)
    {
        return ZOMBIE_COW_TEXTURE;
    }
}

