package com.momo.morecows.entity.creatures.misc;

import com.momo.morecows.init.ModLootList;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityBeefroot extends EntityModCow
{
    public EntityBeefroot(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BOWL && this.getGrowingAge() >= 0 && !player.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(Items.BEETROOT_SOUP));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.BEETROOT_SOUP)))
            {
                player.dropItem(new ItemStack(Items.BEETROOT_SOUP), false);
            }

            return true;
        }
        else if (itemstack.getItem() == Items.SHEARS && this.getGrowingAge() >= 0)
        {
            this.setDead();
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY + (double)(this.height / 2.0F), this.posZ, 0.0D, 0.0D, 0.0D);

            if (!this.world.isRemote)
            {
                EntityCow entitycow = new EntityCow(this.world);
                entitycow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
                entitycow.setHealth(this.getHealth());
                entitycow.renderYawOffset = this.renderYawOffset;

                if (this.hasCustomName())
                {
                    entitycow.setCustomNameTag(this.getCustomNameTag());
                }

                this.world.spawnEntity(entitycow);

                for (int i = 0; i < 3; ++i)
                {
                    this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY + (double)this.height, this.posZ, new ItemStack(Items.BEETROOT)));
                    this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY + (double)this.height, this.posZ, new ItemStack(Items.BEETROOT_SEEDS)));
                }

                itemstack.damageItem(1, player);
                this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    public EntityBeefroot createChild(EntityAgeable ageable)
    {
        return new EntityBeefroot(this.world);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return ModLootList.ENTITY_BEETROOT_COW;
    }
}
