package com.mr208.lostartifice.common.recipes;

import com.mr208.lostartifice.LostArtifice;
import com.mr208.lostartifice.common.ModConfig;
import com.mr208.lostartifice.common.ModRecipes;
import com.mr208.lostartifice.common.items.EnumUpgrade;
import com.mr208.lostartifice.common.items.ItemUpgrade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeUpgrade extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	public RecipeUpgrade()
	{
		this.setRegistryName(LostArtifice.modID,"upgrade_recipe");
		ModRecipes.registeredRecipes.add(this);
	}
	
	private ItemStack output = ItemStack.EMPTY;
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		this.output = ItemStack.EMPTY;
		
		int numTools = 0;
		
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if (stack.isEmpty())
				continue;
			
			if (!isItemTool(stack) && !(stack.getItem() instanceof ItemUpgrade))
				return false;
			else if (isItemTool(stack))
				numTools++;
		}
		if (numTools != 1)
			return false;
		
		// Get our target
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && isItemTool(stack))
				this.output = stack.copy();
		}
		
		int numUpgrades = 0;
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && (stack.getItem() instanceof ItemUpgrade))
				numUpgrades++;
		}
		if (numUpgrades == 0)
			return false;
		
		if (this.output.isEmpty())
			return false;
		
		// Apply the enchants
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && (stack.getItem() instanceof ItemUpgrade))
			{
				ItemUpgrade upgrade = (ItemUpgrade) stack.getItem();
				Enchantment enchant;
				int maxLevel = 0;
				
				// Sharpening Kit
				if (upgrade.getUpgradeEnum() == EnumUpgrade.SHARPENING_KIT)
				{
					if (Enchantments.SHARPNESS.canApply(this.output) && Enchantments.EFFICIENCY.canApply(this.output))
					{
						if (i % 2 == 0)
							enchant = Enchantments.SHARPNESS;
						else
							enchant = Enchantments.EFFICIENCY;
					}
					else if (Enchantments.SHARPNESS.canApply(this.output))
						enchant = Enchantments.SHARPNESS;
					else if (Enchantments.EFFICIENCY.canApply(this.output))
						enchant = Enchantments.EFFICIENCY;
					else
						return false;
					
					if (ModConfig.upgrades.limit_upgrades)
					{
						if (enchant == Enchantments.SHARPNESS)
							maxLevel = EnumUpgrade.SHARPENING_KIT.getMaxLevel();
						else
							maxLevel = EnumUpgrade.SHARPENING_KIT_E.getMaxLevel();
					}
					else
					{
						if (enchant == Enchantments.SHARPNESS)
							maxLevel = EnumUpgrade.SHARPENING_KIT.getEnchantment().getMaxLevel();
						else
							maxLevel = EnumUpgrade.SHARPENING_KIT_E.getEnchantment().getMaxLevel();
					}
				}
				else
				{
					enchant = upgrade.getUpgradeEnum().getEnchantment();
					if (ModConfig.upgrades.limit_upgrades)
						maxLevel = upgrade.getUpgradeEnum().getMaxLevel();
					else
						maxLevel = upgrade.getUpgradeEnum().getEnchantment().getMaxLevel();
				}
				
				if (enchant.canApply(this.output) && (this.output.getItem().getItemEnchantability() > 0 || this.output.getItem() == Items.SHIELD))
				{
					if (EnchantmentHelper.getEnchantmentLevel(enchant, this.output) < maxLevel)
					{
						if (EnchantmentHelper.getEnchantmentLevel(enchant, this.output) == 0)
						{
							if (this.output.getTagCompound() != null)
							{
								NBTTagList enchants = (NBTTagList) this.output.getTagCompound().getTag("ench");
								if (enchants != null && enchants.tagCount() > 0)
								{
									for (int k = 0; k < enchants.tagCount(); k++)
									{
										NBTTagCompound tag = enchants.getCompoundTagAt(k);
										
										Enchantment other = Enchantment.getEnchantmentByID(tag.getShort("id"));
										
										if (!enchant.isCompatibleWith(other))
											return false;
									}
								}
							}
							this.output.addEnchantment(enchant, 1);
						}
						else
						{
							NBTTagList enchants = (NBTTagList) this.output.getTagCompound().getTag("ench");
							for (int j = 0; j < enchants.tagCount(); j++)
							{
								NBTTagCompound tag = enchants.getCompoundTagAt(j);
								if (tag.getShort("id") == Enchantment.getEnchantmentID(enchant))
									tag.setShort("lvl", (short) (tag.getShort("lvl") + 1));
							}
						}
					}
					else
						return false;
				}
				else
					return false;
			}
		}
		
		return true;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		return this.output.copy();
	}
	
	@Override
	public boolean canFit(int width, int height)
	{
		return width * height > 2;
	}
	
	@Override
	public ItemStack getRecipeOutput()
	{
		return this.output;
	}
	
	@Override
	public boolean isDynamic()
	{
		return true;
	}
	
	@Override
	public String getGroup()
	{
		return "lostartifice:upgrade";
	}
	
	protected boolean isItemTool(ItemStack stack)
	{
		return 	stack.getItem() instanceof ItemArmor ||
				stack.getItem() instanceof ItemSword ||
				stack.getItem() instanceof ItemPickaxe ||
				stack.getItem() instanceof ItemAxe ||
				stack.getItem() instanceof ItemSpade ||
				stack.getItem() instanceof ItemHoe ||
				stack.getItem() instanceof ItemTool ||
				stack.getItem().isShield(stack,null);
	}
}
