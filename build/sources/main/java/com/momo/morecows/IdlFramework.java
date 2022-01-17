package com.momo.morecows;

import com.momo.morecows.init.ModConfig;
import com.momo.morecows.init.ModRecipes;
import com.momo.morecows.init.ModSpawn;
import com.momo.morecows.init.RegistryHandler;
import com.momo.morecows.item.potions.ModPotionType;
import com.momo.morecows.keys.KeyboardManager;
import com.momo.morecows.meta.MetaUtil;
import com.momo.morecows.network.NetworkHandler;
import com.momo.morecows.proxy.ProxyBase;
import com.momo.morecows.recipe.MilkWorkshop;
import com.momo.morecows.util.CommonDef;
import com.momo.morecows.util.Reference;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

//Added 'little' steps for making weapons, tools and armor. Enjoy casting and striking as a blacksmith.

@Mod(modid = IdlFramework.MODID, name = IdlFramework.NAME, version = IdlFramework.VERSION)
//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class IdlFramework {
    public static final String MODID = "morecows";
    public static final String NAME = "MoreCows";
    public static final String VERSION = "1.4.2";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static IdlFramework instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        if (MODID.equals("untitled")) {
            logger.error("Please change your mod id in the main class.");

        }

        if (Reference.CLIENT_PROXY_CLASS.indexOf("somebody.idlframewok.proxy.ClientProxy") > 0) {
            logger.warn("Have you changed your package name to author and modname?");

        }

        RegistryHandler.preInitRegistries(event);
        NetworkHandler.register();
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.Init();
        RegisterTileEntity();
        RegistryHandler.initRegistries(event);
        if (!proxy.isServer()) {
            KeyboardManager.init();
        }
        NetworkHandler.init();

        ModPotionType.register();

        LogWarning("%s has finished its initializations", MODID);

        //MachineRecipe
        MilkWorkshop.MilkWorkshopRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late
        if (ModConfig.SPAWN_CONF.SPAWN) {
            ModSpawn.registerSpawnList();
        }

        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isIRRLoaded = Loader.isModLoaded("itemrender");
        MetaUtil.isLoaded_TiC = Loader.isModLoaded("tconstruct");
        MetaUtil.isLoaded_Slashblade = Loader.isModLoaded("flammpfeil.slashblade");
        MetaUtil.isLoaded_Botania = Loader.isModLoaded("botania");
        MetaUtil.isLoaded_DWeapon = Loader.isModLoaded("dweapon");
        MetaUtil.isLoaded_AOA3 = Loader.isModLoaded(CommonDef.MOD_NAME_AOA3);
        MetaUtil.isLoaded_GC = Loader.isModLoaded("galacticraftcore");
        MetaUtil.isLoaded_Taoism = Loader.isModLoaded("taoism");
        MetaUtil.isLoaded_GOG = Loader.isModLoaded(CommonDef.MOD_NAME_GOG);

        TrashTalking();

        RegistryHandler.postInitReg();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }

    private void TrashTalking() {
        if (MetaUtil.isIDLLoaded) {
            IdlFramework.Log("[Idealland Framework] Bow to Idealland.");
        } else {
            IdlFramework.Log("[Idealland Framework] Made with Idealland Framework.");
        }
    }

    private static void RegisterTileEntity() {
//        GameRegistry.registerTileEntity(TileEntityDeBoomOrb.class, new ResourceLocation(MODID, "deboom_orb_basic"));

        //GameRegistry.registerTileEntity(TileEntityBuilderFarm.class, new ResourceLocation(MODID, "builder_farm_basic"));
        //GameRegistry.registerTileEntity(TileEntityBuilderOne.class, new ResourceLocation(MODID, "builder.builder_one"));
    }

    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(str);
//        }
    }

    public static void Log(String str, Object... args) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(String.format(str, args));
//        }
    }
}