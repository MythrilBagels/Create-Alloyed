package com.molybdenum.alloyed.base;

import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FacingBlockCTBehaviour extends ConnectedTextureBehaviour.Base {
  CTSpriteShiftEntry shift;

  public FacingBlockCTBehaviour(CTSpriteShiftEntry shift) {
    this.shift = shift;
  }

  @Override
  public boolean connectsTo (BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos, BlockPos otherPos, Direction face) {
    return (state.getBlock() == other.getBlock() && state.getValue(HorizontalConnectedBlock.FACING) == other.getValue(HorizontalConnectedBlock.FACING));
  }

  @Override
  protected Direction getUpDirection(BlockAndTintGetter reader, BlockPos pos, BlockState state, Direction face) {
    Direction facing = state.getValue(HorizontalConnectedBlock.FACING);
    if (facing == null) return super.getRightDirection(reader, pos, state, face);

    return face.getAxis().isHorizontal() ? Direction.UP : facing;
  }

  @Override
  protected Direction getRightDirection(BlockAndTintGetter reader, BlockPos pos, BlockState state, Direction face) {
    Direction facing = state.getValue(HorizontalConnectedBlock.FACING);
    if (facing == null) return super.getRightDirection(reader, pos, state, face);

    if (face.getAxis() == Direction.Axis.Y) return facing.getCounterClockWise();
    return face.getAxis() == Direction.Axis.X ? Direction.SOUTH : Direction.WEST;
  }

  @Override
  public @Nullable CTSpriteShiftEntry getShift(BlockState state, Direction direction, @Nullable TextureAtlasSprite sprite) {
    return shift;
  }
}
