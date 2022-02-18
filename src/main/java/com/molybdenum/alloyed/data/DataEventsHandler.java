package com.molybdenum.alloyed.data;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModPonders;
import com.molybdenum.alloyed.common.registry.ModAdvancements;
import com.molybdenum.alloyed.data.recipes.MechanicalCraftingRecipes;
import com.molybdenum.alloyed.data.registry.ModAdvancementProvider;
import com.molybdenum.alloyed.data.registry.ModLootModifiers;
import com.molybdenum.alloyed.data.registry.ModProcessingRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataEventsHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void gatherData(GatherDataEvent event) {
        Alloyed.LOGGER.info("Gathering data for Alloyed ...");
        DataGenerator generator = event.getGenerator();

        // Register ponders and ponder lang
        ModPonders.register();
        ModPonders.registerLang();
        // Register processing recipes
        MechanicalCraftingRecipes.register(generator);
        ModProcessingRecipes.registerAllProcessingProviders(generator);
        // Register loot modifiers
        ModLootModifiers.register(generator);
        // Register advancements
        ModAdvancements.register();
        ModAdvancementProvider.register(generator);

        Alloyed.LOGGER.info("Finished gathering data for Alloyed");
    }
}
