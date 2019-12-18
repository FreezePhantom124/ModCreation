package com.freezephantom124.icemod.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() {
		GameRegistry.addSmelting(ModBlocks.ICE_DIAMOND_ORE, new ItemStack(ModItems.ICE_DIAMOND, 1), 2.0F);
		GameRegistry.addSmelting(ModBlocks.ENDER_ORE, new ItemStack(ModItems.ENDER_DUST, 4), 1.6F);
	}
}
