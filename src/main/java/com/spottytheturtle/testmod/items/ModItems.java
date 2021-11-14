package com.spottytheturtle.testmod.items;

import com.spottytheturtle.testmod.TestMod;
import com.spottytheturtle.testmod.fluids.ModFluids;
import com.spottytheturtle.testmod.items.custom.Firestone;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);


    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
            () -> new Item(new Item.Properties().group(ModItemGroup.TEST_GROUP)));
    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone",
            () -> new Firestone(new Item.Properties().group(ModItemGroup.TEST_GROUP).maxDamage(8)));



    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().group(ModItemGroup.TEST_GROUP)));
    public static final RegistryObject<Item> TEST_ITEM_2 = ITEMS.register("test_item_2", () -> new Item(new Item.Properties().group(ModItemGroup.TEST_GROUP)));
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketItem(() -> ModFluids.OIL_FLUID.get(), new Item.Properties().maxStackSize(1).group(ModItemGroup.TEST_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
