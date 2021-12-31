package com.molybdenum.alloyed.items;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.*;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    // Item Entries
    public static final ItemEntry<Item> BRONZE_INGOT = ingredient("bronze_ingot");
    public static final ItemEntry<Item> STEEL_INGOT = ingredient("steel_ingot");

    public static final ItemEntry<Item> BRONZE_SHEET = ingredient("bronze_sheet");
    public static final ItemEntry<Item> STEEL_SHEET = ingredient("steel_sheet");

    public static final ItemEntry<SwordItem> STEEL_SWORD = REGISTRATE
            .item("steel_sword", (p) -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, p))
            .register();

    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = REGISTRATE
            .item("steel_pickaxe", (p) -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,p))
            .register();

    public static final ItemEntry<AxeItem> STEEL_AXE = REGISTRATE
            .item("steel_axe", (p) -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,p))
            .register();

    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = REGISTRATE
            .item("steel_shovel", (p) -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, p))
            .register();

    public static final ItemEntry<HoeItem> STEEL_HOE = REGISTRATE
            .item("steel_hoe", (p) -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, p))
            .register();

    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", (p) -> new ShearsItem(p.durability(476)))
            .register();

    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", (p) -> new FishingRodItem(p.durability(128)))
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
    private static ItemEntry<Item> ingredient(String name) {
        return REGISTRATE.item(name, Item::new).register();
    }
}