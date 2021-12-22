package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.blocks.ModBlocks;
import com.molybdenum.alloyed.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Mixin(NoteBlockInstrument.class)
@Unique
public abstract class NoteblockInstrumentMixin {
    static {
        try {
            ModSounds.BRONZE_BELL_NOTEBLOCK = addToEnum(createNoteBlockInstrument("BRONZE_BELL", "bronze_bell"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Inject(method = "byState", at = @At("HEAD"), cancellable = true)
    private static void alloyed$byState(BlockState state, CallbackInfoReturnable<NoteBlockInstrument> ci) {
        if (state.is(ModBlocks.BRONZE_BELL.get())) {
            ci.setReturnValue(ModSounds.BRONZE_BELL_NOTEBLOCK);
        }
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