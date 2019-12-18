package com.freezephantom124.icemod.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler 
{
	public static enum EnumType implements IStringSerializable
	{
		ICE(0, "ice"),
		HOT(1, "hot");
		
		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private final int meta;
		private final String name, UnlocalizedName;
		
		private EnumType(int meta, String name) 
		{
			this(meta, name, name);
		}
		
		private EnumType(int meta, String name, String UnlocalizedName) 
		{
			this.meta = meta;
			this.name = name;
			this.UnlocalizedName = UnlocalizedName;
		}
		
		@Override
		public String getName()
		{
			return this.name;
		}
		
		public int getMeta()
		{
			return this.meta;
		}
		
		public String getUnlocalizedName()
		{
			return this.UnlocalizedName;
		}
		
		@Override
		public String toString()
		{
			return this.name;
		}
		
		public static EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}
		
		static
		{
			for(EnumType enumtype : values())
			{
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}
