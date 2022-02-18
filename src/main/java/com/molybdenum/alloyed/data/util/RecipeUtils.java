package com.molybdenum.alloyed.data.util;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

import java.util.function.UnaryOperator;

public class RecipeUtils {

    public static class Crafting {

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> metalBlockRecipe(ITag.INamedTag<Item> metalTag, String ingotName) {
            return (ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', metalTag)
                        .unlockedBy("has_" + ingotName, Criterion.has(metalTag))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            };
        }

        public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> metalIngotDecompactingRecipe(ITag.INamedTag<Item> blockTag, String blockName) {
            return (ctx, prov) -> {
                ShapelessRecipeBuilder.shapeless(ctx.get(), 9)
                        .requires(blockTag)
                        .unlockedBy("has_" + blockName, Criterion.has(blockTag))
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

        public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> steelToolRecipe(IItemProvider ironToolEquivalent) {
            return (ctx, prov) -> {
                SmithingRecipeBuilder
                        .smithing(Ingredient.of(ironToolEquivalent), Ingredient.of(ModTags.Items.STEEL_INGOT), ctx.get())
                        .unlocks("has_steel_ingot", Criterion.has(ModTags.Items.STEEL_INGOT))
                        .save(prov, Alloyed.asResource("smithing/" + ctx.getName()));
            };
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

    public static class Criterion {
        public static InventoryChangeTrigger.Instance has(ITag<Item> pTag) {
            return inventoryTrigger(ItemPredicate.Builder.item().of(pTag).build());
        }

        private static InventoryChangeTrigger.Instance inventoryTrigger(ItemPredicate... pPredicate) {
            return InventoryChangeTrigger.Instance.hasItems(pPredicate);
        }
    }
}

