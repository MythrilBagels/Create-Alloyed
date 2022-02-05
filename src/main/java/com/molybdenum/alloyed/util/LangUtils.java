package com.molybdenum.alloyed.util;

import com.molybdenum.alloyed.data.registrate.PostRegistrationHelper;
import com.simibubi.create.foundation.utility.Iterate;

public class LangUtils {

    public static void correctOxidizingMetalLang(String name, String metalLang) {
        for (boolean waxed : Iterate.falseAndTrue) {
            String waxedLangPrefix = waxed ? "Waxed " : "";
            String waxedNamePrefix = waxed ? "waxed_" : "";

            PostRegistrationHelper.addBlockLang(waxedNamePrefix + name,                waxedLangPrefix + "Block of " + metalLang);
            PostRegistrationHelper.addBlockLang(waxedNamePrefix + "exposed_" + name,   waxedLangPrefix + "Exposed " + metalLang);
            PostRegistrationHelper.addBlockLang(waxedNamePrefix + "weathered_" + name, waxedLangPrefix + "Weathered " + metalLang);
            PostRegistrationHelper.addBlockLang(waxedNamePrefix + "oxidized_" + name,  waxedLangPrefix + "Oxidized " + metalLang);
        }
    }
}
