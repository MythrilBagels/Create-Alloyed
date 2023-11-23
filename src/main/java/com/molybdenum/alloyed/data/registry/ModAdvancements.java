package com.molybdenum.alloyed.data.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.molybdenum.alloyed.data.providers.ModAdvancementProvider;
import com.simibubi.create.Create;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.Advancement;

import java.util.function.Function;

import static com.molybdenum.alloyed.data.advancements.DisplayInfoBuilder.create;

@SuppressWarnings("unused")
public class ModAdvancements {

    public static final Advancement BRONZE_INGOT = advancement("bronze_ingot", advancement -> advancement
            .parent(Create.asResource("burner"))
            .addCriterion("has_bronze_ingot", RegistrateRecipeProvider.has(ModTags.Items.BRONZE_INGOT))
            .display(create("bronze_ingot")
                    .icon(ModItems.BRONZE_INGOT.get())
                    .title("The Other Copper Alloy")
                    .description("Acquire some Bronze, a decorative and sonorous metal.")
                    .build())
    );

    public static final Advancement STEEL_INGOT = advancement("steel_ingot", advancement -> advancement
            .parent(Create.asResource("burner"))
            .addCriterion("has_steel_ingot", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
            .display(create("steel_ingot")
                    .icon(ModItems.STEEL_INGOT.get())
                    .title("Industrial Iron")
                    .description("Acquire some Steel, a hard and robust metal.")
                    .build())
    );

    public static final Advancement BRONZE_INSTRUMENTS = advancement("bronze_instruments", advancement -> advancement
            .parent(Alloyed.asResource("bronze_ingot"))
            .addCriterion("has_bronze_instrument", RegistrateRecipeProvider.has(ModTags.Items.BRONZE_INSTRUMENTS))
            .display(create("bronze_instruments")
                    .icon(ModBlocks.BRONZE_BELL.get())
                    .title("Metallic Music")
                    .description("Craft a Bronze instrument. Time to get composing!")
                    .build())
    );



    public static void register() {
        Alloyed.LOGGER.debug("Registering ModAdvancements!");
    }

    // Utilities
    private static Advancement advancement(String name, Function<Advancement.Builder, Advancement.Builder> builderFunc) {
        Advancement output = builderFunc
                .apply(Advancement.Builder.advancement())
                .build(Alloyed.asResource(name));
        ModAdvancementProvider.addAdvancement(output);
        return output;
    }
}
