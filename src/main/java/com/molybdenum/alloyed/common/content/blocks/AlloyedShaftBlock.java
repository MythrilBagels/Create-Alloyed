package com.molybdenum.alloyed.common.content.blocks;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AlloyedShaftBlock extends EncasedShaftBlock {
    public AlloyedShaftBlock(Properties properties, Supplier<Block> casing) {
        super(properties, casing);
    }

    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return AllBlockEntityTypes.ENCASED_SHAFT.get();
    }
}
