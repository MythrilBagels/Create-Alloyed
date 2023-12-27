package com.molybdenum.alloyed.data;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModPonders;
import com.molybdenum.alloyed.common.content.items.SteelUpgradeSmithingTemplateItem;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.data.providers.ModAdvancementProvider;
import com.molybdenum.alloyed.data.providers.ModProcessingRecipes;
import com.molybdenum.alloyed.data.recipes.MechanicalCraftingRecipes;
import com.molybdenum.alloyed.data.registry.ModAdvancements;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
        // Register Creative Tab lang
        ModCreativeModeTab.registerLang();
        // Register Steel Upgrade Template lang
        SteelUpgradeSmithingTemplateItem.registerLang();

        if (event.includeServer()) {
            // Register processing recipes
            MechanicalCraftingRecipes.register(generator);
            ModProcessingRecipes.registerAllProcessingProviders(generator, generator.getPackOutput());

            // Register advancements
            ModAdvancements.register();
            ModAdvancementProvider.register(generator);
        }

        Alloyed.LOGGER.debug("Finished gathering data for Alloyed");
    }
}
