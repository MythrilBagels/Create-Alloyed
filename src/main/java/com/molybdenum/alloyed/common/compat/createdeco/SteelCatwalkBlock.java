package com.molybdenum.alloyed.common.compat.createdeco;

import com.github.talrey.createdeco.blocks.CatwalkBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import org.lwjgl.system.NonnullDefault;

public class SteelCatwalkBlock extends CatwalkBlock {
    public SteelCatwalkBlock(Properties props) {
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
