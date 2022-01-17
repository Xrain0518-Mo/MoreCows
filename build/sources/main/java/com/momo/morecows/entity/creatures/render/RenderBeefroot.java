package com.momo.morecows.entity.creatures.render;


import com.momo.morecows.entity.creatures.misc.EntityBeefroot;
import com.momo.morecows.entity.creatures.misc.EntityLavaCow;
import com.momo.morecows.util.Reference;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBeefroot extends RenderLiving<EntityBeefroot> {

    private static final ResourceLocation BEEFROOT_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/beefroot.png");

    public RenderBeefroot(RenderManager manager)
    {
        super(manager, new ModelCow(), 0.7f);
        this.addLayer(new LayerBeefrootBeetroot(this));
    }

    public ModelCow getMainModel()
    {
        return (ModelCow)super.getMainModel();
    }

    protected ResourceLocation getEntityTexture(EntityBeefroot entity)
    {
        return BEEFROOT_TEXTURE;
    }
}

