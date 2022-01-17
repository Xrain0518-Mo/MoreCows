package com.momo.morecows.entity.creatures.misc;

import com.momo.morecows.item.ModItems;
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

public class EntityZombieCow extends EntityModCow
{

    public EntityZombieCow(World worldIn)
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
    protected ResourceLocation getLootTable() { return LootTableList.ENTITIES_ZOMBIE_HORSE; }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(ModItems.ROTTEN_MILK));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.ROTTEN_MILK)))
            {
                player.dropItem(new ItemStack(ModItems.ROTTEN_MILK), false);
            }

            return true;
        }

        else
        {
            return super.processInteract(player, hand);
        }
    }

    public EntityZombieCow createChild(EntityAgeable ageable) { return null; }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return false;
    }

    public boolean isBreedingItem(ItemStack stack) { return false;}
}
