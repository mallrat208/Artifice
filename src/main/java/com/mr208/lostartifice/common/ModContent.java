package com.mr208.lostartifice.common;

import com.mr208.lostartifice.LostArtifice;
import com.mr208.lostartifice.common.items.EnumUpgrade;
import com.mr208.lostartifice.common.items.ItemUpgrade;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid =LostArtifice.modID)
public class ModContent
{
	public static ArrayList<Item> registeredItems = new ArrayList<>();
	protected static ArrayList<ItemUpgrade> upgradeItems = new ArrayList<>();
	
	static {
		
		for(EnumUpgrade enumUpgrade:EnumUpgrade.values())
		{
			//This one is Skipped, it always appears with Sharpness
			if(enumUpgrade == EnumUpgrade.SHARPENING_KIT_E)
				continue;
			
			upgradeItems.add(new ItemUpgrade(enumUpgrade));
		}
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		for(Item item : registeredItems)
			event.getRegistry().register(item);
	}
}
