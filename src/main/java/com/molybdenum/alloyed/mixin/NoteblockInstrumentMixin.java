package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.blocks.ModBlocks;
import com.molybdenum.alloyed.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
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
    @Shadow(remap = false)
    @Final
    @Mutable
    private static NoteBlockInstrument[] $VALUES;

    static {
        try {
            ModSounds.BRONZE_BELL_NOTEBLOCK = addToEnum(createNoteBlockInstrument("BRONZE_BELL", "bronze_bell"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Invoker(value = "<init>", remap = false)
    static NoteBlockInstrument newNoteblockInstrument(String internalName, int ordinal, String soundName, SoundEvent sound) {
        throw new AssertionError();
    }

    @Inject(method = "byState", at = @At("HEAD"), cancellable = true)
    private static void alloyed$byState(BlockState state, CallbackInfoReturnable<NoteBlockInstrument> ci) {
        if (state.is(ModBlocks.BRONZE_BELL.get())) {
            ci.setReturnValue(ModSounds.BRONZE_BELL_NOTEBLOCK);
        }
    }

    private static NoteBlockInstrument createNoteBlockInstrument(String internalName, String soundName) throws Exception {
        return newNoteblockInstrument(internalName, NoteBlockInstrument.values().length + 1, soundName, null);
    }

    private static NoteBlockInstrument addToEnum(NoteBlockInstrument instrument) throws Exception {
        NoteBlockInstrument[] newValues = new NoteBlockInstrument[NoteBlockInstrument.values().length + 1];
        System.arraycopy(NoteBlockInstrument.values(), 0, newValues, 0, NoteBlockInstrument.values().length);
        newValues[NoteBlockInstrument.values().length] = instrument;
        $VALUES = newValues;
        return instrument;
    }

}