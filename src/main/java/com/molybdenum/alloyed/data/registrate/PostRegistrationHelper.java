package com.molybdenum.alloyed.data.registrate;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.ProviderType;
import com.simibubi.create.repack.registrate.providers.RegistrateProvider;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiConsumer;
import com.simibubi.create.repack.registrate.util.nullness.NonNullConsumer;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class PostRegistrationHelper {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate();

    // Lang
    public static void addBlockLang(String name, String lang) {
        BlockEntry<Block> blockEntry = (BlockEntry<Block>) REGISTRATE.get(name, Block.class);
        REGISTRATE.setDataGenerator(name, Block.class, ProviderType.LANG, registrateLangProvider -> registrateLangProvider.add(blockEntry.get(), lang));
    }

    // Recipes
    public static <T extends Block, P extends RegistrateProvider> void addBlockRecipe(String name, NonNullConsumer<RegistrateRecipeProvider> recipe) {
        REGISTRATE.setDataGenerator(name, Block.class, ProviderType.RECIPE, recipe);
    }

    public static <T extends Block, P extends RegistrateProvider> void addMetalBlockRecipe(String name, ItemLike metalItem, String ingotName) {
        BlockEntry<Block> blockEntry = (BlockEntry<Block>) REGISTRATE.get(name, Block.class);

        REGISTRATE.setDataGenerator(name, Block.class, ProviderType.RECIPE, prov -> {
            ShapedRecipeBuilder.shaped(blockEntry.get(), 1)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', metalItem)
                    .unlockedBy("has_" + ingotName, RegistrateRecipeProvider.has(metalItem))
                    .save(prov, Alloyed.asResource("crafting/" + name));
        });
    }

    public static <T extends Block, P extends RegistrateProvider> void addMetalBlockRecipe(String name, Tag.Named<Item> metalItem, String ingotName, String path) {
        BlockEntry<Block> blockEntry = (BlockEntry<Block>) REGISTRATE.get(name, Block.class);

        REGISTRATE.setDataGenerator(name, Block.class, ProviderType.RECIPE, prov -> {
            ShapedRecipeBuilder.shaped(blockEntry.get(), 1)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', metalItem)
                    .unlockedBy("has_" + ingotName, RegistrateRecipeProvider.has(metalItem))
                    .save(prov, Alloyed.asResource("crafting/" + path + name));
        });
    }

}