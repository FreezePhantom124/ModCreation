package com.freezephantom124.icemod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class IceEgg extends BlockBase
{
	
	public static final AxisAlignedBB ICE_EGG_AABB = new AxisAlignedBB(0.125D, 0, 0.125D, 0.875D, 0.9375D, 0.875D);

	public IceEgg(String name)
	{
		super(name, Material.DRAGON_EGG, CreativeTabs.BUILDING_BLOCKS, SoundType.STONE, 1.0F, 0.0F, "pickaxe", 0, 0.0F, 0);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return ICE_EGG_AABB;
	}
	
}
