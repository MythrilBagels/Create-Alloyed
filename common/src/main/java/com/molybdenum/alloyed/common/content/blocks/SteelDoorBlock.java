package com.molybdenum.alloyed.common.content.blocks;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SteelDoorBlock extends DoorBlock {
    public SteelDoorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull SoundType getSoundType(@NotNull BlockState state) {
        return SoundType.METAL;
    }
}
