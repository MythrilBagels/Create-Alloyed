package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingHookRenderer.class)
public abstract class FishingHookRendererMixin {
    @Redirect(
            method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    private boolean fixSteelFishingRodAppearingOnWrongHand(ItemStack itemStack, Item fishingRod) {
        return itemStack.is(fishingRod) || itemStack.is(ModItems.STEEL_FISHING_ROD.get());
    }
}
