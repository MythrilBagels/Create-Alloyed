package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.content.items.SteelUpgradeSmithingTemplateItem;
import com.molybdenum.alloyed.common.item.ModArmourMaterials;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.item.ModItemTiers;
import com.molybdenum.alloyed.data.recipes.MechanicalCraftingRecipes;
import com.molybdenum.alloyed.data.recipes.MixingRecipes;
import com.molybdenum.alloyed.data.recipes.PressingRecipes;
import com.molybdenum.alloyed.data.util.RecipeUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;

import static com.molybdenum.alloyed.data.util.RecipeUtils.Crafting;
import static com.molybdenum.alloyed.data.util.RecipeUtils.Smithing;

public class ModItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.REGISTRATE.setCreativeTab(ModCreativeModeTab.MAIN_TAB);

    // Ingots
    /**
     * Mixing recipe can be found here:
     * @see MixingRecipes#BRONZE_INGOT
     * @see MixingRecipes#BRONZE_INGOTx3
     */
    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient(
            "bronze_ingot",
            Crafting.compactingDecompactingRecipe(ModTags.Items.BRONZE_BLOCK, ModTags.Items.BRONZE_NUGGET),
            ModTags.Items.BRONZE_INGOT
    );

    /**
     * Mixing recipe can be found here:
     * @see MixingRecipes#STEEL_INGOT
     */
    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient(
            "steel_ingot",
            Crafting.compactingDecompactingRecipe(ModTags.Items.STEEL_BLOCK, ModTags.Items.STEEL_NUGGET),
            ModTags.Items.STEEL_INGOT
    );

    // Nuggets

    public static final ItemEntry<Item> BRONZE_NUGGET = taggedIngredient(
            "bronze_nugget",
            Crafting.decompactingRecipe(ModTags.Items.BRONZE_INGOT),
            ModTags.Items.BRONZE_NUGGET
    );

    public static final ItemEntry<Item> STEEL_NUGGET = taggedIngredient(
            "steel_nugget",
            Crafting.decompactingRecipe(ModTags.Items.STEEL_INGOT),
            ModTags.Items.STEEL_NUGGET
    );

    // Sheets
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

    public static final ItemEntry<SteelUpgradeSmithingTemplateItem> STEEL_SMITHING_UPGRADE_TEMPLATE = REGISTRATE
            .item($ -> new SteelUpgradeSmithingTemplateItem())
            .recipe((ctx, prov) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
                        .pattern("###")
                        .pattern("#O#")
                        .pattern("###")
                        .define('#', ModTags.Items.STEEL_INGOT)
                        .define('O', Blocks.COBBLED_DEEPSLATE)
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            })
            .register();

    // Steel toolset.
    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_SWORD
     */
    public static final ItemEntry<SwordItem> STEEL_SWORD = handheldItem(
            "steel_sword", 
            properties -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, properties),
            Smithing.steelItemRecipe(Items.IRON_SWORD)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_PICKAXE
     */
    public static final ItemEntry<PickaxeItem> STEEL_PICKAXE = handheldItem(
            "steel_pickaxe", 
            properties -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F,properties),
            Smithing.steelItemRecipe(Items.IRON_PICKAXE)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_AXE
     */
    public static final ItemEntry<AxeItem> STEEL_AXE = handheldItem(
            "steel_axe", 
            properties -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F,properties),
            Smithing.steelItemRecipe(Items.IRON_AXE)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_SHOVEL
     */
    public static final ItemEntry<ShovelItem> STEEL_SHOVEL = handheldItem(
            "steel_shovel", 
            properties -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, properties),
            Smithing.steelItemRecipe(Items.IRON_SHOVEL)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_HOE
     */
    public static final ItemEntry<HoeItem> STEEL_HOE = handheldItem(
            "steel_hoe", 
            properties -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, properties),
            Smithing.steelItemRecipe(Items.IRON_HOE)
    );

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_SHEARS
     */
    public static final ItemEntry<ShearsItem> STEEL_SHEARS = REGISTRATE
            .item("steel_shears", properties -> new ShearsItem(properties.durability(750)))
            .tag(ModTags.Items.STEEL_SHEARS)
            .recipe(Smithing.steelItemRecipe(Items.SHEARS))
            .register();

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_FISHING_ROD
     */
    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = REGISTRATE
            .item("steel_fishing_rod", properties -> new FishingRodItem(properties.durability(512)))
            .model((ctx, prov) -> prov.getExistingFile(prov.modLoc("item/steel_fishing_rod")))
            .recipe(Smithing.steelItemRecipe(Items.FISHING_ROD))
            .register();

    // Steel Armour
    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_HELMET
     */
    public static final ItemEntry<ArmorItem> STEEL_HELMET = REGISTRATE
            .item("steel_helmet", properties -> new ArmorItem(ModArmourMaterials.STEEL, ArmorItem.Type.HELMET, properties))
            .recipe(Smithing.steelItemRecipe(Items.CHAINMAIL_HELMET))
            .register();

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_CHESTPLATE
     */
    public static final ItemEntry<ArmorItem> STEEL_CHESTPLATE = REGISTRATE
            .item("steel_chestplate", properties -> new ArmorItem(ModArmourMaterials.STEEL, ArmorItem.Type.CHESTPLATE, properties))
            .recipe(Smithing.steelItemRecipe(Items.CHAINMAIL_CHESTPLATE))
            .register();

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_LEGGINGS
     */
    public static final ItemEntry<ArmorItem> STEEL_LEGGINGS = REGISTRATE
            .item("steel_leggings", properties -> new ArmorItem(ModArmourMaterials.STEEL, ArmorItem.Type.LEGGINGS, properties))
            .recipe(Smithing.steelItemRecipe(Items.CHAINMAIL_LEGGINGS))
            .register();

    /**
     * Mechanical Crafting recipe can be found here:
     * @see MechanicalCraftingRecipes#STEEL_BOOTS
     */
    public static final ItemEntry<ArmorItem> STEEL_BOOTS = REGISTRATE
            .item("steel_boots", properties -> new ArmorItem(ModArmourMaterials.STEEL, ArmorItem.Type.BOOTS, properties))
            .recipe(Smithing.steelItemRecipe(Items.CHAINMAIL_BOOTS))
            .register();

    // End Item Entries

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModItems!");
    }

    @SafeVarargs
    public static <T extends Item> ItemEntry<T> handheldItem(String name, NonNullFunction<Item.Properties, T> factory, TagKey<Item>... tags) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(ctx::getEntry, prov.modLoc("item/" + name)))
                .tag(tags)
                .register();
    }

    @SafeVarargs
    public static <T extends Item> ItemEntry<T> handheldItem(String name, NonNullFunction<Item.Properties, T> factory, NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> recipe, TagKey<Item>... tags) {
        return REGISTRATE
                .item(name, factory)
                .model((ctx, prov) -> prov.handheld(ctx::getEntry, prov.modLoc("item/" + name)))
                .tag(tags)
                .recipe(recipe)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
        return REGISTRATE
                .item(name, Item::new)
                .tag(tags)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, NonNullBiConsumer<DataGenContext<Item, Item>, RegistrateRecipeProvider> recipe, TagKey<Item>... tags) {
        return REGISTRATE
                .item(name, Item::new)
                .tag(tags)
                .recipe(recipe)
                .register();
    }
}