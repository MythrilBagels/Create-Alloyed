package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BeltBlock.class)
public abstract class BeltBlockMixin implements IBE<BeltBlockEntity> {

    @Shadow(remap=false)
    public abstract void updateCoverProperty(LevelAccessor world, BlockPos pos, BlockState state);

    @Inject(
            method = "use(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;",
            at = @At("TAIL"),
            cancellable = true
    )
    private void tryEncaseWithAlloyedCasings(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack heldItem = player.getItemInHand(handIn);

        if (ModBlocks.STEEL_CASING.isIn(heldItem)) {
            withBlockEntityDo(world, pos, be ->
                    ((BeltBlockEntityExtension) be).setAlloyedCasingType(BeltBlockEntityExtension.AlloyedCasingType.STEEL));
            updateCoverProperty(world, pos, world.getBlockState(pos));
            cir.setReturnValue(InteractionResult.SUCCESS);
            cir.cancel();
        }
    }

    @Inject(
            method = "onWrenched(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;",
            at = @At(value = "RETURN", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private void tryUnencaseWithAlloyedCasings(BlockState state, UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, Level world, Player player, BlockPos pos) {
        withBlockEntityDo(world, pos, be -> {
            if (be instanceof BeltBlockEntityExtension bex && bex.getAlloyedCasingType() != BeltBlockEntityExtension.AlloyedCasingType.NONE) {
                bex.setAlloyedCasingType(BeltBlockEntityExtension.AlloyedCasingType.NONE);
            }
        });
    }
}
