package com.molybdenum.alloyed.data.recipes;

import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.molybdenum.alloyed.data.registry.ModProcessingRecipeProviders;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;

public class PressingRecipesProvider extends ModProcessingRecipeProviders {

    public GeneratedRecipe BRONZE_SHEET = create("bronze_sheet", b -> b
            .require(ModTags.Items.BRONZE_INGOT)
            .output(ModItems.BRONZE_SHEET.get())
    );

    public GeneratedRecipe STEEL_SHEET = create("steel_sheet", b -> b
            .require(ModTags.Items.STEEL_INGOT)
            .output(ModItems.STEEL_SHEET.get())
    );

    public PressingRecipesProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.PRESSING;
    }

    @Override
    public @NotNull String getName() {
        return "Create: Alloyed's Pressing Recipes";
    }
}
