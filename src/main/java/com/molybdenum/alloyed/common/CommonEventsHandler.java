package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.loot.SteelShearsModifier;
import com.molybdenum.alloyed.data.registry.ModLootModifiers;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventsHandler {
    // Global loot modifiers
    @SubscribeEvent
    public static void registerModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        for (ModLootModifiers.AllModifiers modifier : ModLootModifiers.AllModifiers.values()) {
            event.getRegistry().register(new SteelShearsModifier.Serializer().setRegistryName(Alloyed.asResource(modifier.getSerializedName())));
        }
    }
}
