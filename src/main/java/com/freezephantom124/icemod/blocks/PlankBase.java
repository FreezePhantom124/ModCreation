package com.freezephantom124.icemod.blocks;

import com.freezephantom124.icemod.Main;
import com.freezephantom124.icemod.init.ModBlocks;
import com.freezephantom124.icemod.init.ModItems;
import com.freezephantom124.icemod.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class PlankBase extends Block implements IHasModel
{
	public PlankBase(String name)
	{
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setHardness(2);
		setHarvestLevel("axe", 0);
		setDefaultState(this.blockState.getBaseState());
		setCreativeTab(Main.ICEMOD);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}
