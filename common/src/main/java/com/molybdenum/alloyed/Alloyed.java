package com.molybdenum.alloyed;

import com.mojang.brigadier.CommandDispatcher;
import com.molybdenum.alloyed.base.data.CRTagGen;
import com.molybdenum.alloyed.base.data.lang.CRLangPartials;
import com.molybdenum.alloyed.base.data.recipe.RailwaysSequencedAssemblyRecipeGen;
import com.molybdenum.alloyed.base.data.recipe.RailwaysStandardRecipeGen;
import com.molybdenum.alloyed.client.registry.ModPonders;
import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import com.molybdenum.alloyed.common.item.ModItemGroup;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.data.providers.ModAdvancementProvider;
import com.molybdenum.alloyed.data.providers.ModProcessingRecipes;
import com.molybdenum.alloyed.data.recipes.MechanicalCraftingRecipes;
import com.molybdenum.alloyed.util.Utils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.LangMerger;
import com.simibubi.create.foundation.ponder.PonderLocalization;
import com.tterrag.registrate.providers.ProviderType;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.registry.ModAdvancements;

import java.util.function.BiConsumer;

public class Alloyed {

    public static final String MOD_ID = "alloyed";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String VERSION = findVersion();

    // Compat
    public static boolean isFarmersDelightLoaded = false;
    public static boolean isCreateDecoLoaded = false;

    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID)
        .creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    public static void init() {
        ModSetup.register();
        finalizeRegistrate();

        isFarmersDelightLoaded = Utils.isModLoaded("farmersdelight", null);
        isCreateDecoLoaded = Utils.isModLoaded("createdeco", null);

        if (isFarmersDelightLoaded)
            FarmersDelightCompat.steelKnifeDispenseBehaviour();
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void gatherData(FabricDataGenerator gen) {
        Alloyed.LOGGER.debug("Gathering data for Alloyed ...");

        // Correct bronze blocks
        ModBlocks.fixBronzeBlocks();
        // Register ponders and generate ponder lang
        ModPonders.register();
        ModPonders.registerLang();

        // Register processing recipes
        MechanicalCraftingRecipes.register(gen);
        ModProcessingRecipes.registerAllProcessingProviders(gen);

        // Register advancements
        ModAdvancements.register();
        ModAdvancementProvider.register(gen);

        Alloyed.LOGGER.debug("Finished gathering data for Alloyed");
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

    @ExpectPlatform
    public static String findVersion() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void finalizeRegistrate() {
        throw new AssertionError();
    }
}
