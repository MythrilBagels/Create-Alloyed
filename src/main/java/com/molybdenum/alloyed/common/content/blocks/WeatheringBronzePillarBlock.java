package com.molybdenum.alloyed.common.content.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class WeatheringBronzePillarBlock extends ConnectedPillarBlock implements WeatheringCopper {
    public static final MapCodec<WeatheringBronzePillarBlock> CODEC = RecordCodecBuilder.mapCodec((weatheringBronzePillarBlockInstance) -> weatheringBronzePillarBlockInstance.group(WeatherState.CODEC.fieldOf("weathering_state").forGetter(ChangeOverTimeBlock::getAge), propertiesCodec()).apply(weatheringBronzePillarBlockInstance, WeatheringBronzePillarBlock::new));
    private final WeatheringCopper.WeatherState weatherState;

    public MapCodec<WeatheringBronzePillarBlock> codec() {
        return CODEC;
    }

    public WeatheringBronzePillarBlock(WeatheringCopper.WeatherState weatherState, BlockBehaviour.Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }
}
