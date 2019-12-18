package com.freezephantom124.icemod.util.handlers;

import com.freezephantom124.icemod.blocks.machines.cooler.TileEntityCooler;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
	
	public static void registerTileEntities()
	{
		
		GameRegistry.registerTileEntity(TileEntityCooler.class, "cooler");
		
	}
	
}
