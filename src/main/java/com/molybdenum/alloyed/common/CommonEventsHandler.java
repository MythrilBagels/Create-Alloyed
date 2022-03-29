package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import com.molybdenum.alloyed.common.loot.SteelShearsModifier;
import com.molybdenum.alloyed.data.registry.ModLootModifiers;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventsHandler {
    // Global loot modifiers
    @SubscribeEvent
    public static void registerModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        for (ModLootModifiers.AllModifiers modifier : ModLootModifiers.AllModifiers.values()) {
            event.getRegistry().register(modifier.getSerializer());
        }
    }

    // Common setup
    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {
        if (Alloyed.isFarmersDelightLoaded)
            FarmersDelightCompat.steelKnifeDispenseBehaviour();
    }
}
