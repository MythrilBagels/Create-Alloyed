package com.molybdenum.alloyed.data.util;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class RecipeUtils {
    public static class Crafting {

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> metalBlockRecipe(Tag.Named<Item> metalTag, String ingotName) {
            return (ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', metalTag)
                        .unlockedBy("has_" + ingotName, RegistrateRecipeProvider.has(metalTag))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            };
        }

        public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> metalBlockRecipe(ItemLike metalItem, String ingotName) {
            return (ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', metalItem)
                        .unlockedBy("has_" + ingotName, RegistrateRecipeProvider.has(metalItem))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            };
        }
    }
}
