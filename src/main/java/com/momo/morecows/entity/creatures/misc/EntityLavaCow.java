package com.momo.morecows.entity.creatures.misc;

import com.google.common.collect.Sets;
import com.momo.morecows.init.ModLootList;
import com.momo.morecows.item.ModItems;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityLavaCow extends EntityModCow
{
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(ModItems.LAVA_WHEAT);

    public EntityLavaCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.isImmuneToFire = true;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, ModItems.LAVA_WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return ModLootList.ENTITY_LAVA_COW;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            player.playSound(SoundEvents.ITEM_BUCKET_FILL_LAVA, 1.0F, 1.0F);
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(Items.LAVA_BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET)))
            {
                player.dropItem(new ItemStack(Items.LAVA_BUCKET), false);
            }

            return true;
        }

        if (itemstack.getItem() == Items.WATER_BUCKET && !player.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(Items.BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
            {
                player.dropItem(new ItemStack(Items.BUCKET), false);
            }

            this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH,1.0F, 1.0F);

            for (int k = 0; k < 20; ++k)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            }

            onKillCommand();

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    public EntityLavaCow createChild(EntityAgeable ageable)
    {
        return new EntityLavaCow(this.world);
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.isInWater()) { this.attackEntityFrom(DamageSource.DROWN, 4.0F); }

    }

    public boolean isBreedingItem(ItemStack stack) { return TEMPTATION_ITEMS.contains(stack.getItem());}

}
