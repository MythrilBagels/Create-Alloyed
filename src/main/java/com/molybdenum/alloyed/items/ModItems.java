package com.molybdenum.alloyed.items;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.item.Item;
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

    // Chrome
    public static final RegistryObject<Item> CHROME_INGOT = ITEMS.register("chrome_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.TEST_GROUP)));




    //public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
    //        () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(), new Item.Properties().maxStackSize(1).group(ModItemGroup.TEST_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
