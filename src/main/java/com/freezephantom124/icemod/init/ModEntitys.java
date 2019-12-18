package com.freezephantom124.icemod.init;

import com.freezephantom124.icemod.Main;
import com.freezephantom124.icemod.entity.EntityIce_mage;
import com.freezephantom124.icemod.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntitys
{
	public static void registerEntities()
	{
		registerEntity("ice_mage", EntityIce_mage.class, Reference.ENTITY_ICE_MAGE, 40, 16777215, 65535);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
}
