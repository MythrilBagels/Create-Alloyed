package com.molybdenum.alloyed.data.recipes;

import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.molybdenum.alloyed.data.providers.ModProcessingRecipes;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class PressingRecipes extends ModProcessingRecipes {

    public GeneratedRecipe BRONZE_SHEET = create("bronze_sheet", b -> b
            .require(ModTags.Items.BRONZE_INGOT)
            .output(ModItems.BRONZE_SHEET.get())
    );

    public GeneratedRecipe STEEL_SHEET = create("steel_sheet", b -> b
            .require(ModTags.Items.STEEL_INGOT)
            .output(ModItems.STEEL_SHEET.get())
    );

    public PressingRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.PRESSING;
    }
}
