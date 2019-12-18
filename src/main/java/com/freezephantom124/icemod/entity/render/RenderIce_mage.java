package com.freezephantom124.icemod.entity.render;

import com.freezephantom124.icemod.entity.EntityIce_mage;
import com.freezephantom124.icemod.entity.model.ModelIce_mage;
import com.freezephantom124.icemod.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderIce_mage extends RenderLiving<EntityIce_mage>
{
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/ice_mage.png");
	
	public RenderIce_mage(RenderManager manager)
	{
		super(manager, new ModelIce_mage(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityIce_mage entity)
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityIce_mage entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{

		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	
}
