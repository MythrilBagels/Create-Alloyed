package com.molybdenum.alloyed.common.compat.createdeco.connected;

import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class SteelCatwalkCTBehaviour extends SimpleCTBehaviour {
    public SteelCatwalkCTBehaviour() {
        super(ModSpriteShifts.STEEL_CATWALK_TOP);
    }

    @Override
    public boolean connectsTo(BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos, BlockPos otherPos, Direction face) {
        return face.getAxis().isVertical() && state.getBlock() == other.getBlock();
    }
}
