package com.mr208.lostartifice.common;

import com.mr208.lostartifice.LostArtifice;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = LostArtifice.modID)
public class ModConfig
{
	@Comment("Enable Item Tooltips")
	public static boolean tooltips = true;
	
	@Comment("Enable Item Flavortext")
	public static boolean flavortext = true;
	
	@Comment({"Maximum level of enchantment allowed for each enchantment via upgrades","Upgrades will cap at the values below or the enchantments max level, whichever is lover"})
	public static Upgrades upgrades = new Upgrades();
	
	public static class Upgrades
	{
		@Name("Limit Upgrades")
		public boolean limit_upgrades = true;
		@Name("Max Aqua Afinity")
		public int aqua_afinity = 1;
		@Name("Max Blast Protection")
		public int blast_protection = 3;
		@Name("Max Efficiency")
		public int efficiency = 3;
		@Name("Max Feather Falling")
		public int feather_falling = 3;
		@Name("Max Fire Protection")
		public int fire_protection = 3;
		@Name("Max Knockback")
		public int knockback = 2;
		@Name("Max Power")
		public int power = 3;
		@Name("Max Projectile Protection")
		public int projectile_protection = 3;
		@Name("Max Protection")
		public int protection = 3;
		@Name("Max Punch")
		public int punch = 2;
		@Name("Max Respiration")
		public int respiration = 3;
		@Name("Max Sharpness")
		public int sharpness = 3;
		@Name("Max Thorns")
		public int thorns = 3;
		@Name("Max Unbreaking")
		public int unbreaking = 2;
	}
}
