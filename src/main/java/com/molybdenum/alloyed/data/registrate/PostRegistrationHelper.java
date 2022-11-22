package com.molybdenum.alloyed.data.registrate;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.RegistrateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class PostRegistrationHelper {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate();

    // Lang
    public static void addBlockLang(String name, String lang) {
        BlockEntry<Block> blockEntry = (BlockEntry<Block>) REGISTRATE.get(name, ForgeRegistries.BLOCKS.getRegistryKey());
        REGISTRATE.setDataGenerator(name, ForgeRegistries.BLOCKS.getRegistryKey(), ProviderType.LANG,
                registrateLangProvider -> registrateLangProvider.add(blockEntry.get(), lang));
    }

    // Recipes
    public static <T extends Block, P extends RegistrateProvider> void addMetalBlockRecipe(String name, TagKey<Item> metalItem, String ingotName, String path) {
        BlockEntry<Block> blockEntry = (BlockEntry<Block>) REGISTRATE.get(name, ForgeRegistries.BLOCKS.getRegistryKey());

        REGISTRATE.setDataGenerator(name, ForgeRegistries.BLOCKS.getRegistryKey(), ProviderType.RECIPE, prov -> {
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