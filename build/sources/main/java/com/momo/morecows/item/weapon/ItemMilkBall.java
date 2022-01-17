package com.momo.morecows.item.weapon;

import com.momo.morecows.entity.tiles.EntityMilkBall;
import com.momo.morecows.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMilkBall extends ItemBase {
    public ItemMilkBall(String name)
    {
        super(name);
        setMaxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack item = playerIn.getHeldItem(handIn);
        if(!playerIn.capabilities.isCreativeMode){item.shrink(1);}

        if(!worldIn.isRemote)
        {
            EntityMilkBall entityMilkBall = new EntityMilkBall(worldIn, playerIn);
            float pitch = playerIn.rotationPitch, yaw = playerIn.rotationYaw;
            entityMilkBall.shoot(playerIn, pitch, yaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entityMilkBall);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, item);
    }
}
