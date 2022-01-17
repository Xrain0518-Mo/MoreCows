package com.momo.morecows.entity.creatures.render;


import com.momo.morecows.entity.creatures.misc.EntityLavaCow;
import com.momo.morecows.util.Reference;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLavaCow extends RenderLiving<EntityLavaCow> {

    private static final ResourceLocation LAVA_COW_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lava_cow.png");

    public RenderLavaCow(RenderManager manager){ super(manager, new ModelCow(), 0.7f); }

    protected ResourceLocation getEntityTexture(EntityLavaCow entity)
    {
        return LAVA_COW_TEXTURE;
    }
}

