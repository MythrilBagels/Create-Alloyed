package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.ponder.BronzeBellPonder;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.foundation.ponder.PonderLocalization;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;

import static com.molybdenum.alloyed.Alloyed.REGISTRATE;

public class ModPonders {
    private static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Alloyed.MOD_ID);

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModPonders!");
        HELPER.forComponents(ModBlocks.BRONZE_BELL)
                .addStoryBoard("bronze_bell/decoration", BronzeBellPonder::decoration, AllPonderTags.DECORATION)
                .addStoryBoard("bronze_bell/instrument", BronzeBellPonder::instrument, AllPonderTags.DECORATION);

        PonderRegistry.TAGS.forTag(AllPonderTags.DECORATION)
                .add(ModBlocks.BRONZE_BELL);
    }

    public static void registerLang() {
        PonderLocalization.provideRegistrateLang(REGISTRATE);
    }
}