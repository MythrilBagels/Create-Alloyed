package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.items.ModItemGroups;
import com.molybdenum.alloyed.common.items.ModItemTiers;
import com.molybdenum.alloyed.data.recipes.MechanicalCraftingRecipes;
import com.molybdenum.alloyed.data.recipes.MixingRecipes;
import com.molybdenum.alloyed.data.recipes.PressingRecipes;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiConsumer;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import net.minecraft.item.*;
import net.minecraft.tags.ITag;

import static com.molybdenum.alloyed.data.util.RecipeUtils.Crafting;
import static com.molybdenum.alloyed.data.util.RecipeUtils.Smithing;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // Materials
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    /**
     * Mixing recipe can be found here:
     * @see MixingRecipes#BRONZE_INGOT
     * @see MixingRecipes#BRONZE_INGOTx3
     */
    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient(
            "bronze_ingot",
            Crafting.metalIngotDecompactingRecipe(ModTags.Items.BRONZE_BLOCK, "bronze_block"),
            ModTags.Items.BRONZE_INGOT
    );

    /**
     * Mixing recipe can be found here:
     * @see MixingRecipes#STEEL_INGOT
     */
    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient(
            "steel_ingot",
            Crafting.metalIngotDecompactingRecipe(ModTags.Items.STEEL_BLOCK, "steel_block"),
            ModTags.Items.STEEL_INGOT
    );

    /**
     * Pressing recipe can be found here:
     * @see PressingRecipes#BRONZE_SHEET
     */
    public static final ItemEntry<Item> BRONZE_SHEET = taggedIngredient("bronze_sheet", ModTags.Items.BRONZE_SHEET);

    /**
     * Pressing recipe can be found here:
     * @see PressingRecipes#STEEL_SHEET
     */
    public static final ItemEntry<Item> STEEL_SHEET = taggedIngredient("steel_sheet", ModTags.Items.STEEL_SHEET);

    // Steel toolset
    static { REGISTRATE.startSection(AllSections.CURIOSITIES); }

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_SWORD
     */
    public static final ItemEntry<SwordItem> STEEL_SWORD = handheldItem(
            "steel_sword",
            properties -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, properties),
            Smithing.steelToolRecipe(Items.IRON_SWORD)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_PICKAXE
     */
    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = handheldItem(
            "steel_pickaxe",
            properties -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,properties),
            Smithing.steelToolRecipe(Items.IRON_PICKAXE)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_AXE
     */
    public static final ItemEntry<AxeItem> STEEL_AXE = handheldItem(
            "steel_axe",
            properties -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,properties),
            Smithing.steelToolRecipe(Items.IRON_AXE)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_SHOVEL
     */
    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = handheldItem(
            "steel_shovel",
            properties -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, properties),
            Smithing.steelToolRecipe(Items.IRON_SHOVEL)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_HOE
     */
    public static final ItemEntry<HoeItem> STEEL_HOE = handheldItem(
            "steel_hoe",
            properties -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, properties),
            Smithing.steelToolRecipe(Items.IRON_HOE)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_SHEARS
     */
    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", properties -> new ShearsItem(properties.durability(750)))
            .tag(ModTags.Items.STEEL_SHEARS)
            .recipe(Smithing.steelToolRecipe(Items.SHEARS))
            .register();

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_FISHING_ROD
     */
    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", properties -> new FishingRodItem(properties.durability(512)))
            .model((ctx, prov) -> prov.getExistingFile(prov.modLoc("item/steel_fishing_rod")))
            .recipe(Smithing.steelToolRecipe(Items.FISHING_ROD))
            .register();

    // End Item Entries

    public static void register() { Alloyed.LOGGER.info("Registering ModItems!"); }

    @SafeVarargs
    public static <T extends Item> ItemEntry<T> handheldItem(String name, NonNullFunction<Item.Properties, T> factory, ITag.INamedTag<Item>... tags) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(ctx::getEntry, prov.modLoc("item/" + name)))
                .tag(tags)
                .register();
    }

    @SafeVarargs
    public static <T extends Item> ItemEntry<T> handheldItem(String name, NonNullFunction<Item.Properties, T> factory, NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> recipe, ITag.INamedTag<Item>... tags) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(ctx::getEntry, prov.modLoc("item/" + name)))
                .tag(tags)
                .recipe(recipe)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, ITag.INamedTag<Item>... tags) {
        return REGISTRATE
                .item(name, Item::new)
                .tag(tags)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, NonNullBiConsumer<DataGenContext<Item, Item>, RegistrateRecipeProvider> recipe, ITag.INamedTag<Item>... tags) {
        return REGISTRATE
                .item(name, Item::new)
                .tag(tags)
                .recipe(recipe)
                .register();
    }
}
