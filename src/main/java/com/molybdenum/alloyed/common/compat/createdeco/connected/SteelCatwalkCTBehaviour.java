package com.molybdenum.alloyed.common.compat.createdeco.connected;

import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.foundation.block.connected.StandardCTBehaviour;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

public class SteelCatwalkCTBehaviour extends StandardCTBehaviour {
    public SteelCatwalkCTBehaviour() {
        super(ModSpriteShifts.STEEL_CATWALK_TOP);
    }

    @Override
    public boolean connectsTo(BlockState state, BlockState other, IBlockDisplayReader reader, BlockPos pos, BlockPos otherPos, Direction face) {
        return face.getAxis().isVertical() && state.getBlock() == other.getBlock();
    }
}
