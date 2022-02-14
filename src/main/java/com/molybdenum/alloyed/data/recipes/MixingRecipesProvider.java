package com.molybdenum.alloyed.data.recipes;

import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.molybdenum.alloyed.data.registry.ModProcessingRecipeProviders;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.contraptions.processing.HeatCondition;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class MixingRecipesProvider extends ModProcessingRecipeProviders {

    public GeneratedRecipe BRONZE_INGOT = create("bronze_ingot", b -> b
            .require(Items.COPPER_INGOT)
            .require(ModTags.Generic.ZINC_NUGGET)
            .require(ModTags.Generic.ZINC_NUGGET)
            .require(ModTags.Generic.ZINC_NUGGET)
            .require(AllItems.CINDER_FLOUR.get())
            .output(ModItems.BRONZE_INGOT.get())
            .requiresHeat(HeatCondition.HEATED)
    );

    public GeneratedRecipe BRONZE_INGOTx3 = create("bronze_ingot_x3", b -> b
            .require(Items.COPPER_INGOT)
            .require(Items.COPPER_INGOT)
            .require(Items.COPPER_INGOT)
            .require(ModTags.Generic.ZINC_INGOT)
            .require(AllItems.CINDER_FLOUR.get())
            .require(AllItems.CINDER_FLOUR.get())
            .require(AllItems.CINDER_FLOUR.get())
            .output(ModItems.BRONZE_INGOT.get(), 3)
            .requiresHeat(HeatCondition.HEATED)
    );

    public GeneratedRecipe STEEL_INGOT = create("steel_ingot", b -> b
            .require(Items.IRON_INGOT)
            .require(Items.IRON_INGOT)
            .require(Items.IRON_INGOT)
            .require(Ingredient.of(Items.COAL, Items.CHARCOAL))
            .output(ModItems.STEEL_INGOT.get(), 3)
            .requiresHeat(HeatCondition.HEATED)
            .duration(200)
    );

    public MixingRecipesProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.MIXING;
    }

    @Override
    public @NotNull String getName() {
        return "Create: Alloyed's Mixing Recipes";
    }
}
