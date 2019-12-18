package com.freezephantom124.icemod.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelIce_mage extends ModelBase {
    public ModelRenderer Capuz;
    public ModelRenderer BracoDireito;
    public ModelRenderer PernaDireita;
    public ModelRenderer Cabeca;
    public ModelRenderer Corpo;
    public ModelRenderer BracoEsquerdo;
    public ModelRenderer PernaEsquerda;

    public ModelIce_mage() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Capuz = new ModelRenderer(this, 32, 0);
        this.Capuz.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Capuz.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.PernaEsquerda = new ModelRenderer(this, 16, 48);
        this.PernaEsquerda.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.PernaEsquerda.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.BracoEsquerdo = new ModelRenderer(this, 32, 48);
        this.BracoEsquerdo.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.BracoEsquerdo.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Corpo = new ModelRenderer(this, 16, 16);
        this.Corpo.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Corpo.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.BracoDireito = new ModelRenderer(this, 40, 16);
        this.BracoDireito.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.BracoDireito.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Cabeca = new ModelRenderer(this, 0, 0);
        this.Cabeca.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cabeca.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.PernaDireita = new ModelRenderer(this, 0, 16);
        this.PernaDireita.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.PernaDireita.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Capuz.render(f5);
        this.PernaEsquerda.render(f5);
        this.BracoEsquerdo.render(f5);
        this.Corpo.render(f5);
        this.BracoDireito.render(f5);
        this.Cabeca.render(f5);
        this.PernaDireita.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
    	this.PernaEsquerda.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.PernaDireita.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    	
    	this.BracoEsquerdo.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.BracoDireito.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    	
    	this.Cabeca.rotateAngleY = netHeadYaw * 0.017453292F;
    	this.Cabeca.rotateAngleX = headPitch * 0.017453292F;
    	
    	this.Capuz.rotateAngleY = netHeadYaw * 0.017453292F;
    	this.Capuz.rotateAngleX = headPitch * 0.017453292F;
    }
    
}
