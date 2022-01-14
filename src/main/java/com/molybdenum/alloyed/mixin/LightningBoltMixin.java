package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.blocks.WeatheringBronze;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin { // Add bronze oxidization stuff to the lightning bolt

    @Inject(
            method = "clearCopperOnLightningStrike(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD // If something goes wrong, we want to be aware of it.
    )
    private static void alloyed$clearCopperOnLightningStrike(Level level, BlockPos blockPos, CallbackInfo ci, BlockState blockstate, BlockPos blockpos, BlockState blockstate1) {
        // Custom functionality
        if (blockstate1.getBlock() instanceof WeatheringBronze) {
            level.setBlockAndUpdate(blockpos, WeatheringBronze.getFirst(level.getBlockState(blockpos)));
            BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();
            int i = level.random.nextInt(3) + 3;

            for(int j = 0; j < i; ++j) {
                int k = level.random.nextInt(8) + 1;
                randomWalkCleaningBronze(level, blockpos, blockpos$mutableblockpos, k);
            }
        }
    }

    // Helper functions changed to use WeatheringBronze rather than WeatheringCopper
    private static void randomWalkCleaningBronze(Level level, BlockPos blockPos, BlockPos.MutableBlockPos mutableBlockPos, int j) {
        mutableBlockPos.set(blockPos);

        for(int i = 0; i < j; ++i) {
            Optional<BlockPos> optional = randomStepCleaningBronze(level, mutableBlockPos);
            if (!optional.isPresent()) {
                break;
            }

            mutableBlockPos.set(optional.get());
        }
    }

    private static Optional<BlockPos> randomStepCleaningBronze(Level level, BlockPos blockPos) {
        for(BlockPos blockpos : BlockPos.randomInCube(level.random, 10, blockPos, 1)) {
            BlockState blockstate = level.getBlockState(blockpos);

            if (blockstate.getBlock() instanceof WeatheringBronze) {
                WeatheringBronze.getPrevious(blockstate).ifPresent((blockState) -> {
                    level.setBlockAndUpdate(blockpos, blockState);
                });
                level.levelEvent(3002, blockpos, -1);
                return Optional.of(blockpos);
            }
        }

        return Optional.empty();
    }
}
