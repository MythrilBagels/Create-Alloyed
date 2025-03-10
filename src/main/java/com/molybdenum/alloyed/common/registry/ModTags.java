package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items {
        // Get steel shears working
        public static final TagKey<Item> STEEL_SHEARS = createCommonTag("shears");

        // Compat for other mods' ingots
        public static final TagKey<Item> BRONZE_INGOT = createCommonTag("ingots/bronze");
        public static final TagKey<Item> STEEL_INGOT = createCommonTag("ingots/steel");

        // Compat for other mods' nuggets
        public static final TagKey<Item> BRONZE_NUGGET = createCommonTag("nuggets/bronze");
        public static final TagKey<Item> STEEL_NUGGET = createCommonTag("nuggets/steel");

        // Compat for other mods' sheets/plates
        public static final TagKey<Item> BRONZE_SHEET = createCommonTag("plates/bronze");
        public static final TagKey<Item> STEEL_SHEET = createCommonTag("plates/steel");

        // Compat for other mods' ingot blocks
        public static final TagKey<Item> BRONZE_BLOCK = createCommonTag("storage_blocks/bronze");
        public static final TagKey<Item> STEEL_BLOCK = createCommonTag("storage_blocks/steel");

        // Compat for other mods' knives
        public static final TagKey<Item> STEEL_KNIFE = createCommonTag("tools/knives");

        // Compat for Farmer's Delight's knives
        public static final TagKey<Item> STEEL_KNIFE_FD = createSpecialTag("farmersdelight","tools/knives");

        // All bronze instruments
        public static final TagKey<Item> BRONZE_INSTRUMENTS = createTag("bronze_instruments");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Alloyed.asResource(name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }

        private static TagKey<Item> createSpecialTag(String modId, String path) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
        }
    }

    public static class Blocks {

        // Compat for other mods' ingot blocks
        public static final TagKey<Block> BRONZE_BLOCK = createCommonTag("storage_blocks/bronze");
        public static final TagKey<Block> STEEL_BLOCK = createCommonTag("storage_blocks/steel");

        // All bronze instruments
        public static final TagKey<Block> BRONZE_INSTRUMENTS = createTag("bronze_instruments");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Alloyed.asResource(name));
        }

        private static TagKey<Block> createCommonTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Generic {
        // Used for recipes

        // Ingots
        public static final TagKey<Item> ZINC_INGOT = createCommonTag("ingots/zinc");
        // Nuggets
        public static final TagKey<Item> ZINC_NUGGET = createCommonTag("nuggets/zinc");
        // Misc
        public static final TagKey<Item> STICK = createCommonTag("rods/wooden");


        private static TagKey<Item> createCommonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

}
