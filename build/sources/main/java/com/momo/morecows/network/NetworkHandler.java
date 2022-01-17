package com.momo.morecows.network;

import com.momo.morecows.IdlFramework;
import com.momo.morecows.network.protocols.PacketTest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkHandler {
    public static final ResourceLocation MSG_RESOURCE = new ResourceLocation(IdlFramework.MODID, "msg");
    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(IdlFramework.MODID);

    static int id = 0;

    public static void init()
    {
        //C2S
        channel.registerMessage(PacketTest.Handler.class, PacketTest.class, id++, Side.SERVER);
        //just call SendToServer


        //S2C
        //PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
    }

    public static void SendToServer(IMessage packet)
    {
        channel.sendToServer(packet);
    }

    public static void register()
    {

        Power.CHANNEL.register(Power.class);
        NetworkRegistry.INSTANCE.registerGuiHandler(IdlFramework.MODID, new GuiHandler());
    }

    public static class Power
    {
        private static final String NAME = "POWER";
        private static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onClientCustomPacket(FMLNetworkEvent.ClientCustomPacketEvent event)
        {

        }
    }
}
