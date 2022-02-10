package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.loot.SteelShearsModifier;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.client.registry.ModPonders;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventsHandler {

    // Datagen
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void gatherData(GatherDataEvent event) {
        Alloyed.LOGGER.debug("Gathering data for Alloyed ...");

        ModBlocks.fixBronzeLang();
        ModPonders.safeRegister();
        ModPonders.registerLang();

        Alloyed.LOGGER.debug("Finished gathering data for Alloyed");
    }

    // Global loot modifiers
    @SubscribeEvent
    public static void registerModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

        String[] allModifierLocations = { // Easier to copy+paste
                "alloyed:acacia_leaves_shears",
                "alloyed:azalea_leaves_shears",
                "alloyed:birch_leaves_shears",
                "alloyed:cobweb_shears",
                "alloyed:dark_oak_leaves_shears",
                "alloyed:dead_bush_shears",
                "alloyed:fern_shears",
                "alloyed:flowering_azalea_leaves_shears",
                "alloyed:glow_lichen_shears",
                "alloyed:jungle_leaves_shears",
                "alloyed:large_fern_shears",
                "alloyed:nether_sprouts_shears",
                "alloyed:oak_leaves_shears",
                "alloyed:seagrass_shears",
                "alloyed:spruce_leaves_shears",
                "alloyed:tall_grass_shears",
                "alloyed:tall_seagrass_shears",
                "alloyed:twisting_vines_shears",
                "alloyed:vine_shears",
                "alloyed:weeping_vines_shears"
        };

        for (String location : allModifierLocations) {
            event.getRegistry().register(new SteelShearsModifier.Serializer().setRegistryName(location));
        }
    }
}
