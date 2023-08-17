package com.molybdenum.alloyed.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Contract;

import java.util.function.Function;

public class EntityUtils {
	/**
	 * Gives a player an item. Plays the pickup sound, and drops whatever can't be picked up.
	 */
	@ExpectPlatform
	public static void givePlayerItem(Player player, ItemStack stack) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static double getReachDistance(Player player) {
		throw new AssertionError();
	}

	/**
	 * Fire a use event.
	 * @return true if the use is allowed, false otherwise
	 */
	@ExpectPlatform
	@Contract // shut
	public static boolean handleUseEvent(Player player, InteractionHand hand, BlockHitResult hit) {
		throw new AssertionError();
	}

	public static boolean isHolding(Player player, Function<ItemStack, Boolean> test) {
		return test.apply(player.getItemInHand(InteractionHand.MAIN_HAND))
				|| test.apply(player.getItemInHand(InteractionHand.OFF_HAND));
	}

	public static boolean isHoldingItem(Player player, Function<Item, Boolean> test) {
		return isHolding(player, (stack) -> test.apply(stack.getItem()));
	}
}
