package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.items.ModItemGroup;
import com.molybdenum.alloyed.items.ModItemTiers;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.*;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    // Materials
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient("bronze_ingot", ModTags.Items.BRONZE_INGOT);
    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient("steel_ingot", ModTags.Items.STEEL_INGOT);

    public static final ItemEntry<Item> BRONZE_SHEET = taggedIngredient("bronze_sheet", ModTags.Items.BRONZE_SHEET);
    public static final ItemEntry<Item> STEEL_SHEET = taggedIngredient("steel_sheet", ModTags.Items.STEEL_SHEET);

    // Steel toolset
    static { REGISTRATE.startSection(AllSections.CURIOSITIES); }

    public static final ItemEntry<SwordItem> STEEL_SWORD = handheldItem(
            "steel_sword", 
            properties -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, properties)
    );

    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = handheldItem(
            "steel_pickaxe", 
            properties -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,properties)
    );

    public static final ItemEntry<AxeItem> STEEL_AXE = handheldItem(
            "steel_axe", 
            properties -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,properties)
    );

    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = handheldItem(
            "steel_shovel", 
            properties -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, properties)
    );

    public static final ItemEntry<HoeItem> STEEL_HOE = handheldItem(
            "steel_hoe", 
            properties -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, properties)
    );

    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", properties -> new ShearsItem(properties.durability(476)))
            .tag(ModTags.Items.STEEL_SHEARS)
            .register();

    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", properties -> new FishingRodItem(properties.durability(128)))
            .model((ctx, prov) -> prov.getExistingFile(prov.modLoc("item/steel_fishing_rod")))
            .register();

    // End Item Entries

    public ModItems() {}

    public static void register() {
        System.out.println("Registering ModItems!");
    }

    @SafeVarargs
    private static <T extends Item> ItemEntry<T> handheldItem(String name, NonNullFunction<Item.Properties, T> factory, Tag.Named<Item>... tags) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(ctx::getEntry, prov.modLoc("item/" + name)))
                .tag(tags)
                .register();
    }

    // Function from Create's item registrate. Found it useful, so copied it over.
    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, Tag.Named<Item>... tags) {
        return REGISTRATE.item(name, Item::new)
                .tag(tags)
                .register();
    }
}