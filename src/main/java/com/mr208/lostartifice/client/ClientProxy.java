package com.mr208.lostartifice.client;

import com.mr208.lostartifice.LostArtifice;
import com.mr208.lostartifice.common.CommonProxy;
import com.mr208.lostartifice.common.ModContent;
import com.mr208.lostartifice.common.items.ItemUpgrade;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid =LostArtifice.modID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy
{

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item: ModContent.registeredItems)
		{
			if(item instanceof ItemUpgrade)
			{
				ItemUpgrade upgrade = (ItemUpgrade) item;
				ResourceLocation loc = new ResourceLocation(LostArtifice.modID, "upgrade/"+ upgrade.getUpgradeEnum().getName());
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(loc,"inventory"));
			}
			else
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(),"inventory"));
			}
		}
	}
}
