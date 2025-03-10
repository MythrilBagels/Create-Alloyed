package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Alloyed.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> BRONZE_BELL = SOUND_EVENTS.register("bronze_bell",
            () -> SoundEvent.createVariableRangeEvent(Alloyed.asResource("bronze_bell")));

    public static void register(IEventBus eventBus) {
        Alloyed.LOGGER.debug("Registering ModSounds!");
        SOUND_EVENTS.register(eventBus);
    }
}