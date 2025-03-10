package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.common.util.EncasingHelper;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShaftBlock.class)
public abstract class ShaftBlockMixin {

    @Inject(
            method = "useItemOn",
            at = @At(value = "INVOKE", target = "Lnet/createmod/catnip/placement/IPlacementHelper;matchesItem(Lnet/minecraft/world/item/ItemStack;)Z"),
            cancellable = true

    )
    private void tryEncaseWithAlloyedCasings(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        ItemStack heldItem = player.getItemInHand(hand);
        ItemInteractionResult result = EncasingHelper
                .tryEncaseWithSteel(EncasingHelper.EncaseType.SHAFT, state, level, pos, heldItem, player, hand, hitResult);

        if (result.consumesAction()) {
            cir.setReturnValue(result);
            cir.cancel();
        }
    }
}
