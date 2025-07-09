package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber(modid = Alloyed.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonEventsHandler {

    // Common setup
    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {
        if (Alloyed.isFarmersDelightLoaded)
            FarmersDelightCompat.steelKnifeDispenseBehaviour();
    }

    @SubscribeEvent
    public static void addBlocks(final BlockEntityTypeAddBlocksEvent event) {
        event.modify(AllBlockEntityTypes.ENCASED_COGWHEEL.getKey(), ModBlocks.STEEL_ENCASED_COGWHEEL.get(), ModBlocks.BRONZE_ENCASED_COGWHEEL.get());
        event.modify(AllBlockEntityTypes.ENCASED_LARGE_COGWHEEL.getKey(), ModBlocks.STEEL_ENCASED_LARGE_COGWHEEL.get(),  ModBlocks.BRONZE_ENCASED_LARGE_COGWHEEL.get());
        event.modify(AllBlockEntityTypes.ENCASED_SHAFT.getKey(), ModBlocks.STEEL_ENCASED_SHAFT.get(), ModBlocks.BRONZE_ENCASED_SHAFT.get());
    }
}
