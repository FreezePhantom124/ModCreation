package com.freezephantom124.icemod.tabs;

import com.freezephantom124.icemod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class IceModTab extends CreativeTabs
{
	public IceModTab(String label)
	{
		super("icemodtab");
		this.setBackgroundImageName("icemod.png");
	}
	
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.ICE_DIAMOND);
	}
}
