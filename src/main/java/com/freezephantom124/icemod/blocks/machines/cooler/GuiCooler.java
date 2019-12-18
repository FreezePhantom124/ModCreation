package com.freezephantom124.icemod.blocks.machines.cooler;

import com.freezephantom124.icemod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiCooler extends GuiContainer
{
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/cooler.png");
	private final InventoryPlayer player;
	private final TileEntityCooler tileentity;
	
	public GuiCooler(InventoryPlayer player, TileEntityCooler tileentity)
	{
		
		super(new ContainerCooler(player, tileentity));
		
		this.player = player;
		this.tileentity = tileentity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityCooler.isFreezing(tileentity))
		{
			
			int num = this.getFreezeLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 58, this.guiTop + 37 + 12 - num, 176, 12 - num, 14, num + 1);
			
		}
		
		int num2 = this.getCoolprogressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 36, 176, 14, num2 + 1, 16);
		
	}
	
	private int getFreezeLeftScaled(int pixels)
	{
		
		int num = this.tileentity.getField(1);
		
		if(num == 0) num = 100;
		
		return this.tileentity.getField(0) * pixels / num;
		
	}
	
	private int getCoolprogressScaled(int pixels)
	{
		
		int num = this.tileentity.getField(2);
		int num2 = this.tileentity.getField(2);
		
		return num2 != 0 && num != 0 ? num * pixels / num2 : 0;
		
	}
	
}
