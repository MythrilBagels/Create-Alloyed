package com.molybdenum.alloyed.data.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.molybdenum.alloyed.data.providers.ModAdvancementProvider.NamedAdvancementBuilder;
import com.molybdenum.alloyed.data.util.RecipeUtils;
import com.simibubi.create.Create;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.Advancement;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ModAdvancements {

    public static final NamedAdvancementBuilder BRONZE_INGOT = advancement("bronze_ingot", advancement -> advancement
            .parent(Create.asResource("blaze_burner"))
            .addCriterion("has_bronze_ingot", RegistrateRecipeProvider.has(ModTags.Items.BRONZE_INGOT))
    ).displayInfo(builder -> builder
            .icon(ModItems.BRONZE_INGOT.get())
            .title("The Other Copper Alloy")
            .description("Acquire some Bronze, a decorative and sonorous metal.")
    ).save();

    public static final NamedAdvancementBuilder STEEL_INGOT = advancement("steel_ingot", advancement -> advancement
            .parent(Create.asResource("blaze_burner"))
            .addCriterion("has_steel_ingot", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
    ).displayInfo(builder -> builder
            .icon(ModItems.STEEL_INGOT.get())
            .title("Industrial Iron")
            .description("Acquire some Steel, a hard and robust metal.")
    ).save();

    public static final NamedAdvancementBuilder BRONZE_INSTRUMENTS = advancement("bronze_instruments", advancement -> advancement
            .parent(Alloyed.asResource("bronze_ingot"))
            .addCriterion("has_bronze_ingot", RegistrateRecipeProvider.has(ModTags.Items.BRONZE_INSTRUMENTS))
    ).displayInfo(builder -> builder
            .icon(ModBlocks.BRONZE_BELL.get())
            .title("Metallic Music")
            .description("Craft a Bronze instrument. Time to get composing!")
    ).save();

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModAdvancements!");
    }

    // Utilities
    private static NamedAdvancementBuilder advancement(String name, Function<Advancement.Builder, Advancement.Builder> builder) {
        return new NamedAdvancementBuilder(Alloyed.asResource(name), builder.apply(Advancement.Builder.advancement()));
    }
}
