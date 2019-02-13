package com.mr208.lostartifice.common.items;

import com.mr208.lostartifice.common.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;

public enum EnumUpgrade {
	SHARPENING_KIT("toolweapon", Enchantments.SHARPNESS, ModConfig.upgrades.sharpness),
	SHARPENING_KIT_E("toolweapon", Enchantments.EFFICIENCY, ModConfig.upgrades.efficiency),
	REINFORCEMENT("universal", Enchantments.UNBREAKING, ModConfig.upgrades.unbreaking),
	REINFORCED_LIMBS("bow", Enchantments.PUNCH, ModConfig.upgrades.punch),
	PLAITED_STRING("bow", Enchantments.POWER, ModConfig.upgrades.power),
	COUNTERWEIGHT("weapon", Enchantments.KNOCKBACK, ModConfig.upgrades.knockback),
	ARMOR_SPIKES("armor", Enchantments.THORNS, ModConfig.upgrades.thorns),
	LAMINATED_PADDING("armor", Enchantments.PROTECTION, ModConfig.upgrades.protection),
	QUILTED_COVER("armor", Enchantments.PROJECTILE_PROTECTION, ModConfig.upgrades.projectile_protection),
	ELASTIC_SOLES("boot", Enchantments.FEATHER_FALLING, ModConfig.upgrades.feather_falling),
	FIREDAMP("armor", Enchantments.FIRE_PROTECTION, ModConfig.upgrades.fire_protection),
	ELASTIC_LAYERING("armor", Enchantments.BLAST_PROTECTION, ModConfig.upgrades.blast_protection),
	SCUBA_TANK("helmet", Enchantments.RESPIRATION, ModConfig.upgrades.respiration),
	DIVE_KIT("helmet", Enchantments.AQUA_AFFINITY, ModConfig.upgrades.aqua_afinity);
	
	private String type;
	private Enchantment enchantment;
	private int maxLevel;
	
	EnumUpgrade(String type, Enchantment enchantment, int maxLevel)
	{
		this.type = type;
		this.enchantment = enchantment;
		this.maxLevel = maxLevel;
	}
	
	public String getName()
	{
		return this.name().toLowerCase();
	}
	
	public String getType()
	{
		return type;
	}
	
	public Enchantment getEnchantment()
	{
		return enchantment;
	}
	
	public int getMaxLevel()
	{
		return maxLevel;
	}
}