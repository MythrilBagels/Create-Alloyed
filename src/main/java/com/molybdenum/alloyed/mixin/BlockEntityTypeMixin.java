package com.molybdenum.alloyed.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CancellationException;

import java.util.Objects;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At(value = "RETURN"), cancellable = true)
    private void forceAllowAlloyed(BlockState arg, CallbackInfoReturnable<Boolean> cir) {
        try {
            if (Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(arg.getBlock())).getNamespace().equals("alloyed"))
                cir.setReturnValue(true);
        } catch (NullPointerException e) {
            cir.setReturnValue(false);
        }
    }
}
