package com.molybdenum.alloyed.mixin;

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

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(NoteBlockInstrument.class)
@Unique
public abstract class NoteblockInstrumentMixin {

    @Shadow
    @Final
    @Mutable
    private static NoteBlockInstrument[] $VALUES;

    private static final NoteBlockInstrument BRONZE_BELL = addVariant("BRONZE_BELL", "bronze_bell", ModSounds.BRONZE_BELL.get());

    @Invoker("<init>")
    public static NoteBlockInstrument invokeInit(String internalName, int internalId, String name, SoundEvent sound) {
        throw new AssertionError();
    }

    private static NoteBlockInstrument addVariant(String internalName, String name, SoundEvent sound) {
        ArrayList<NoteBlockInstrument> variants = new ArrayList<NoteBlockInstrument>(Arrays.asList(NoteblockInstrumentMixin.$VALUES));
        NoteBlockInstrument instrument = invokeInit(internalName, variants.get(variants.size() - 1).ordinal() + 1, name, sound);
        variants.add(instrument);
        NoteblockInstrumentMixin.$VALUES = variants.toArray(new NoteBlockInstrument[0]);
        return instrument;
    }

    @Inject(method = "byState", at = @At("HEAD"), cancellable = true)
    private static void byState(BlockState state, CallbackInfoReturnable<NoteBlockInstrument> ci) {
        if (state.is(ModBlocks.BRONZE_BELL.get())) {
            ci.setReturnValue(BRONZE_BELL);
        }
    }
}