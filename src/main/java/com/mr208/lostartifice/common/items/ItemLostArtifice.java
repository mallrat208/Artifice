package com.mr208.lostartifice.common.items;

import com.google.common.collect.Lists;
import com.mr208.lostartifice.LostArtifice;
import com.mr208.lostartifice.common.ModConfig;
import com.mr208.lostartifice.common.ModContent;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLostArtifice extends Item
{
	protected String name;
	protected boolean hasTooltip = true;
	protected boolean hasFlavortext = true;
	
	public ItemLostArtifice(String name)
	{
		super();
		this.name = name;
		this.setUnlocalizedName(LostArtifice.modID + "." + name);
		this.setRegistryName(LostArtifice.modID, name);
		
		this.setCreativeTab(LostArtifice.mainTab);
		
		ModContent.registeredItems.add(this);
	}
	
	public ItemLostArtifice setNoTooltip()
	{
		this.hasTooltip = false;
		return this;
	}
	
	public ItemLostArtifice setNoFlavortext()
	{
		this.hasFlavortext = false;
		return this;
	}
	
	protected List<String> getTooltip()
	{
		ArrayList<String> tooltip = new ArrayList<>();
		
		tooltip.add(I18n.format(this.getUnlocalizedName() + ".desc"));
		
		return tooltip;
	}
	
	protected String getFlavortext()
	{
		return TextFormatting.DARK_GRAY.toString() + TextFormatting.ITALIC.toString() + I18n.format(this.getUnlocalizedName() + ".flavor");
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(this.hasTooltip && ModConfig.tooltips)
			tooltip.addAll(getTooltip());
		if(this.hasFlavortext && ModConfig.flavortext)
			tooltip.add(getFlavortext());
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
