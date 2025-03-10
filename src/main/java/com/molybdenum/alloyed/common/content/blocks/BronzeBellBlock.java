package com.molybdenum.alloyed.common.content.blocks;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.simibubi.create.AllItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BronzeBellBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final IntegerProperty NOTE = BlockStateProperties.NOTE;

    public BronzeBellBlock(Properties properties) {
        super(properties);
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

    private void playNote(BlockState pState, Level pLevel, BlockPos pPos, BlockHitResult pHit) {
        if (isProperHit(pHit.getDirection())) {
            int i = pState.getValue(NOTE);
            pLevel.addParticle(ParticleTypes.NOTE, (double)pPos.getX() + (double)0.5F, (double)pPos.getY() + 1.2, (double)pPos.getZ() + (double)0.5F, (double)i / (double)24.0F, 0.0F, 0.0F);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        playNote(pState, pLevel, pPos, pHit);
        return onHit(pLevel, pHit, pPlayer, true) ?
                InteractionResult.sidedSuccess(pLevel.isClientSide) :
                InteractionResult.PASS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack arg, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (isProperHit(pHit.getDirection())) {
            if (AllItems.WRENCH.isIn(arg)) {
                pState = pState.cycle(NOTE);
                pLevel.setBlock(pPos, pState, 3);
                playNote(pState, pLevel, pPos, pHit);
            }
        }
        if (onHit(pLevel, pHit, pPlayer, true)) return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
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
