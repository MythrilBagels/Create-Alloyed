package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModItemProperties;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.client.registry.ModPonders;

public class AlloyedClient {
    public static void init() {
        ModPartialModels.register();

        // Set up steel fishing rod
        ModItemProperties.register();
        // Register ponders
        ModPonders.register();
    }
}
