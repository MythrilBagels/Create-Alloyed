package com.molybdenum.alloyed.common.compat.createdeco;

import com.github.talrey.createdeco.blocks.CatwalkBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.lwjgl.system.NonnullDefault;

public class SteelCatwalkBlock extends CatwalkBlock {
    public SteelCatwalkBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    @NonnullDefault
    @SuppressWarnings("deprecation")
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        if (!(pState.getBlock() instanceof SteelCatwalkBlock)) return false;
        if (!(pAdjacentBlockState.getBlock() instanceof SteelCatwalkBlock)) return false;

        if (pSide == Direction.UP || pSide == Direction.DOWN) return false;

        return pState.getValue(BlockStateProperties.BOTTOM) ==
                        pAdjacentBlockState.getValue(BlockStateProperties.BOTTOM);
    }
}
