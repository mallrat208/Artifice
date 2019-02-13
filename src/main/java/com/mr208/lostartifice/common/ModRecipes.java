package com.mr208.lostartifice.common;

import com.mr208.lostartifice.LostArtifice;
import com.mr208.lostartifice.common.recipes.RecipeUpgrade;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@EventBusSubscriber(modid =LostArtifice.modID)
public class ModRecipes
{
	public static ArrayList<IRecipe> registeredRecipes = new ArrayList<>();
	
	static IRecipe recipeUpgrade;
	
	static {
		
		recipeUpgrade = new RecipeUpgrade();
		
	
	}
	
	@SubscribeEvent
	public static void onRecipeRegister(RegistryEvent.Register<IRecipe> event)
	{
		for(IRecipe recipe : registeredRecipes)
			event.getRegistry().register(recipe);
	}
}
