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
        public static final TagKey<Item> STEEL_SHEARS = createForgeTag("shears");

        // Compat for other mods' ingots
        public static final TagKey<Item> BRONZE_INGOT = createForgeTag("ingots/bronze");
        public static final TagKey<Item> STEEL_INGOT = createForgeTag("ingots/steel");

        // Compat for other mods' nuggets
        public static final TagKey<Item> BRONZE_NUGGET = createForgeTag("nuggets/bronze");
        public static final TagKey<Item> STEEL_NUGGET = createForgeTag("nuggets/steel");

        // Compat for other mods' sheets/plates
        public static final TagKey<Item> BRONZE_SHEET = createForgeTag("plates/bronze");
        public static final TagKey<Item> STEEL_SHEET = createForgeTag("plates/steel");

        // Compat for other mods' ingot blocks
        public static final TagKey<Item> BRONZE_BLOCK = createForgeTag("storage_blocks/bronze");
        public static final TagKey<Item> STEEL_BLOCK = createForgeTag("storage_blocks/steel");

        // Compat for other mods' knives
        public static final TagKey<Item> STEEL_KNIFE = createForgeTag("tools/knives");

        // Compat for Farmer's Delight's knives
        public static final TagKey<Item> STEEL_KNIFE_FD = createSpecialTag("farmersdelight","tools/knives");

        // All bronze instruments
        public static final TagKey<Item> BRONZE_INSTRUMENTS = createTag("bronze_instruments");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Alloyed.asResource(name));
        }

        private static TagKey<Item> createForgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Item> createSpecialTag(String modId, String path) {
            return ItemTags.create(new ResourceLocation(modId, path));
        }
    }

    public static class Blocks {

        // Compat for other mods' ingot blocks
        public static final TagKey<Block> BRONZE_BLOCK = createForgeTag("storage_blocks/bronze");
        public static final TagKey<Block> STEEL_BLOCK = createForgeTag("storage_blocks/steel");

        // All bronze instruments
        public static final TagKey<Block> BRONZE_INSTRUMENTS = createTag("bronze_instruments");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Alloyed.asResource(name));
        }

        private static TagKey<Block> createForgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Generic {
        // Used for recipes

        // Ingots
        public static final TagKey<Item> ZINC_INGOT = createForgeTag("ingots/zinc");
        // Nuggets
        public static final TagKey<Item> ZINC_NUGGET = createForgeTag("nuggets/zinc");
        // Misc
        public static final TagKey<Item> STICK = createForgeTag("rods/wooden");


        private static TagKey<Item> createForgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

}
