package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.compat.famersdelight.FarmersDelightCompat;
import com.molybdenum.alloyed.items.ModItemGroups;
import com.molybdenum.alloyed.items.ModItemTiers;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import net.minecraft.item.*;
import net.minecraft.tags.ITag;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // Materials
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient("bronze_ingot", ModTags.Items.BRONZE_INGOT);
    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient("steel_ingot", ModTags.Items.STEEL_INGOT);

    public static final ItemEntry<Item> BRONZE_SHEET = taggedIngredient("bronze_sheet", ModTags.Items.BRONZE_SHEET);
    public static final ItemEntry<Item> STEEL_SHEET = taggedIngredient("steel_sheet", ModTags.Items.STEEL_SHEET);

    // Steel toolset
    static { REGISTRATE.startSection(AllSections.CURIOSITIES); }

    public static final ItemEntry<SwordItem> STEEL_SWORD = handheldItem(
            "steel_sword", (p) -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, p));

    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = handheldItem(
            "steel_pickaxe", (p) -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,p));

    public static final ItemEntry<AxeItem> STEEL_AXE = handheldItem(
            "steel_axe", (p) -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,p));

    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = handheldItem(
            "steel_shovel", (p) -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, p));

    public static final ItemEntry<HoeItem> STEEL_HOE = handheldItem(
            "steel_hoe", (p) -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, p));

    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", (p) -> new ShearsItem(p.durability(476)))
            .model((ctx, prov) -> prov.generated(ctx::getEntry, prov.modLoc("item/steel_shears")))
            .tag(ModTags.Items.STEEL_SHEARS)
            .register();

    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", (p) -> new FishingRodItem(p.durability(128)))
            .model((ctx, prov) -> prov.getExistingFile(prov.modLoc("item/steel_fishing_rod")))
            .register();

    // End Item Entries

    public static void register() {}

    // Function from Create's item registrate. Found it useful, so copied it over.
    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, ITag.INamedTag<Item>... tags) {
        return REGISTRATE.item(name, Item::new).tag(tags).register();
    }

    // This one isn't from Create, its by Nebula
    @SafeVarargs
    public static <T extends Item> ItemEntry<T> handheldItem(String name, NonNullFunction<Item.Properties, T> factory, ITag.INamedTag<Item>... tags) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(ctx::getEntry, prov.modLoc("item/" + name)))
                .tag(tags)
                .register();
    }
}
