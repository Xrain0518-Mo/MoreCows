package com.momo.morecows.events;

import com.momo.morecows.IdlFramework;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class AddedEntityDrop  {

    @SubscribeEvent
    public static void MobDrop(LivingDropsEvent evt){
        EntityLivingBase deathOne = evt.getEntityLiving();

        if (deathOne.isChild()){
            return;
        }

//        if (deathOne instanceof EntityCow ) {
//            deathOne.dropItem(ModItems.SINEW_COW, (int)(Math.random()*3) + ((int)(Math.round(Math.random()*(evt.getLootingLevel())))));
//        }
//
        }
   }
 /*
    public static void MaterialsDrop(LivingDeathEvent evt){   //监听生物死亡事件
        World world = evt.getEntity().getEntityWorld();       //获取世界
        EntityLivingBase deathOne = evt.getEntityLiving();    //获取死亡生物

        if (evt.isCanceled() || world.isRemote){            //没死成 或 本地端不执行
            return;
        }

        if (deathOne instanceof EntityWolf ){
            deathOne.dropItem(ModItems.SINEW_WOLF,1 + (int)(Math.random()*2));
            deathOne.dropItem(ModItems.LEATHER_WOLF,1 + (int)(Math.random()*2));
        }

        }

    }
*/
