package com.molybdenum.alloyed.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
    // Was renamed from ModConfig to Config since Forge already has a class called ModConfig

    public static final ForgeConfigSpec.Builder SERVER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SERVER_CONF;

    // Recipe Settings
    public static ForgeConfigSpec.ConfigValue<Boolean> OVERRIDE_STEAMPOWERED_RECIPES;

    static {
        SERVER.comment("Recipe Settings").push("recipe");
        OVERRIDE_STEAMPOWERED_RECIPES = SERVER
                .comment("Overrides Create: Steam Powered's bronze recipes to use bronze instead of brass. False by default.")
                .define("overrideSteamPoweredRecipes", false);
        SERVER.pop();

        // Finished building config!
        SERVER_CONF = SERVER.build();
    }

}
