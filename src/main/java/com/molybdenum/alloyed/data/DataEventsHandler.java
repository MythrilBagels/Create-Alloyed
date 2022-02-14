package com.molybdenum.alloyed.data;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModPonders;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.data.recipes.MechanicalCraftingRecipes;
import com.molybdenum.alloyed.data.registry.ModLootModifiers;
import com.molybdenum.alloyed.data.registry.ModProcessingRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataEventsHandler {
    // Datagen
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void gatherData(GatherDataEvent event) {
        Alloyed.LOGGER.debug("Gathering data for Alloyed ...");
        DataGenerator generator = event.getGenerator();

        // Correct bronze blocks
        ModBlocks.fixBronzeBlocks();
        // Register ponders and generate ponder lang
        ModPonders.register();
        ModPonders.registerLang();
        // Register processing recipes
        MechanicalCraftingRecipes.register(generator);
        ModProcessingRecipes.registerAllProcessingProviders(generator);
        // Register loot modifiers
        ModLootModifiers.register(generator);

        Alloyed.LOGGER.debug("Finished gathering data for Alloyed");
    }
}
