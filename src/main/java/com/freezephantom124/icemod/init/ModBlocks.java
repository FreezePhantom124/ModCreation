package com.freezephantom124.icemod.init;

import java.util.ArrayList;
import java.util.List;

import com.freezephantom124.icemod.blocks.BlockBase;
import com.freezephantom124.icemod.blocks.IceEgg;
import com.freezephantom124.icemod.blocks.LeavesBase;
import com.freezephantom124.icemod.blocks.LogBase;
import com.freezephantom124.icemod.blocks.OreBaseDropItem;
import com.freezephantom124.icemod.blocks.PlankBase;
import com.freezephantom124.icemod.blocks.SaplingsFire;
import com.freezephantom124.icemod.blocks.SaplingsIce;
import com.freezephantom124.icemod.blocks.machines.cooler.BlockCooler;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks 
{
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Ores
	public static final Block ICE_DIAMOND_ORE = new OreBaseDropItem("ice_diamond_ore", Material.ROCK, CreativeTabs.BUILDING_BLOCKS, SoundType.STONE, 10.0F, 15.0F, "pickaxe", 3, 0.0F, 1, ModItems.ICE_DIAMOND, 2, 1);
	public static final Block ENDER_ORE = new BlockBase("ender_ore", Material.ROCK, CreativeTabs.BUILDING_BLOCKS, SoundType.STONE, 8.0F, 15.0F, "pickaxe", 3, 0.0F, 1);
	
	//Blocks
	public static final Block ICE_DIAMOND_BLOCK = new BlockBase("ice_diamond_block", Material.IRON, CreativeTabs.BUILDING_BLOCKS, SoundType.METAL, 5.0F, 15.0F, "pickaxe", 3, 0.0F, 1);
	
	//Three Blocks
	public static final Block ICE_PLANKS = new PlankBase("ice_planks");
	public static final Block ICE_LOG = new LogBase("ice_log");
	public static final Block ICE_SAPLING = new SaplingsIce("ice_sapling");
	public static final Block ICE_LEAVES = new LeavesBase("ice_leaves", ModBlocks.ICE_SAPLING);
	
	public static final Block FIRE_PLANKS = new PlankBase("fire_planks");
	public static final Block FIRE_LOG = new LogBase("fire_log");
	public static final Block FIRE_SAPLING = new SaplingsFire("fire_sapling");
	public static final Block FIRE_LEAVES = new LeavesBase("fire_leaves", ModBlocks.FIRE_SAPLING);
	
	//Blocks With Custom Model
	public static final Block ICE_EGG = new IceEgg("ice_egg");
	
	//GUI Blocks
	public static final Block COOLER = new BlockCooler("cooler", CreativeTabs.REDSTONE, 5.0F, 10.0F, 2, 0.0F, 1);
	
}