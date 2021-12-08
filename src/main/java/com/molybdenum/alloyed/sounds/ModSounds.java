package com.molybdenum.alloyed.sounds;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alloyed.MOD_ID);


    public static final RegistryObject<SoundEvent> BRONZE_BELL = registerSoundEvent("bronze_bell");


    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name,
                () -> new SoundEvent(new ResourceLocation(Alloyed.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }

}
