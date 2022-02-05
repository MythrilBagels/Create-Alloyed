package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Items {
        // Get steel shears working
        public static final Tag.Named<Item> STEEL_SHEARS = createForgeTag("shears");

        // Compat for other mods' ingots
        public static final Tag.Named<Item> BRONZE_INGOT = createForgeTag("ingots/bronze");
        public static final Tag.Named<Item> STEEL_INGOT = createForgeTag("ingots/steel");

        // Compat for other mods' sheets/plates
        public static final Tag.Named<Item> BRONZE_SHEET = createForgeTag("plates/bronze");
        public static final Tag.Named<Item> STEEL_SHEET = createForgeTag("plates/steel");

        // Compat for other mods' ingot blocks
        public static final Tag.Named<Item> BRONZE_BLOCK = createForgeTag("storage_blocks/bronze");
        public static final Tag.Named<Item> STEEL_BLOCK = createForgeTag("storage_blocks/steel");

        // Compat for other mods' knives
        public static final Tag.Named<Item> STEEL_KNIFE = createForgeTag("tools/knives");

        // Compat for Farmer's Delight's knives
        public static final Tag.Named<Item> STEEL_KNIFE_FD = createSpecialTag("farmersdelight","tools/knives");

        private static Tag.Named<Item> createTag(String name) {
            return ItemTags.bind(new ResourceLocation(Alloyed.MOD_ID, name).toString());
        }

        private static Tag.Named<Item> createForgeTag(String name) {
            return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }

        private static Tag.Named<Item> createSpecialTag(String modId, String path) {
            return ItemTags.bind(new ResourceLocation(modId, path).toString());
        }
    }

    public static class Blocks {

        // Compat for other mods' ingot blocks
        public static final Tag.Named<Block> BRONZE_BLOCK = createForgeTag("storage_blocks/bronze");
        public static final Tag.Named<Block> STEEL_BLOCK = createForgeTag("storage_blocks/steel");

        // 1.18 mineability
        //public static final Tag.Named<Block> PICKAXE = BlockTags.MINEABLE_WITH_PICKAXE;

        private static Tag.Named<Block> createTag(String name) {
            return BlockTags.bind(new ResourceLocation(Alloyed.MOD_ID, name).toString());
        }

        private static Tag.Named<Block> createForgeTag(String name) {
            return BlockTags.bind(new ResourceLocation("forge", name).toString());
        }
    }

}
