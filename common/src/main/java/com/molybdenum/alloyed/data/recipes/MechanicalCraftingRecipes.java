package com.molybdenum.alloyed.data.recipes;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.molybdenum.alloyed.data.util.RecipeUtils.Lang;
import static com.molybdenum.alloyed.data.util.RecipeUtils.MechanicalCrafting;

public class MechanicalCraftingRecipes extends CreateRecipeProvider {

    // Steel toolset
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
    public GeneratedRecipe STEEL_KNIFE = create(ModCompatItems.STEEL_KNIFE::get).recipe(b -> b
            .patternLine(" #")
            .patternLine("/ ")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
            .key('/', Ingredient.of(ModTags.Generic.STICK))
            .whenModLoaded("farmersdelight")
    );

    // Steel armour
    public GeneratedRecipe STEEL_HELMET = create(ModItems.STEEL_HELMET::get).recipe(b -> b
            .patternLine("###")
            .patternLine("# #")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
    );
    public GeneratedRecipe STEEL_CHESTPLATE = create(ModItems.STEEL_CHESTPLATE::get).recipe(b -> b
            .patternLine("# #")
            .patternLine("###")
            .patternLine("###")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
    );
    public GeneratedRecipe STEEL_LEGGINGS = create(ModItems.STEEL_LEGGINGS::get).recipe(b -> b
            .patternLine("###")
            .patternLine("# #")
            .patternLine("# #")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
    );
    public GeneratedRecipe STEEL_BOOTS = create(ModItems.STEEL_BOOTS::get).recipe(b -> b
            .patternLine("# #")
            .patternLine("# #")
            .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT))
    );

    public MechanicalCraftingRecipes(FabricDataGenerator generator) {
        super(generator);
    }

    public static void register(FabricDataGenerator generator) {
        generator.addProvider(true, new MechanicalCraftingRecipes(generator));
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
                        builder.apply( MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));

                ResourceLocation resultLocation = Registry.ITEM.getKey(result.get().asItem());
                b.build(consumer, Alloyed.asResource("mechanical_crafting/" +
                        resultLocation.getPath() + suffix));
            });
        }
    }



    @Override
    public String getName() {
        return "Create: Alloyed's Mechanical Crafting Recipes";
    }
}
