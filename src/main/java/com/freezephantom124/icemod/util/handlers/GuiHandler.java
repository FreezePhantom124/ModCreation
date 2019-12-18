package com.freezephantom124.icemod.util.handlers;

import com.freezephantom124.icemod.blocks.machines.cooler.ContainerCooler;
import com.freezephantom124.icemod.blocks.machines.cooler.GuiCooler;
import com.freezephantom124.icemod.blocks.machines.cooler.TileEntityCooler;
import com.freezephantom124.icemod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		
		if(ID == Reference.GUI_COOLER) return new ContainerCooler(player.inventory, (TileEntityCooler)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		
		if(ID == Reference.GUI_COOLER) return new GuiCooler(player.inventory, (TileEntityCooler)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
		
	}
	
	
	
}
