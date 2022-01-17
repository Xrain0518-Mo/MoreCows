package com.momo.morecows.entity.creatures.misc;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class EntitySkeletonCow extends EntityModCow
{

    public EntitySkeletonCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Nullable
    protected ResourceLocation getLootTable() { return LootTableList.ENTITIES_SKELETON_HORSE; }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        return super.processInteract(player, hand);
    }

    public EntitySkeletonCow createChild(EntityAgeable ageable) { return null; }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return false;
    }

    public boolean isBreedingItem(ItemStack stack) { return false;}
}
