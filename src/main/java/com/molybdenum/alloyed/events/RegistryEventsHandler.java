package com.molybdenum.alloyed.events;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.events.loot.CobwebShearsModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventsHandler {
    @SubscribeEvent
    public static void registerModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new CobwebShearsModifier.Serializer().setRegistryName(Alloyed.asResource("cobweb_shears"))
        );
    }
}
