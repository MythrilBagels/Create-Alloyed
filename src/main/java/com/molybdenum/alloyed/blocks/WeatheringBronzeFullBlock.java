package com.molybdenum.alloyed.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class WeatheringBronzeFullBlock extends Block implements WeatheringBronze {
    private final WeatheringBronze.WeatherState weatherState;

    public WeatheringBronzeFullBlock(WeatheringBronze.WeatherState p_154925_, BlockBehaviour.Properties p_154926_) {
        super(p_154926_);
        this.weatherState = p_154925_;
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState p_154929_, ServerLevel p_154930_, BlockPos p_154931_, Random p_154932_) {
        this.onRandomTick(p_154929_, p_154930_, p_154931_, p_154932_);
    }

    public boolean isRandomlyTicking(BlockState p_154935_) {
        return WeatheringBronze.getNext(p_154935_.getBlock()).isPresent();
    }

    public WeatheringBronze.WeatherState getAge() {
        return this.weatherState;
    }
}