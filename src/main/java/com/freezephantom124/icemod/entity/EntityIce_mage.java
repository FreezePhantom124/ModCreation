package com.freezephantom124.icemod.entity;

import com.freezephantom124.icemod.util.handlers.LootTableHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityIce_mage extends EntitySnowman
{
	
	public EntityIce_mage(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.8F);
	}
	
	protected void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIWanderAvoidWater(this, 1.0F, 1.0000001E-5F));
		this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.5D, 10, 20.0F));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.MOB_SELECTOR));
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
		//this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(10.0D);
		//this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
	}
	
	@Override
	public float getEyeHeight()
	{
		return 1.6F;
	}
	
	public EntitySnowman createChild(EntityAgeable ageable)
	{
		return new EntityIce_mage(world);
	}
	
	@Override
	protected ResourceLocation getLootTable()
	{
		return LootTableHandler.ICE_MAGE;
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return super.getAmbientSound();
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return super.getHurtSound(source);
	}
	
	@Override
	protected SoundEvent getDeathSound()
	{
		return super.getDeathSound();
	}
	
}
