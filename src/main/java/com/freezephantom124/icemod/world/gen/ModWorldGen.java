package com.freezephantom124.icemod.world.gen;

import java.util.Random;

import com.freezephantom124.icemod.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator {

	private WorldGenerator ore_ice_diamond;
	private WorldGenerator ore_ender;
	
	public ModWorldGen()
	{
		ore_ice_diamond = new WorldGenMinable(ModBlocks.ICE_DIAMOND_ORE.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
		ore_ender = new WorldGenMinable(ModBlocks.ENDER_ORE.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.END_STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch(world.provider.getDimension())
		{
		case -1:
			
			
			
			break;
		
		case 0:
			
			generateOre(ore_ice_diamond, world, random, chunkX, chunkZ, 4, 10, 25);
			
			break;
		
		case 1:
			
			
			generateOre(ore_ender, world, random, chunkX, chunkZ, 0, 256, 30);
			
		}
	}
	
	private void generateOre(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int chances)
	{
		if(minY > maxY || minY < 0 || maxY > 256) throw new IllegalArgumentException("Ore generated out of bounds");
		
		int deltaY = maxY - minY;
		for (int i = 0; i < chances; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = minY + random.nextInt(deltaY);
			int z = chunkZ * 16 + random.nextInt(16);
			
			generator.generate(world, random, new BlockPos(x,y,z));
		}
	}
	
}
