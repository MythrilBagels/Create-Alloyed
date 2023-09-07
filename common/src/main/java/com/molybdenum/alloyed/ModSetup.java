package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModCompatBlocks;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;

public class ModSetup {
    public static void register() {
        ModItems.register();
        ModBlocks.register();
        ModCompatItems.register();
        ModCompatBlocks.register();
        ModSoundEvents.register();
    }
}
