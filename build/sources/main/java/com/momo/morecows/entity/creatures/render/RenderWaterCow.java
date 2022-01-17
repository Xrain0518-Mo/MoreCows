package com.momo.morecows.entity.creatures.render;



import com.momo.morecows.entity.creatures.misc.EntityWaterCow;
import com.momo.morecows.util.Reference;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWaterCow extends RenderLiving<EntityWaterCow> {

    private static final ResourceLocation WATER_COW_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/water_cow.png");

    public RenderWaterCow(RenderManager manager){ super(manager, new ModelCow(), 0.7f); }

    protected ResourceLocation getEntityTexture(EntityWaterCow entity)
    {
        return WATER_COW_TEXTURE;
    }
}

