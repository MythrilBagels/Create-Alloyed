package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.ponder.BronzeBellPonder;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.createmod.ponder.foundation.registration.*;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ModPonders {

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        Alloyed.LOGGER.debug("Registering ModPonders!");
        PonderSceneRegistrationHelper<ItemProviderEntry<?>> PONDER = helper.withKeyFunction(RegistryEntry::getId);


        PONDER.forComponents(ModBlocks.BRONZE_BELL)
                .addStoryBoard("bronze_bell/decoration", BronzeBellPonder::decoration, AllCreatePonderTags.DECORATION)
                .addStoryBoard("bronze_bell/instrument", BronzeBellPonder::instrument, AllCreatePonderTags.DECORATION);
    }

    public static void registerLang() {
        Alloyed.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            BiConsumer<String, String> langConsumer = provider::add;
            PonderIndex.getLangAccess().provideLang(Alloyed.MOD_ID, langConsumer);
        });
    }
}