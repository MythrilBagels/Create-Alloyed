package com.spottytheturtle.create_composition.items;

import com.spottytheturtle.create_composition.create_composition;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, create_composition.MOD_ID);


    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.TEST_GROUP)));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().group(ModItemGroup.TEST_GROUP)));



    //public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
    //        () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(), new Item.Properties().maxStackSize(1).group(ModItemGroup.TEST_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
