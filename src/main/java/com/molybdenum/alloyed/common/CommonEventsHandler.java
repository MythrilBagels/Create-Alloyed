package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import com.simibubi.create.foundation.block.CopperRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.function.Supplier;

import static com.molybdenum.alloyed.common.registry.ModBlocks.*;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventsHandler {

    // Common setup
    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {
        if (Alloyed.isFarmersDelightLoaded)
            FarmersDelightCompat.steelKnifeDispenseBehaviour();
        CopperRegistries.addWeathering((Supplier<Block>) CUT_BRONZE.get(0), (Supplier<Block>) CUT_EXPOSED_BRONZE.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) CUT_EXPOSED_BRONZE.get(0), (Supplier<Block>) CUT_WEATHERED_BRONZE.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) CUT_WEATHERED_BRONZE.get(0), (Supplier<Block>) CUT_OXIDIZED_BRONZE.get(0));

        CopperRegistries.addWeathering((Supplier<Block>) BRONZE_PILLAR.get(0), (Supplier<Block>) EXPOSED_BRONZE_PILLAR.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) EXPOSED_BRONZE_PILLAR.get(0), (Supplier<Block>) WEATHERED_BRONZE_PILLAR.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) WEATHERED_BRONZE_PILLAR.get(0), (Supplier<Block>) OXIDIZED_BRONZE_PILLAR.get(0));
    }
}
