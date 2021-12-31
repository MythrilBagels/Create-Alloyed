package com.molybdenum.alloyed.util;

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

        // Compat for other mods' ingots
        public static final Tags.IOptionalNamedTag<Item> BRONZE_INGOT = createForgeTag("ingots/bronze");
        public static final Tags.IOptionalNamedTag<Item> STEEL_INGOT = createForgeTag("ingots/steel");

        // Compat for other mods' sheets/plates
        public static final Tags.IOptionalNamedTag<Item> BRONZE_SHEET = createForgeTag("plates/bronze");
        public static final Tags.IOptionalNamedTag<Item> STEEL_SHEET = createForgeTag("plates/steel");

        // Compat for other mods' ingot blocks
        public static final Tags.IOptionalNamedTag<Item> BRONZE_BLOCK = createForgeTag("storage_blocks/bronze");
        public static final Tags.IOptionalNamedTag<Item> STEEL_BLOCK = createForgeTag("storage_blocks/steel");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Alloyed.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Blocks {

        // Compat for other mods' ingot blocks
        public static final Tags.IOptionalNamedTag<Block> BRONZE_BLOCK = createForgeTag("storage_blocks/bronze");
        public static final Tags.IOptionalNamedTag<Block> STEEL_BLOCK = createForgeTag("storage_blocks/steel");

        // 1.18 mineability
        //public static final Tags.IOptionalNamedTag<Block> PICKAXE = BlockTags.MINEABLE_WITH_PICKAXE;

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Alloyed.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

}
