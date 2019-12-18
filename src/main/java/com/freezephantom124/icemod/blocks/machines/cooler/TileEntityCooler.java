package com.freezephantom124.icemod.blocks.machines.cooler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.freezephantom124.icemod.init.ModItems;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.command.CommandTitle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCooler extends TileEntity implements IInventory, ITickable
{
	
	private static Table<ItemStack, ItemStack, ItemStack> freezingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private static Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	private ItemStackHandler inventory = new ItemStackHandler(4);
	private ItemStack Freezing = ItemStack.EMPTY;
	private String customName;
	
	private int freezeTime;
	private int	currentFreezeTime;
	private int coolTime;
	private int totalCoolTime;
	
	@Override
	public String getName()
	{
		
		return this.hasCustomName() ? this.customName : "container.cooler";
		
	}
	
	@Override
	public boolean hasCustomName()
	{
		
		return this.customName != null && !this.customName.isEmpty();
		
	}
	
	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
		
	}
	
	@Override
	public int getSizeInventory()
	{
		
		return this.inventory.getSlots();
		
	}
	
	@Override
	public boolean isEmpty()
	{
		
		ItemStack[] stacks = new ItemStack[] {this.inventory.getStackInSlot(0), this.inventory.getStackInSlot(1), this.inventory.getStackInSlot(2), this.inventory.getStackInSlot(3)};
		
		for(ItemStack stack : stacks)
		{
			
			if(!stack.isEmpty()) return false;
			
		}
		return true;
		
	}
	
	@Override
	public ItemStack getStackInSlot(int index)
	{
		
		return (ItemStack)this.inventory.getStackInSlot(index);
		
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		
		List<ItemStack> stacks = new ArrayList<>();
		
		stacks.add(this.inventory.getStackInSlot(0));
		stacks.add(this.inventory.getStackInSlot(1));
		stacks.add(this.inventory.getStackInSlot(2));
		stacks.add(this.inventory.getStackInSlot(3));
		
		return ItemStackHelper.getAndSplit(stacks, index, count);
		
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		
		List<ItemStack> stacks = new ArrayList<>();
		
		stacks.add(this.inventory.getStackInSlot(0));
		stacks.add(this.inventory.getStackInSlot(1));
		stacks.add(this.inventory.getStackInSlot(2));
		stacks.add(this.inventory.getStackInSlot(3));
		
		return ItemStackHelper.getAndRemove(stacks, index);
		
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		
		ItemStack itemstack = (ItemStack)this.inventory.getStackInSlot(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.setStackInSlot(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if(index == 0 && index + 1 == 1 && !flag)
		{
			
			ItemStack stack1 = (ItemStack)this.inventory.getStackInSlot(index + 1);
			this.totalCoolTime = this.getCoolTime(stack, stack1);
			this.coolTime = 0;
			this.markDirty();
			
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventoty"));
		this.freezeTime = compound.getInteger("FreezeTime");
		this.coolTime = compound.getInteger("CoolTime");
		this.totalCoolTime = compound.getInteger("TotalCoolTime");
		this.currentFreezeTime = getItemFreezeTime((ItemStack)this.inventory.getStackInSlot(2));
		
	if(compound.hasKey("customName", 8)) this.setCustomName(compound.getString("customName"));
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		
		super.writeToNBT(compound);
		compound.setInteger("FreezeTime", (short)this.freezeTime);
		compound.setInteger("CoolTime", (short)this.coolTime);
		compound.setInteger("TotalCoolTime", (short)this.totalCoolTime);
		compound.setTag("Inventory", this.inventory.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("customName", this.customName);
		return compound;
		
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		
		return 64;
		
	}
	
	public boolean isFreezing()
	{
		
		return this.freezeTime > 0;
		
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isFreezing(IInventory inventory)
	{
		
		return inventory.getField(0) > 0;
		
	}
	
	public void update()
	{
		
		if(this.isFreezing())
		{
			
			--this.freezeTime;
			BlockCooler.setState(true, world, pos);
			
		}
		
		ItemStack[] inputs = new ItemStack[] {inventory.getStackInSlot(0), inventory.getStackInSlot(1)};
		ItemStack fuel = this.inventory.getStackInSlot(2);
		
		if(this.isFreezing() || !fuel.isEmpty() && !this.inventory.getStackInSlot(0).isEmpty() || this.inventory.getStackInSlot(1).isEmpty())
		{
			
			if(!this.isFreezing() && this.canFreeze())
			{
				
				this.freezeTime = getItemFreezeTime(fuel);
				this.currentFreezeTime = freezeTime;
				
				
				if(this.isFreezing() && !fuel.isEmpty())
				{
					
					Item item = fuel.getItem();
					fuel.shrink(1);
					
					if(fuel.isEmpty())
					{
						
						ItemStack item1 = item.getContainerItem(fuel);
						this.inventory.setStackInSlot(2, item1);
						
					}
					
				}
				
			}
			
		}
		
		if(this.isFreezing() && this.canFreeze() && coolTime > 0)
		{
			
			coolTime++;
			
			if(coolTime == totalCoolTime)
			{
				
				if(inventory.getStackInSlot(3).getCount() > 0)
				{
					
					inventory.getStackInSlot(3).grow(1);
					
				}
				else
				{
					
					inventory.insertItem(3, Freezing, false);
					
				}
				
				Freezing = ItemStack.EMPTY;
				coolTime = 0;
				
				return;
				
			}
			
		}
		else
		{
			
			if(this.canFreeze() && this.isFreezing())
			{
				
				ItemStack output = CoolerRecipes.getInstance().getCoolerResult(inputs[0], inputs[1]);
				
				if(!output.isEmpty())
				{
					
					Freezing = output;
					coolTime++;
					
					inputs[0].shrink(1);
					inputs[1].shrink(1);
					inventory.setStackInSlot(0, inputs[0]);
					inventory.setStackInSlot(1, inputs[1]);
					
				}
				
			}
			
		}
		
	}
	
	public int getCoolTime(ItemStack stack1, ItemStack stack2)
	{
		
		return 100;
		
	}
	
	private boolean canFreeze()
	{
		
		if(((ItemStack)this.inventory.getStackInSlot(0)).isEmpty() || ((ItemStack)this.inventory.getStackInSlot(1)).isEmpty()) return false;
		else
		{
			
			ItemStack result = CoolerRecipes.getInstance().getCoolerResult((ItemStack)this.inventory.getStackInSlot(0), (ItemStack)this.inventory.getStackInSlot(1));
			if(result.isEmpty()) return false;
			else
			{
				
				ItemStack output = (ItemStack)this.inventory.getStackInSlot(3);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
				
			}
			
		}
		
	}
	
	public void FreezetItem()
	{
		
		if(this.canFreeze())
		{
			
			ItemStack input1 = (ItemStack)this.inventory.getStackInSlot(0);
			ItemStack input2 = (ItemStack)this.inventory.getStackInSlot(1);
			ItemStack result = CoolerRecipes.getInstance().getCoolerResult(input1, input2);
			ItemStack output = (ItemStack)this.inventory.getStackInSlot(3);
			
			if(output.isEmpty()) this.inventory.setStackInSlot(3, result.copy());
			else if(output.getItem() == result.getItem()) output.grow(result.getCount());
			
			input1.shrink(1);
			input2.shrink(1);
			
		}
		
	}
	
	public static int getItemFreezeTime(ItemStack fuel) 
	{
		
		if(fuel.isEmpty()) return 0;
		else
		{
			
			Item item = fuel.getItem();

			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) 
			{
				
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.SNOW_LAYER) return 150;
				if (block == Blocks.SNOW) return 1200;
				if (block == Blocks.ICE) return 2000;
				if (block == Blocks.PACKED_ICE) return 3200;
				
				//if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
				
			}
			
			if (item == Items.SNOWBALL) return 100;
			if (item == ModItems.ICE_STICK) return 1600;

			return GameRegistry.getFuelValue(fuel);
			
		}
		
	}
	
	public static boolean isItemFuel(ItemStack fuel)
	{
		
		return getItemFreezeTime(fuel) > 0;
		
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}
	
	@Override
	public void closeInventory(EntityPlayer player) {}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		
		if(index == 3)return false;
		else if(index != 2)return true;
		else
		{
			
			return isItemFuel(stack);
			
		}
		
	}
	
	public String getGuiID()
	{
		
		return "im:cooler";
		
	}
	
	@Override
	public int getField(int id)
	{
		
		switch(id)
		{
		case 0:
			return this.freezeTime;
		case 1:
			return this.currentFreezeTime;
		case 2:
			return this.coolTime;
		case 3:
			return this.totalCoolTime;
		default:
			return 0;
		}
		
	}
	
	public void setField(int id, int value) 
	{
		
		switch(id)
		{
		case 0:
			this.freezeTime = value;
			break;
		case 1:
			this.currentFreezeTime = value;
			break;
		case 2:
			this.coolTime = value;
			break;
		case 3:
			this.totalCoolTime = value;
		}
		
	}
	
	@Override
	public int getFieldCount()
	{
		
		return 4;
		
	}
	
	@Override
	public void clear()
	{
		
		for(int cont = 0; cont < this.inventory.getSlots(); cont++)
		{
			
			this.inventory.setStackInSlot(cont, ItemStack.EMPTY);
			
		}
		
	}
	
}
