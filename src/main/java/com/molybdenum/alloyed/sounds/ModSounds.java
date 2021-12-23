package com.molybdenum.alloyed.sounds;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alloyed.MOD_ID);

    @Nullable
    public static NoteBlockInstrument BRONZE_BELL_NOTEBLOCK;

    public static final RegistryObject<SoundEvent> BRONZE_BELL = registerSoundEvent("bronze_bell", soundEvent -> BRONZE_BELL_NOTEBLOCK.soundEvent = soundEvent);

    public static RegistryObject<SoundEvent> registerSoundEvent(String name, Consumer<SoundEvent> onRegister) {
        return SOUND_EVENTS.register(name,
                () -> {
                    SoundEvent event = new SoundEvent(new ResourceLocation(Alloyed.MOD_ID, name));
                    onRegister.accept(event);
                    return event;
                });
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}