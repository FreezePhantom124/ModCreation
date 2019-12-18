package com.freezephantom124.icemod;

import com.freezephantom124.icemod.init.ModEntitys;
import com.freezephantom124.icemod.init.ModRecipes;
import com.freezephantom124.icemod.proxy.CommonProxy;
import com.freezephantom124.icemod.tabs.IceModTab;
import com.freezephantom124.icemod.util.Reference;
import com.freezephantom124.icemod.util.handlers.GuiHandler;
import com.freezephantom124.icemod.util.handlers.RenderHandler;
import com.freezephantom124.icemod.world.gen.ModWorldGen;
import com.freezephantom124.icemod.world.gen.WorldGenCustomStructures;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	public static final CreativeTabs ICEMOD = new IceModTab("icemodtab");
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
		
		ModEntitys.registerEntities();
		RenderHandler.registerEntityRenders();
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		
		ModRecipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		
	}
	
	@EventHandler
	public static void Postinit(FMLPostInitializationEvent event)
	{
		
		
		
	}
	
}
