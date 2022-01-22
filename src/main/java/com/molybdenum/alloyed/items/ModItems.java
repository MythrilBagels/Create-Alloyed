package com.molybdenum.alloyed.items;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.util.ModTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import net.minecraft.item.*;
import net.minecraft.tags.ITag;

import java.util.function.Supplier;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroup.MAIN_GROUP);

    // Item Entries
    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient("bronze_ingot", ModTags.Items.BRONZE_INGOT);
    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient("steel_ingot", ModTags.Items.STEEL_INGOT);

    public static final ItemEntry<Item> BRONZE_SHEET = taggedIngredient("bronze_sheet", ModTags.Items.BRONZE_SHEET);
    public static final ItemEntry<Item> STEEL_SHEET = taggedIngredient("steel_sheet", ModTags.Items.STEEL_SHEET);

    public static final ItemEntry<SwordItem> STEEL_SWORD = tool(
            "steel_sword", (p) -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, p));

    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = tool(
            "steel_pickaxe", (p) -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,p));

    public static final ItemEntry<AxeItem> STEEL_AXE = tool(
            "steel_axe", (p) -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,p));

    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = tool(
            "steel_shovel", (p) -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, p));

    public static final ItemEntry<HoeItem> STEEL_HOE = tool(
            "steel_hoe", (p) -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, p));

    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", (p) -> new ShearsItem(p.durability(476)))
            .model((ctx, prov) -> prov.generated(() -> ctx.getEntry(), prov.modLoc("item/steel_shears")))
            .tag(ModTags.Items.STEEL_SHEARS)
            .register();

    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", (p) -> new FishingRodItem(p.durability(128)))
            .model((ctx, prov) -> prov.getExistingFile(prov.modLoc("item/steel_fishing_rod")))
            .register();

    // End Item Entries

    public ModItems() {}

    public static void register() {
        Create.registrate().addToSection(BRONZE_INGOT, AllSections.MATERIALS);
        Create.registrate().addToSection(STEEL_INGOT, AllSections.MATERIALS);

        Create.registrate().addToSection(BRONZE_SHEET, AllSections.MATERIALS);
        Create.registrate().addToSection(STEEL_SHEET, AllSections.MATERIALS);

        Create.registrate().addToSection(STEEL_SWORD, AllSections.CURIOSITIES);
        Create.registrate().addToSection(STEEL_PICKAXE, AllSections.CURIOSITIES);
        Create.registrate().addToSection(STEEL_AXE, AllSections.CURIOSITIES);
        Create.registrate().addToSection(STEEL_SHOVEL, AllSections.CURIOSITIES);
        Create.registrate().addToSection(STEEL_HOE, AllSections.CURIOSITIES);
        Create.registrate().addToSection(STEEL_SHEARS, AllSections.CURIOSITIES);
        Create.registrate().addToSection(STEEL_FISHING_ROD, AllSections.CURIOSITIES);
    }

    // Function from Create's item registrate. Found it useful, so copied it over.
    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, ITag.INamedTag<Item>... tags) {
        return REGISTRATE.item(name, Item::new).tag(tags).register();
    }

    // This one isn't from Create, its by Nebula
    private static <T extends Item> ItemEntry<T> tool(String name, NonNullFunction<Item.Properties, T> factory) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(() -> ctx.getEntry(), prov.modLoc("item/" + name)))
                .defaultLang()
                .register();
    }
}
