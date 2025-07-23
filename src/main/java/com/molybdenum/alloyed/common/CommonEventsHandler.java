package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventsHandler {

    // Common setup
    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {
        if (Alloyed.isFarmersDelightLoaded)
            FarmersDelightCompat.steelKnifeDispenseBehaviour();
    }
}
