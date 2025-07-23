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

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alloyed.MOD_ID);

    public static final RegistryObject<SoundEvent> BRONZE_BELL = SOUND_EVENTS.register("bronze_bell",
            () -> SoundEvent.createVariableRangeEvent(Alloyed.asResource("bronze_bell")));

    public static void register(IEventBus eventBus) {
        Alloyed.LOGGER.debug("Registering ModSounds!");
        SOUND_EVENTS.register(eventBus);
    }
}