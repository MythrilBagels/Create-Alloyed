package com.molybdenum.alloyed.items;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.util.ModTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.*;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    // Item Entries
    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient("bronze_ingot", ModTags.Items.BRONZE_INGOT);
    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient("steel_ingot", ModTags.Items.STEEL_INGOT);

    public static final ItemEntry<Item> BRONZE_SHEET = taggedIngredient("bronze_sheet", ModTags.Items.BRONZE_SHEET);
    public static final ItemEntry<Item> STEEL_SHEET = taggedIngredient("steel_sheet", ModTags.Items.STEEL_SHEET);

    public static final ItemEntry<SwordItem> STEEL_SWORD = tool(
            "steel_sword", 
            properties -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, properties)
    );

    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = tool(
            "steel_pickaxe", 
            properties -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,properties)
    );

    public static final ItemEntry<AxeItem> STEEL_AXE = tool(
            "steel_axe", 
            properties -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,properties)
    );

    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = tool(
            "steel_shovel", 
            properties -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, properties)
    );

    public static final ItemEntry<HoeItem> STEEL_HOE = tool(
            "steel_hoe", 
            properties -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, properties)
    );

    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", properties -> new ShearsItem(properties.durability(476)))
            .tag(ModTags.Items.STEEL_SHEARS)
            .register();

    public static final ItemEntry<SteelFishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", properties -> new SteelFishingRodItem(properties.durability(128)))
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

    private static <T extends Item> ItemEntry<T> tool(String name, NonNullFunction<Item.Properties, T> factory) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(() -> ctx.getEntry(), prov.modLoc("item/" + name)))
                .register();
    }

    // Function from Create's item registrate. Found it useful, so copied it over.
    private static ItemEntry<Item> ingredient(String name) {
        return REGISTRATE
                .item(name, Item::new)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, Tag.Named<Item>... tags) {
        return REGISTRATE.item(name, Item::new)
                .tag(tags)
                .register();
    }
}