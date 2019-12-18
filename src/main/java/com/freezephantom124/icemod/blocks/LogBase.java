package com.freezephantom124.icemod.blocks;

import com.freezephantom124.icemod.Main;
import com.freezephantom124.icemod.init.ModBlocks;
import com.freezephantom124.icemod.init.ModItems;
import com.freezephantom124.icemod.util.interfaces.IHasModel;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LogBase extends BlockLog implements IHasModel
{
	public LogBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
		setCreativeTab(Main.ICEMOD);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = this.getDefaultState();
		
		switch(meta & 6)
		{
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;
		
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;
		
		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;
		
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
		
		return state;
		
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		
		switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
		{
		case X:
			i |= 2;
			break;
		
		case Y:
			i |= 4;
			break;
		
		case Z:
			i |= 6;
		}
		
		return i;
		
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
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
