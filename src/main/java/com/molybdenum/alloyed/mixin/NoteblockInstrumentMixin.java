package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.blocks.ModBlocks;
import com.molybdenum.alloyed.sounds.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        Class<?> caClass = Class.forName("jdk.internal.ConstructorAccessor");
        Object ca = constructorAccessorField.get(constructor);
        if (!caClass.isInstance(ca)) {
            Method acquireConstructorAccessorMethod = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessorMethod.setAccessible(true);
            ca = acquireConstructorAccessorMethod.invoke(constructor);
        }
        // note that real constructor contains 2 additional parameters, name and ordinal
        Method instanceCreationInvoker = caClass.getDeclaredMethod("newInstance", Object[].class);
        instanceCreationInvoker.setAccessible(true);
        NoteBlockInstrument enumValue = (NoteBlockInstrument) instanceCreationInvoker.invoke(ca, new Object[]{internalName, NoteBlockInstrument.values().length + 1, soundName, null});
        // NoteBlockInstrument enumValue = (NoteBlockInstrument) ca.newInstance(new Object[]{internalName, NoteBlockInstrument.values().length + 1, soundName, null});
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