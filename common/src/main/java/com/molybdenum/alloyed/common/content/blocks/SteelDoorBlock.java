package com.molybdenum.alloyed.common.content.blocks;

import net.minecraft.world.level.block.DoorBlock;

public class SteelDoorBlock extends DoorBlock {
    public SteelDoorBlock(Properties p_52737_) {
        super(p_52737_);
    }

    @Override
    public int getCloseSound() {
        return 1011;
    }

    @Override
    public int getOpenSound() {
        return 1005;
    }
}
