package com.freezephantom124.icemod.blocks.machines.cooler;

import java.util.Random;

import com.freezephantom124.icemod.Main;
import com.freezephantom124.icemod.blocks.BlockBase;
import com.freezephantom124.icemod.init.ModBlocks;
import com.freezephantom124.icemod.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCooler extends BlockBase implements ITileEntityProvider
{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool FREEZING = PropertyBool.create("freezing");
	
	public BlockCooler(String name, CreativeTabs creativetab, float hardness, float resistance, int toollevel, float glow, int opacity)
	{
		
		super(name, Material.IRON, creativetab, SoundType.METAL, hardness, resistance, "pickaxe", toollevel, glow, opacity);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(FREEZING, false));
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		
		return Item.getItemFromBlock(ModBlocks.COOLER);
		
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		
		return new ItemStack(ModBlocks.COOLER);
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Main.instance, Reference.GUI_COOLER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		this.createNewTileEntity(worldIn, 0);
		
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		
		if(!worldIn.isRemote)
		{
			
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			
			if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
			else if(face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
			else if(face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
			else if(face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
			
		}
		
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active) worldIn.setBlockState(pos, ModBlocks.COOLER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(FREEZING, true), 3);
		else worldIn.setBlockState(pos, ModBlocks.COOLER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(FREEZING, false), 3);
		
		if(tileentity != null)
		{
			
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
			
		}
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		
		return new TileEntityCooler();
		
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		
		TileEntityCooler tileentity = (TileEntityCooler)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
		
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		
		return EnumBlockRenderType.MODEL;
		
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
		
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
		
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		
		return new BlockStateContainer(this, new IProperty[] {FREEZING,FACING});
		
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
		
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		
		return ((EnumFacing)state.getValue(FACING)).getIndex();
		
	}
	
}
