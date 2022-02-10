package com.molybdenum.alloyed.client;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModItemProperties;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.client.registry.ModPonders;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventsHandler {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        Alloyed.LOGGER.debug("Entering client setup for Alloyed ...");

        // Set up steel fishing rod
        ModItemProperties.register();
        // Set up ponder scenes
        ModPonders.safeRegister();

        Alloyed.LOGGER.debug("Finished client setup for Alloyed");
    }
}
