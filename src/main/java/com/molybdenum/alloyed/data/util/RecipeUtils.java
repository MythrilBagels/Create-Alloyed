package com.molybdenum.alloyed.data.util;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class RecipeUtils {

    public static class Crafting {

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> metalBlockRecipe(TagKey<Item> metalTag) {
            return (ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', metalTag)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(metalTag))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            };
        }

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> stairs(ItemLike item) {
            return (ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 4)
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', item)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(item))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            };
        }

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> slab(ItemLike item) {
            return (ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 6)
                        .pattern("###")
                        .define('#', item)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(item))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            };
        }

        public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> metalIngotDecompactingRecipe(TagKey<Item> blockTag) {
            return (ctx, prov) -> {
                ShapelessRecipeBuilder.shapeless(ctx.get(), 9)
                        .requires(blockTag)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(blockTag))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName() + "_from_decompacting"));
            };
        }

    }

    public static class MechanicalCrafting {

        public static UnaryOperator<MechanicalCraftingRecipeBuilder> steelToolRecipe(String[] pattern) {
            return builder -> builder
                    .patternLine(pattern[0])
                    .patternLine(pattern[1])
                    .patternLine(pattern[2])
                    .key('#', Ingredient.of(ModTags.Items.STEEL_INGOT)) // Always use '#' for steel ingots (no need to worry if using prebuilt lang)
                    .key('/', Ingredient.of(ModTags.Generic.STICK)); // Always use '/' for sticks (no need to worry if using prebuilt lang)
        }

    }

    public static class Smithing {

        public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> steelItemRecipe(ItemLike ironEquivalent) {
            return (ctx, prov) -> {
                UpgradeRecipeBuilder
                        .smithing(Ingredient.of(ironEquivalent), Ingredient.of(ModTags.Items.STEEL_INGOT), ctx.get())
                        .unlocks("has_ingredient", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
                        .save(prov, Alloyed.asResource("smithing/" + ctx.getName()));
            };
        }

    }

    public static class Stonecutting {

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> customDefaultLang(TagKey<Item> source, int count, String sourceName) {
            return (ctx, prov) -> {
                SingleItemRecipeBuilder
                        .stonecutting(Ingredient.of(source), ctx.get(), count)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(source))
                        .save(prov, Alloyed.asResource("stonecutting/" + ctx.getName() + "_from_" + sourceName));
            };
        }

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> customDefaultLang(ItemLike source, int count, String sourceName) {
            return (ctx, prov) -> {
                SingleItemRecipeBuilder
                        .stonecutting(Ingredient.of(source), ctx.get(), count)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(source))
                        .save(prov, Alloyed.asResource("stonecutting/" + ctx.getName() + "_from_" + sourceName));
            };
        }

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> customDefaultLang(ItemLike source, int count) {
            return customDefaultLang(source, count, Objects.requireNonNull(source.asItem().getRegistryName()).getPath());
        }

    }


    public static class Lang {
        public static final String[] AXE_PATTERN = {
                "## ",
                "#/ ",
                " / "
        };
        public static final String[] HOE_PATTERN = {
                "## ",
                " / ",
                " / "
        };
        public static final String[] PICKAXE_PATTERN = {
                "###",
                " / ",
                " / "
        };
        public static final String[] SHOVEL_PATTERN = {
                " # ",
                " / ",
                " / "
        };
        public static final String[] SWORD_PATTERN = {
                " # ",
                " # ",
                " / "
        };
    }

    public static <T extends Block> void toFunction(DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov, NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> factory) {
        factory.accept(ctx, prov);
    }
}
