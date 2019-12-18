package com.freezephantom124.icemod.blocks.machines.cooler.slots;

import com.freezephantom124.icemod.blocks.machines.cooler.TileEntityCooler;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCoolerFuel extends Slot
{
	
	public SlotCoolerFuel(IInventory inventory, int index, int x, int y)
	{
		
		super(inventory, index, x, y);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		
		return TileEntityCooler.isItemFuel(stack);
		
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		
		return super.getItemStackLimit(stack);
		
	}
	
}
