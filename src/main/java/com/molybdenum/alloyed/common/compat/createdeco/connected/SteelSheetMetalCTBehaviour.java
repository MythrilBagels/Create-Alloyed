package com.molybdenum.alloyed.common.compat.createdeco.connected;

import com.molybdenum.alloyed.common.registry.ModCompatBlocks;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

public class SteelSheetMetalCTBehaviour extends HorizontalCTBehaviour {
    public SteelSheetMetalCTBehaviour() {
        super(ModSpriteShifts.STEEL_SHEET_METAL);
    }

    @Override
    public boolean connectsTo(BlockState state, BlockState other, IBlockDisplayReader reader, BlockPos pos, BlockPos otherPos, Direction face) {
        if (!face.getAxis().isVertical() && super.connectsTo(state, other, reader, pos, otherPos, face)) {
            return true;
        }
        return state.getBlock() == other.getBlock();
    }
}
