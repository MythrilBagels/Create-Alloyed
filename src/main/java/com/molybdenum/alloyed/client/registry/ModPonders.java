package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.ponder.BronzeBellPonder;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.ponder.PonderLocalization;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.content.PonderTag;

public class ModPonders {
    private static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Alloyed.MOD_ID);
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate();

    public static void register() {
        Alloyed.LOGGER.info("Registering ModPonders!");
        HELPER.forComponents(ModBlocks.BRONZE_BELL)
                .addStoryBoard("bronze_bell/decoration", BronzeBellPonder::decoration, PonderTag.DECORATION)
                .addStoryBoard("bronze_bell/instrument", BronzeBellPonder::instrument, PonderTag.DECORATION);

        PonderRegistry.TAGS.forTag(PonderTag.DECORATION)
                .add(ModBlocks.BRONZE_BELL);
    }

    public static void registerLang() {
        PonderLocalization.provideRegistrateLang(REGISTRATE);
    }
}
