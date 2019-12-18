package com.freezephantom124.icemod.init;

import java.util.ArrayList;
import java.util.List;

import com.freezephantom124.icemod.items.ItemBase;
import com.freezephantom124.icemod.items.armor.ArmorBase;
import com.freezephantom124.icemod.items.food.FoodBase;
import com.freezephantom124.icemod.items.tools.ToolAxe;
import com.freezephantom124.icemod.items.tools.ToolHoe;
import com.freezephantom124.icemod.items.tools.ToolPickaxe;
import com.freezephantom124.icemod.items.tools.ToolSpade;
import com.freezephantom124.icemod.items.tools.ToolSword;
import com.freezephantom124.icemod.util.Reference;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems 
{
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial MATERIAL_ICE_DIAMOND = EnumHelper.addToolMaterial("material_ice_diamond", 4, 2000, 20.0F, 5.0F, 25);
	public static final ArmorMaterial ARMOR_MATERIAL_ICE_DIAMOND = EnumHelper.addArmorMaterial("armor_material_ice_diamond", Reference.MOD_ID + ":ice_diamond", 35, new int[] {4, 8, 10, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
	
	//Items
	public static final Item ICE_DIAMOND = new ItemBase("ice_diamond");
	public static final Item ICE_STICK = new ItemBase("ice_stick");
	public static final Item ENDER_DUST = new ItemBase("ender_dust");
	
	//Tools
	public static final ItemSword ICE_DIAMOND_SWORD = new ToolSword("ice_diamond_sword", MATERIAL_ICE_DIAMOND);
	public static final ItemSpade ICE_DIAMOND_SHOVEL = new ToolSpade("ice_diamond_shovel", MATERIAL_ICE_DIAMOND);
	public static final ItemPickaxe ICE_DIAMOND_PICKAXE = new ToolPickaxe("ice_diamond_pickaxe", MATERIAL_ICE_DIAMOND);
	public static final ItemAxe ICE_DIAMOND_AXE = new ToolAxe("ice_diamond_axe", MATERIAL_ICE_DIAMOND);
	public static final ItemHoe ICE_DIAMOND_HOE = new ToolHoe("ice_diamond_hoe", MATERIAL_ICE_DIAMOND);
	
	//Armors
	public static final Item ICE_DIAMOND_HELMET = new ArmorBase("ice_diamond_helmet", ARMOR_MATERIAL_ICE_DIAMOND, 1, EntityEquipmentSlot.HEAD);
	public static final Item ICE_DIAMOND_CHESTPLATE = new ArmorBase("ice_diamond_chestplate", ARMOR_MATERIAL_ICE_DIAMOND, 1, EntityEquipmentSlot.CHEST);
	public static final Item ICE_DIAMOND_LEGGINGS = new ArmorBase("ice_diamond_leggings", ARMOR_MATERIAL_ICE_DIAMOND, 2, EntityEquipmentSlot.LEGS);
	public static final Item ICE_DIAMOND_BOOTS = new ArmorBase("ice_diamond_boots", ARMOR_MATERIAL_ICE_DIAMOND, 1, EntityEquipmentSlot.FEET);
	
	//Foods
	public static final Item ICE_BEEF = new FoodBase("ice_beef", 12, 25.0F, true);
	
}