package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static final RegistryEntry<SoundEvent> BRONZE_BELL = Alloyed.registrate()
            .simple(
                    "bronze_bell",
                    Registry.SOUND_EVENT_REGISTRY,
                    () -> new SoundEvent(Alloyed.asResource("bronze_bell"))
            );

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModSounds!");
    }
}