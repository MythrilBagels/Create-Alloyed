package com.molybdenum.alloyed.common.content.blocks;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.simibubi.create.AllItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class BronzeBellBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final IntegerProperty NOTE = BlockStateProperties.NOTE;

    public BronzeBellBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(defaultBlockState()
                .setValue(POWERED, false)
                .setValue(NOTE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED, NOTE);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        boolean isPowered = pLevel.hasNeighborSignal(pPos);
        if (isPowered != pState.getValue(POWERED)) {
            if (isPowered) {
                attemptToRing(pLevel, pPos);
            }

            pLevel.setBlock(pPos, pState.setValue(POWERED, isPowered), 3);
        }
    }

    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        Entity entity = pProjectile.getOwner();
        Player player = entity instanceof Player ? (Player)entity : null;
        onHit(pLevel, pHit, player, true);
    }

    @Override
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (AllItems.WRENCH.isIn(pPlayer.getItemInHand(pHand))) {
            pState = pState.cycle(NOTE);
            pLevel.setBlock(pPos, pState, 3);
        }

        return onHit(pLevel, pHit, pPlayer, true) ?
                InteractionResult.sidedSuccess(pLevel.isClientSide) :
                InteractionResult.PASS;
    }

    public boolean onHit(Level pLevel, BlockHitResult pResult, @Nullable Player pPlayer, boolean pCanRingBell) {
        Direction direction = pResult.getDirection();
        BlockPos blockpos = pResult.getBlockPos();
        boolean canRing = !pCanRingBell || isProperHit(direction);

        if (canRing) {
            attemptToRing(pLevel, blockpos);
            if (pPlayer != null) pPlayer.awardStat(Stats.BELL_RING);

            return true;
        }

        return false;
    }

    private boolean isProperHit(Direction pDirection) {
        return pDirection.getAxis() != Direction.Axis.Y;
    }

    public void attemptToRing(Level pLevel, BlockPos pPos) {
        pLevel.blockEvent(pPos, this, 0, 0);
    }

    public boolean triggerEvent(BlockState pState, Level pLevel, BlockPos pPos, int pId, int pParam) {
        int i = pState.getValue(NOTE);
        float f = (float)Math.pow(2.0D, (double)(i - 12) / 12.0D);

        pLevel.playSound(null, pPos, ModSoundEvents.BRONZE_BELL.get(), SoundSource.RECORDS, 5.0F, f);
        return true;
    }
}
