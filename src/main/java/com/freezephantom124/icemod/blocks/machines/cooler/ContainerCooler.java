package com.freezephantom124.icemod.blocks.machines.cooler;

import javax.annotation.Nonnull;

import com.freezephantom124.icemod.blocks.machines.cooler.slots.SlotCoolerFuel;
import com.freezephantom124.icemod.blocks.machines.cooler.slots.SlotCoolerOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCooler extends Container
{
	
	private final TileEntityCooler tileentity;
	
	private int freezeTime;
	private int	currentFreezeTime;
	private int coolTime;
	private int totalCoolTime;
	
	public ContainerCooler(InventoryPlayer player, TileEntityCooler tileentity)
	{
		
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 46, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 67, 17));
		this.addSlotToContainer(new SlotCoolerFuel(tileentity, 2, 56, 53));
		this.addSlotToContainer(new SlotCoolerOutput(player.player, tileentity, 3, 116, 35));
		
		for(int y = 0; y < 3; y++)
		{
			
			for(int x = 0; x < 9; x++)
			{
				
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
				
			}
			
		}
		
		for(int x = 0; x < 9; x++)
		{
			
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
			
		}
		
	}
	
	@Override
	public void addListener(IContainerListener listener)
	{
		
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
		
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.coolTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.freezeTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentFreezeTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalCoolTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			
		}
		
		this.coolTime = this.tileentity.getField(2);
		this.freezeTime = this.tileentity.getField(0);
		this.currentFreezeTime = this.tileentity.getField(1);
		this.totalCoolTime = this.tileentity.getField(3);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		
		this.tileentity.setField(id, data);
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		
		return this.tileentity.isUsableByPlayer(playerIn);
		
	}
	
	@Nonnull
	@Override
	public ItemStack transferStackInSlot(final EntityPlayer player, final int index)
	{
		
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);			
		
		if (slot != null && slot.getHasStack())
		{
			
			ItemStack stack2 = slot.getStack();
			stack = stack2.copy();

			int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
			
			if (index < containerSlots)
			{
				
				if (!mergeItemStack(stack2, containerSlots, this.inventorySlots.size(), true))
				{
					
					return ItemStack.EMPTY;
					
				}
				
			}
			else if (!mergeItemStack(stack2, 0, containerSlots, false))
			{
				
				return ItemStack.EMPTY;
				
			}
			
			if (stack2.getCount() == 0)
			{
				
				slot.putStack(ItemStack.EMPTY);
				
			}
			else
			{
				
				slot.onSlotChanged();
				
			}
			if (stack2.getCount() == stack.getCount())
			{
				
				return ItemStack.EMPTY;
				
			}
			
			slot.onTake(player, stack2);
			
		}
		return stack;
		
	}
	
}
