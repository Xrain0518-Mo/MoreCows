package com.momo.morecows.entity.tiles;

import com.momo.morecows.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMilkBall extends EntityThrowable {
    public static final String ID = "milk_ball";
    public static final String NAME = "MilkBall";

    public EntityMilkBall(World worldIn)
    {
        super(worldIn);
    }

    public EntityMilkBall(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public static void registerFixesMilkBall(DataFixer fixer) { EntityThrowable.registerFixesThrowable(fixer, "milk_ball"); }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, Item.getIdFromItem(ModItems.MILK_BALL));
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if(!this.world.isRemote)
        {
            if(result.entityHit != null){
                float amount = 0F;
                if(result.entityHit instanceof EntityLivingBase)
                {
                    ((EntityLivingBase)result.entityHit).curePotionEffects(new ItemStack(Items.MILK_BUCKET));
                }
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), amount);
            }
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
