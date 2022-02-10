package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin {
    @Redirect(
            method = "shouldStopFishing(Lnet/minecraft/world/entity/player/Player;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    private boolean fixSteelFishingRodNotCasting(ItemStack itemStack, Item fishingRod) {
        return itemStack.is(fishingRod) || itemStack.is(ModItems.STEEL_FISHING_ROD.get());
    }
}
