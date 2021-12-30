package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.blocks.ModBlocks;
import com.molybdenum.alloyed.sounds.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mixin(NoteBlockInstrument.class)
@Unique
public abstract class NoteblockInstrumentMixin {

    private static final NoteBlockInstrument BRONZE_BELL = alloyed$addVariant("BRONZE_BELL", ModSounds.BRONZE_BELL.get());

    private static NoteBlockInstrument alloyed$invokeInit(String internalName, SoundEvent sound) {
        throw new AssertionError();
    }

    private static NoteBlockInstrument alloyed$addVariant(String internalName, SoundEvent sound) {
        NoteBlockInstrument instrument = alloyed$invokeInit(internalName, sound);
        try {
            addToEnum(instrument);
        } catch (Exception e) {
            System.out.println(e);
        }
        return instrument;
    }

    private static NoteBlockInstrument addToEnum(NoteBlockInstrument instrument) throws Exception {
        Field $VALUESField = NoteBlockInstrument.class.getDeclaredField("$VALUES");
        $VALUESField.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt($VALUESField, $VALUESField.getModifiers() & ~Modifier.FINAL);
        NoteBlockInstrument[] oldValues = (NoteBlockInstrument[]) $VALUESField.get(null);
        NoteBlockInstrument[] newValues = new NoteBlockInstrument[oldValues.length + 1];
        System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
        newValues[oldValues.length] = instrument;
        $VALUESField.set(null, newValues);
        return instrument;
    }

    @Inject(method = "byState", at = @At("HEAD"), cancellable = true)
    private static void alloyed$byState(BlockState state, CallbackInfoReturnable<NoteBlockInstrument> ci) {
        if (state.is(ModBlocks.BRONZE_BELL.get())) {
            ci.setReturnValue(BRONZE_BELL);
        }
    }
}