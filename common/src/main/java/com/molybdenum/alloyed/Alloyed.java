package com.molybdenum.alloyed;

import com.mojang.brigadier.CommandDispatcher;
import com.molybdenum.alloyed.base.data.CRTagGen;
import com.molybdenum.alloyed.base.data.lang.CRLangPartials;
import com.molybdenum.alloyed.base.data.recipe.RailwaysSequencedAssemblyRecipeGen;
import com.molybdenum.alloyed.base.data.recipe.RailwaysStandardRecipeGen;
import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.item.ModItemGroup;
import com.molybdenum.alloyed.util.Utils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.LangMerger;
import com.simibubi.create.foundation.ponder.PonderLocalization;
import com.tterrag.registrate.providers.ProviderType;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;

public class Alloyed {

    public static final String MOD_ID = "alloyed";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String VERSION = findVersion();

    // Compat
    public static boolean isFarmersDelightLoaded = false;
    public static boolean isCreateDecoLoaded = false;

//    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID)
        .creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    public static void init() {
//        registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
//        registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
//        Path configDir = Utils.configDir();
//        Config.loadConfig(Config.CLIENT_CONFIG, configDir.resolve(MODID + "-client.toml"));
//        Config.loadConfig(Config.SERVER_CONFIG, configDir.resolve(MODID + "-common.toml"));

        ModSetup.register();
        finalizeRegistrate();

        isFarmersDelightLoaded = Utils.isModLoaded("farmersdelight", null);
        isCreateDecoLoaded = Utils.isModLoaded("createdeco", null);

//        registerCommands(CRCommands::register);
//        CRPackets.PACKETS.registerC2SListener();
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void gatherData(DataGenerator gen) {
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CRTagGen::generateBlockTags);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CRTagGen::generateItemTags);
        gen.addProvider(true, RailwaysSequencedAssemblyRecipeGen.create(gen));
        gen.addProvider(true, RailwaysStandardRecipeGen.create(gen));
        PonderLocalization.provideRegistrateLang(REGISTRATE);
        gen.addProvider(true, new LangMerger(gen, MOD_ID, "Alloyed", CRLangPartials.values()));
    }

    public Alloyed() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        REGISTRATE.registerEventListeners(eventBus);




        ModSoundEvents.register(eventBus);
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

    @ExpectPlatform
    public static void registerCommands(BiConsumer<CommandDispatcher<CommandSourceStack>, Boolean> consumer) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
        throw new AssertionError();
    }
}
