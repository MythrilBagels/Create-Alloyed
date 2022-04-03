package com.molybdenum.alloyed.common.compat.createdeco.connected;

import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class SteelSheetMetalCTBehaviour extends HorizontalCTBehaviour {
    public SteelSheetMetalCTBehaviour() {
        super(ModSpriteShifts.STEEL_SHEET_METAL);
    }

    @Override
    public boolean connectsTo(BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos, BlockPos otherPos, Direction face) {
        if (!face.getAxis().isVertical() && super.connectsTo(state, other, reader, pos, otherPos, face)) {
            return true;
        }
        return state.getBlock() == other.getBlock();
    }
}
