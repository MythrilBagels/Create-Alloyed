package com.molybdenum.alloyed.client;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModItemProperties;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.client.registry.ModPonders;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventsHandler {
    // Proxy class for client setup
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        Alloyed.LOGGER.debug("Entering client setup for Alloyed ...");

        // Set up steel fishing rod
        ModItemProperties.register();
        // Set up ponder scenes
        ModPonders.register();

        Alloyed.LOGGER.debug("Finished client setup for Alloyed");
    }
}
