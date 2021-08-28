package com.momo.morecows.entity.creatures.misc;

import com.google.common.collect.Sets;
import com.momo.morecows.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityWaterCow extends EntityModCow
{
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(ModItems.WATER_WHEAT);
    private static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);

    public EntityWaterCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, ModItems.WATER_WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COW;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET)))
            {
                player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
            }

            return true;
        }

        if (itemstack.getItem() == Items.LAVA_BUCKET && !player.capabilities.isCreativeMode)
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

            this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Blocks.OBSIDIAN)));
            onKillCommand();

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        growCrops(this.getEntityWorld(), new BlockPos(this.posX, this.posY, this.posZ));
        moistenFarmLand(this.getEntityWorld(), new BlockPos(this.posX, this.posY, this.posZ));

        if(this.isInWater()) { this.setAir(300); }
    }

    private void growCrops(World world, BlockPos pos) {
        if (!this.isChild()) {
            for (int x0 = -4; x0 <= 4; x0++) {
                for (int y0 = -4; y0 <= 4; y0++) {
                    for (int z0 = -4; z0 <= 4; z0++) {
                        int x = pos.getX() + x0;
                        int y = pos.getY() + y0;
                        int z = pos.getZ() + z0;

                        IBlockState CropState = world.getBlockState(new BlockPos(x, y, z));
                        Block cropBlock = CropState.getBlock();

                        if (cropBlock instanceof IPlantable || cropBlock instanceof IGrowable) {
                            world.scheduleBlockUpdate(new BlockPos(x, y, z), cropBlock, 70, 1);
                        }
                    }
                }
            }
        }
    }

    private void moistenFarmLand(World world, BlockPos pos){
        if(!this.isChild()){
            for (BlockPos.MutableBlockPos farmLandPos : BlockPos.getAllInBoxMutable(pos.add(-4, -2, -4), pos.add(4, 2, 4)))
            {
                if(world.getBlockState(farmLandPos).getBlock() instanceof BlockFarmland){
                    world.setBlockState(farmLandPos, world.getBlockState(farmLandPos).withProperty(MOISTURE, Integer.valueOf(7)), 2);
                }
            }
        }
    }

    public EntityWaterCow createChild(EntityAgeable ageable)
    {
        return new EntityWaterCow(this.world);
    }

    public boolean isBreedingItem(ItemStack stack) { return TEMPTATION_ITEMS.contains(stack.getItem());}
}
