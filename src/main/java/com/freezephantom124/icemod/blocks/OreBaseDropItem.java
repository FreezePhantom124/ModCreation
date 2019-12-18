package com.freezephantom124.icemod.blocks;

import java.util.Random;

import com.freezephantom124.icemod.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreBaseDropItem extends BlockBase {
	
	Item Itemdrop;
	int maxD, minD;
	
	public OreBaseDropItem(String name, Material material, CreativeTabs creativetab, SoundType sound, float hardness,
			float resistance, String tooltype, int toollevel, float glow, int opacity, Item drop, int maxI, int minI) {
		super(name, material, creativetab, sound, hardness, resistance, tooltype, toollevel, glow, opacity);
		Itemdrop = drop;
		maxD = maxI;
		minD = minI;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Itemdrop;
	}
	
	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(maxD) + minD;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(this);
	}

}
