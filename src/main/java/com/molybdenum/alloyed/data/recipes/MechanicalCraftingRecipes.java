package com.molybdenum.alloyed.data.recipes;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.molybdenum.alloyed.data.util.RecipeUtils.*;

public class MechanicalCraftingRecipes extends CreateRecipeProvider {

    public GeneratedRecipe STEEL_AXE = create(ModItems.STEEL_AXE::get).recipe(MechanicalCrafting.steelToolRecipe(Lang.AXE_PATTERN));
    public GeneratedRecipe STEEL_HOE = create(ModItems.STEEL_HOE::get).recipe(MechanicalCrafting.steelToolRecipe(Lang.HOE_PATTERN));
    public GeneratedRecipe STEEL_PICKAXE = create(ModItems.STEEL_PICKAXE::get).recipe(MechanicalCrafting.steelToolRecipe(Lang.PICKAXE_PATTERN));
    public GeneratedRecipe STEEL_SHOVEL = create(ModItems.STEEL_SHOVEL::get).recipe(MechanicalCrafting.steelToolRecipe(Lang.SHOVEL_PATTERN));
    public GeneratedRecipe STEEL_SWORD = create(ModItems.STEEL_SWORD::get).recipe(MechanicalCrafting.steelToolRecipe(Lang.SWORD_PATTERN));
    public GeneratedRecipe STEEL_SHEARS = create(ModItems.STEEL_SHEARS::get).recipe(b -> b
            .patternLine(" #")
            .patternLine("# ")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
    );
    public GeneratedRecipe STEEL_FISHING_ROD = create(ModItems.STEEL_FISHING_ROD::get).recipe(b -> b
            .patternLine("  #")
            .patternLine(" #I")
            .patternLine("# I")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
            .key('I', Items.STRING)
    );

    public MechanicalCraftingRecipes(DataGenerator generator) {
        super(generator);
    }

    public static void register(DataGenerator generator) {
        generator.addProvider(new MechanicalCraftingRecipes(generator));
    }

    GeneratedRecipeBuilder create(Supplier<IItemProvider> result) {
        return new GeneratedRecipeBuilder(result);
    }

    class GeneratedRecipeBuilder {

        private String suffix;
        private Supplier<IItemProvider> result;
        private int amount;

        public GeneratedRecipeBuilder(Supplier<IItemProvider> result) {
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
                        builder.apply( MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));
                ResourceLocation location = Alloyed.asResource("mechanical_crafting/" + result.get()
                        .asItem()
                        .getRegistryName()
                        .getPath() + suffix);
                b.build(consumer, location);
            });
        }
    }



    @Override
    public String getName() {
        return "Create: Alloyed's Mechanical Crafting Recipes";
    }
}
