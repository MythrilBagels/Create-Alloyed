package com.molybdenum.alloyed.items;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SteelFishingHook extends FishingHook {
    // No custom textures, just a custom class for the rod.
    public SteelFishingHook(EntityType<? extends FishingHook> p_150138_, Level p_150139_) {
        super(p_150138_, p_150139_);
    }

    public SteelFishingHook(Player p_37106_, Level p_37107_, int p_37108_, int p_37109_) {
        super(p_37106_, p_37107_, p_37108_, p_37109_);
    }

    @Override
    public boolean shouldStopFishing(Player player) {
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();
        // Modified bit
        boolean isMainhand = mainHandItem.is(ModItems.STEEL_FISHING_ROD.get());
        boolean isOffhand = offhandItem.is(ModItems.STEEL_FISHING_ROD.get());
        // ------------
        if (!player.isRemoved() && player.isAlive() && (isMainhand || isOffhand) && !(this.distanceToSqr(player) > 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }
}
