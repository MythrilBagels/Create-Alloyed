package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModItemProperties;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.client.registry.ModPonders;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AlloyedClient {
    public static void onClientInit(IEventBus modEventBus) {
        modEventBus.addListener(AlloyedClient::clientSetup);
        ModPartialModels.register();
    }

    public static void clientSetup(final FMLClientSetupEvent event) {
        // Set up steel fishing rod
        ModItemProperties.register();
        // Register ponders
        ModPonders.register();
    }
}
