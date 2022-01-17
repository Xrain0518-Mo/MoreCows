package com.momo.morecows.item;


import com.momo.morecows.IdlFramework;
import com.momo.morecows.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public class ItemDebug extends ItemBase{
    int index = 0;
    public ItemDebug SetIndex(int index){
        this.index = index;
        return this;
    }
    public ItemDebug(String name) {
        super(name);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);

        //tooltip.add(I18n.format("idlframewok.gua_enhance_total.desc", IDLSkillNBT.GetGuaEnhanceTotal(stack)));
        tooltip.add(IDLNBTUtil.getNBT(stack).toString());
    }

//    public boolean isEnchantable(ItemStack stack)
//    {
//        return true;
//    }
//
//    @Override
//    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
//        if (stack.isItemEnchantable())
//        {
//            stack.addEnchantment(ModEnchantments.fireBurn, 0);
//        }
//    }


    void TestEnchant(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        int p_185291_0_ = 30;
        ItemStack testItemStack = playerIn.getHeldItemOffhand();
        boolean allowTreasure = false;
        Item item = testItemStack.getItem();
        boolean flag = item == Items.BOOK;

        for (Enchantment enchantment : Enchantment.REGISTRY)
        {
            boolean judge1 = !enchantment.isTreasureEnchantment() || allowTreasure;
            boolean judge2 = enchantment.canApplyAtEnchantingTable(testItemStack);
            boolean judge3 = flag && enchantment.isAllowedOnBooks();

            IdlFramework.Log("[%s]Lv %d to %d, canApplyAtEnchantingTable = %s, judgement[%s,%s,%s]",
                    enchantment.getName(),
                    enchantment.getMinLevel(),
                    enchantment.getMaxLevel(),
                    enchantment.canApplyAtEnchantingTable(testItemStack),
                    judge1, judge2, judge3
            );
            if (judge1 && judge2 || judge3)
            {
                for (int i = enchantment.getMaxLevel(); i >= enchantment.getMinLevel() ; --i)
                {
                    IdlFramework.Log("[%s] Lv.%d available range = %d to %d", enchantment.getName(), i,
                            enchantment.getMinEnchantability(i), enchantment.getMaxEnchantability(i));
                    if (p_185291_0_ >= enchantment.getMinEnchantability(i) && p_185291_0_ <= enchantment.getMaxEnchantability(i))
                    {
                        IdlFramework.Log("[%s] successfully elected", enchantment.getName());
                        break;
                    }
                }
            }
        }
    }
}
