package com.momo.morecows.entity.creatures.render;


import com.momo.morecows.entity.creatures.misc.EntitySkeletonCow;
import com.momo.morecows.util.Reference;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletonCow extends RenderLiving<EntitySkeletonCow> {

    private static final ResourceLocation SKELETON_COW_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/skeleton_cow.png");

    public RenderSkeletonCow(RenderManager manager){ super(manager, new ModelCow(), 0.7f); }

    protected ResourceLocation getEntityTexture(EntitySkeletonCow entity) { return SKELETON_COW_TEXTURE; }
}

