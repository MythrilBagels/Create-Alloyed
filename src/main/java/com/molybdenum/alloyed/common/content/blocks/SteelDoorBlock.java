package com.molybdenum.alloyed.common.content.blocks;

import com.molybdenum.alloyed.common.registry.ModBlockSetTypes;
import net.minecraft.world.level.block.DoorBlock;

public class SteelDoorBlock extends DoorBlock {
    public SteelDoorBlock(Properties pProperties, boolean locked) {
        super(pProperties, locked ? ModBlockSetTypes.STEEL : ModBlockSetTypes.FLIMSY_STEEL);
    }
}
