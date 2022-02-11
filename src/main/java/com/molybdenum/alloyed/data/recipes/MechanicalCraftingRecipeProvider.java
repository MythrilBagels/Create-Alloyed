package com.molybdenum.alloyed.data.recipes;

import com.google.common.base.Supplier;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.UnaryOperator;

public class MechanicalCraftingRecipeProvider extends CreateRecipeProvider {

    GeneratedRecipe STEEL_SHEARS = create(ModItems.STEEL_SHEARS::get).recipe(b -> b
            .patternLine(" #")
            .patternLine("# ")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
    );

    public MechanicalCraftingRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder(result);
    }

    class GeneratedRecipeBuilder {

        private String suffix;
        private Supplier<ItemLike> result;
        private int amount;

        public GeneratedRecipeBuilder(Supplier<ItemLike> result) {
            this.suffix = "";
            this.result = result;
            this.amount = 1;
        }

        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipe recipe(UnaryOperator<MechanicalCraftingRecipeBuilder> builder) {
            return register(consumer -> {
                MechanicalCraftingRecipeBuilder b =
                        builder.apply(MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));
                ResourceLocation location = Alloyed.asResource("mechanical_crafting/" + result.get()
                        .asItem()
                        .getRegistryName()
                        .getPath() + suffix);
                b.build(consumer, location);
            });
        }
    }
}
