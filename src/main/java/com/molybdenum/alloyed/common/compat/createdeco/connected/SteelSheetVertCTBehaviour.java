package com.molybdenum.alloyed.common.compat.createdeco.connected;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;

public class SteelSheetVertCTBehaviour extends SteelSheetSlabCTBehaviour {

    @Override
    protected boolean slabTouching(BlockState state, BlockPos pos, BlockState other, BlockPos otherPos) {
        if (!other.hasProperty(BlockStateProperties.SLAB_TYPE)) return false;
        SlabType ourSlabType = state.getValue(BlockStateProperties.SLAB_TYPE);

        if (ourSlabType == SlabType.TOP || ourSlabType == SlabType.BOTTOM)
            return other.getValue(BlockStateProperties.SLAB_TYPE).equals(ourSlabType);

        return true;
    }
}
