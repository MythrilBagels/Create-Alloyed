package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.ponder.BronzeBellPonder;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.ponder.PonderLocalization;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.content.PonderTag;

public class ModPonders {
    private static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Alloyed.MOD_ID);
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate();

    private static boolean hasRegistered = false; // Not strictly necessary, but I'd rather just have it here.

    private static void register() {
        HELPER.forComponents(ModBlocks.BRONZE_BELL)
                .addStoryBoard("bronze_bell/decoration", BronzeBellPonder::decoration, PonderTag.DECORATION)
                .addStoryBoard("bronze_bell/instrument", BronzeBellPonder::instrument, PonderTag.DECORATION);

        PonderRegistry.TAGS.forTag(PonderTag.DECORATION)
                .add(ModBlocks.BRONZE_BELL);
    }

    public static void registerLang() {
        PonderLocalization.provideRegistrateLang(REGISTRATE);
    }

    public static void safeRegister() {
        if (hasRegistered) {
            Alloyed.LOGGER.warn("Tried to register ModPonders twice!"); // Unlikely to happen but hey
        } else {
            Alloyed.LOGGER.debug("Registering Alloyed Ponders");
            register();
            hasRegistered = true;
        }
    }
}
