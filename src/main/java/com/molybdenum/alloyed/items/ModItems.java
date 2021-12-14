package com.molybdenum.alloyed.items;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Alloyed.MOD_ID);


    // Bronze
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> BRONZE_SHEET = ITEMS.register("bronze_sheet",
            () -> new Item(new Item.Properties().tab(ModItemGroup.TEST_GROUP)));

    // Steel
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_SHEET = ITEMS.register("steel_sheet",
            () -> new Item(new Item.Properties().tab(ModItemGroup.TEST_GROUP)));

    // Steel tools
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModItemTiers.STEEL, 3, -2.4F, (new Item.Properties())
                    .tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, (new Item.Properties())
                    .tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8F, (new Item.Properties())
                    .tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F, (new Item.Properties())
                    .tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModItemTiers.STEEL, -3, 0.0F, (new Item.Properties())
                    .tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_SHEARS = ITEMS.register("steel_shears",
            () -> new ShearsItem(new Item.Properties()
                    .durability(476)
                    .tab(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_FISHING_ROD = ITEMS.register("steel_fishing_rod",
            () -> new FishingRodItem(new Item.Properties()
                    .durability(128)
                    .tab(ModItemGroup.TEST_GROUP)));



    //public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
    //        () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(), new Item.Properties().maxStackSize(1).group(ModItemGroup.TEST_GROUP)));


    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }


}
