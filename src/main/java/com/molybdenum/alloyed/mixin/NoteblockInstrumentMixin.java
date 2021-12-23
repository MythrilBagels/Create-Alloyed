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


    @Inject(method = "byState", at = @At("HEAD"), cancellable = true)
    private static void alloyed$byState(BlockState state, CallbackInfoReturnable<NoteBlockInstrument> ci) {
        if (state.is(ModBlocks.BRONZE_BELL.get())) {
            ci.setReturnValue(ModSounds.BRONZE_BELL_NOTEBLOCK);
        }
    }



}