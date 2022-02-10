package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alloyed.MOD_ID);

    @Nullable
    public static NoteBlockInstrument BRONZE_BELL_NOTEBLOCK;

    public static final RegistryObject<SoundEvent> BRONZE_BELL = registerSoundEvent("bronze_bell", soundEvent -> {
        if (BRONZE_BELL_NOTEBLOCK != null) BRONZE_BELL_NOTEBLOCK.soundEvent = soundEvent;
    });

    public static RegistryObject<SoundEvent> registerSoundEvent(String name, Consumer<SoundEvent> onRegister) {
        return SOUND_EVENTS.register(name,
                () -> {
                    SoundEvent event = new SoundEvent(Alloyed.asResource(name));
                    onRegister.accept(event);
                    return event;
                });
    }

    public static void register(IEventBus eventBus) {
        System.out.println("Registering ModSounds!");
        SOUND_EVENTS.register(eventBus);
    }
}