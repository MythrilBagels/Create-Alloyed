package com.molybdenum.alloyed.sounds;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import sun.reflect.ConstructorAccessor;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alloyed.MOD_ID);
    @Nullable
    public static NoteBlockInstrument BRONZE_BELL_NOTEBLOCK;
    static {
        try {
            BRONZE_BELL_NOTEBLOCK = addToEnum(createNoteBlockInstrument("BRONZE_BELL", "bronze_bell"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private static NoteBlockInstrument createNoteBlockInstrument(String internalName, String soundName) throws Exception {
        Constructor<?> constructor = NoteBlockInstrument.class.getDeclaredConstructor(String.class, int.class, String.class, SoundEvent.class);
        constructor.setAccessible(true);
        Field constructorAccessorField = Constructor.class.getDeclaredField("constructorAccessor");
        constructorAccessorField.setAccessible(true);
        ConstructorAccessor ca = (ConstructorAccessor) constructorAccessorField.get(constructor);
        if (ca == null) {
            Method acquireConstructorAccessorMethod = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessorMethod.setAccessible(true);
            ca = (ConstructorAccessor) acquireConstructorAccessorMethod.invoke(constructor);
        }
        // note that real constructor contains 2 additional parameters, name and ordinal
        NoteBlockInstrument enumValue = (NoteBlockInstrument) ca.newInstance(new Object[]{internalName, NoteBlockInstrument.values().length + 1, soundName, null});
        return enumValue;
    }

    private static NoteBlockInstrument addToEnum(NoteBlockInstrument instrument) throws Exception {
        Field values = NoteBlockInstrument.class.getDeclaredField("$VALUES");
        values.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(values, values.getModifiers() & ~Modifier.FINAL);
        NoteBlockInstrument[] oldValues = (NoteBlockInstrument[]) values.get(null);
        NoteBlockInstrument[] newValues = new NoteBlockInstrument[oldValues.length + 1];
        System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
        newValues[oldValues.length] = instrument;
        values.set(null, newValues);
        return instrument;
    }
}