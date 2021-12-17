package com.molybdenum.alloyed.config.conditions;

import com.google.gson.JsonObject;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.config.Config;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

public class OverrideSteamPoweredCondition implements ICondition {
    private static final ResourceLocation NAME =
            new ResourceLocation(Alloyed.MOD_ID, "override_steampowered_recipes");
    private final boolean value;

    public OverrideSteamPoweredCondition(boolean value) {
        this.value = value;
    }

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test() {
        return Config.OVERRIDE_STEAMPOWERED_RECIPES.get() == value;
    }

    @Override
    public String toString() {
        return "override_steampowered_recipes(\"" + value + "\")";
    }

    public static class Serializer implements IConditionSerializer<OverrideSteamPoweredCondition> {

        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, OverrideSteamPoweredCondition value) {
            json.addProperty("value", value.value);
        }

        @Override
        public OverrideSteamPoweredCondition read(JsonObject json) {
            return new OverrideSteamPoweredCondition(JSONUtils.getAsBoolean(json, "override_steampowered_recipes"));
        }

        @Override
        public ResourceLocation getID() {
            return OverrideSteamPoweredCondition.NAME;
        }
    }
}
