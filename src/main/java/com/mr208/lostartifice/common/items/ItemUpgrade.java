package com.mr208.lostartifice.common.items;

import com.mr208.lostartifice.common.ModConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class ItemUpgrade extends ItemLostArtifice
{
	private String type;
	private EnumUpgrade enumUpgrade;
	
	public ItemUpgrade(EnumUpgrade upgrade)
	{
		super("upgrade."+upgrade.getName());
		this.type = upgrade.getType();
		this.enumUpgrade = upgrade;
	}
	
	public EnumUpgrade getUpgradeEnum()
	{
		return enumUpgrade;
	}
	
	@Override
	protected List<String> getTooltip()
	{
		ArrayList<String> tooltip = new ArrayList<>();
		
		
		tooltip.add(I18n.format("item.lostartifice.upgrade.type." + this.type));
		tooltip.add(TextFormatting.YELLOW + enumUpgrade.getEnchantment().getTranslatedName(ModConfig.upgrades.limit_upgrades ? enumUpgrade.getMaxLevel() : enumUpgrade.getEnchantment().getMaxLevel()));
		if(this.enumUpgrade == EnumUpgrade.SHARPENING_KIT)
			tooltip.add(TextFormatting.YELLOW + EnumUpgrade.SHARPENING_KIT_E.getEnchantment().getTranslatedName(ModConfig.upgrades.limit_upgrades ? EnumUpgrade.SHARPENING_KIT_E.getMaxLevel() : EnumUpgrade.SHARPENING_KIT_E.getEnchantment().getMaxLevel()));
			
		return tooltip;
	}
}
