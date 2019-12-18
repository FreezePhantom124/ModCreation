package com.freezephantom124.icemod.blocks.machines.cooler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.freezephantom124.icemod.init.ModBlocks;
import com.freezephantom124.icemod.init.ModItems;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CoolerRecipes
{
	
	private static final CoolerRecipes INSTANCE = new CoolerRecipes();
	private static Table<ItemStack, ItemStack, ItemStack> freezingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private static Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	private static List<ItemStack> ingrediente1 = new ArrayList<ItemStack>();
	private static List<ItemStack> ingrediente2 = new ArrayList<ItemStack>();
	private static List<ItemStack> resultados = new ArrayList();
	
	public static CoolerRecipes getInstance()
	{
		
		return INSTANCE;
		
	}
	
	private CoolerRecipes()
	{
		
		addCoolerRecipe(new ItemStack(ModBlocks.ICE_EGG), new ItemStack(Items.DIAMOND), new ItemStack(ModItems.ICE_DIAMOND), 5.0F);
		addCoolerRecipe(new ItemStack(ModItems.ENDER_DUST), new ItemStack(ModItems.ICE_BEEF), new ItemStack(ModItems.ICE_DIAMOND), 10.0F);
		addCoolerRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(Blocks.GOLD_ORE), new ItemStack(Blocks.DIAMOND_ORE), 20.0F);
		
	}
	
	public void addCoolerRecipe(ItemStack input1, ItemStack input2, ItemStack result, Float experience)
	{
		
		if(getCoolerResult(input1,input2) != ItemStack.EMPTY) return;
		this.freezingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
		
		ingrediente1.add(input1);
		ingrediente2.add(input2);
		resultados.add(result);
		
	}
	
	public ItemStack getCoolerResult(ItemStack input1, ItemStack input2)
	{
		
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.freezingList.columnMap().entrySet())
		{
			
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
			{
				
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
				{
					
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
					{
						
						return (ItemStack)ent.getValue();
						
					}
					
				}
				
			}
			
		}
		return ItemStack.EMPTY;
		
		/*for(int cont1 = 0; cont1 < ingrediente1.size(); cont1++)
		{
			
			if(ingrediente1.get(cont1) == input1 && ingrediente2.get(cont1) == input2)
			{
				
				return resultados.get(cont1);
				
			}
			
		}
		
		return ItemStack.EMPTY;*/
		
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
		
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualFreezingList()
	{
		
		return this.freezingList;
		
	}
	
	public float getCoolerExperience(ItemStack stack)
	{
		
		for(Entry<ItemStack, Float> entry : this.experienceList.entrySet())
		{
			
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				
				return ((Float)entry.getValue()).floatValue();
				
			}
			
		}
		return 0.0F;
		
	}
	
}
