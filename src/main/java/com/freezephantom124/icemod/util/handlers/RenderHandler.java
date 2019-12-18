package com.freezephantom124.icemod.util.handlers;

import com.freezephantom124.icemod.entity.EntityIce_mage;
import com.freezephantom124.icemod.entity.render.RenderIce_mage;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityIce_mage.class, new IRenderFactory<EntityIce_mage>()
		{
			@Override
			public Render<? super EntityIce_mage> createRenderFor(RenderManager manager)
			{
				return new RenderIce_mage(manager);
			}
		});
	}
}
