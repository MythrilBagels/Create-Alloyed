package com.molybdenum.alloyed.common.compat.createdeco.connected;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;

public class SteelSheetSlabCTBehaviour extends SteelSheetMetalCTBehaviour {
    @Override
    public boolean connectsTo(BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos, BlockPos otherPos, Direction face) {
        if (slabTouching(state, pos, other, otherPos) && super.connectsTo(state, other, reader, pos, otherPos, face)) {
            return true;
        }
        return state.getBlock() == other.getBlock();
    }

    protected boolean slabTouching (BlockState state, BlockPos pos, BlockState other, BlockPos otherPos) {
        if (!other.hasProperty(BlockStateProperties.SLAB_TYPE)) return false;
        SlabType ourSlabType = state.getValue(BlockStateProperties.SLAB_TYPE);
        SlabType theirSlabType = other.getValue(BlockStateProperties.SLAB_TYPE);

        if (ourSlabType == SlabType.TOP || ourSlabType == SlabType.BOTTOM) {
            return !theirSlabType.equals(ourSlabType);
        }
        // Slab type DOUBLE
        if (pos.getY() > otherPos.getY()) {
            return !theirSlabType.equals(SlabType.BOTTOM);
        }
        return !theirSlabType.equals(SlabType.TOP);
    }
}
