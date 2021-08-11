package com.momo.morecows.network.MilkWorkshop;

import com.momo.morecows.IdlFramework;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiMilkWorkshop extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(IdlFramework.MODID + ":textures/gui/container/milk_workshop.png");

    public GuiMilkWorkshop(EntityPlayer player, World world, int x, int y, int z){
        super(new ContainerMilkWorkshop(player, world, x, y, z));
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        int left = (this.width - this.xSize / 2);
        int top = (this.height - this.ySize / 2);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(left, top, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String text = I18n.format("tile.milk_workshop.name");
        this.drawCenteredString(this.fontRenderer, text, this.xSize / 2, 6, 0x00404040);
    }
}
