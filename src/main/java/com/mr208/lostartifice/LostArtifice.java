package com.mr208.lostartifice;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;

import java.util.logging.Logger;

@Mod(modid = LostArtifice.modID, name = LostArtifice.modName, version = LostArtifice.modVersion)
public class LostArtifice
{
	public static final String modID = "lostartifice";
	public static final String modName = "Lost Artifice";
	public static final String modVersion = "1.0.0";
	
	public static Logger logger;
	
	@Instance(modID)
	public static LostArtifice instance;
	
	
	
	
	public static final CreativeTabs mainTab = new CreativeTabs(modID)
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Items.APPLE);
		}
	};
	
	public static final CreativeTabs worldTab = new CreativeTabs(modID)
	{
		@Override
		public String getTabLabel()
		{
			return super.getTabLabel() + ".world";
		}
		
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Items.PUMPKIN_PIE);
		}
	};
}
