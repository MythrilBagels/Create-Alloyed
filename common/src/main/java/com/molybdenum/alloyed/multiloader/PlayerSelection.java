package com.molybdenum.alloyed.multiloader;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Find players to send S2C packets to.
 */
public abstract class PlayerSelection {
	public abstract void accept(ResourceLocation id, FriendlyByteBuf buffer);

	@ExpectPlatform
	public static PlayerSelection all() {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static PlayerSelection of(ServerPlayer player) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static PlayerSelection tracking(Entity entity) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static PlayerSelection tracking(BlockEntity be) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static PlayerSelection tracking(ServerLevel level, BlockPos pos) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static PlayerSelection trackingAndSelf(ServerPlayer player) {
		throw new AssertionError();
	}
}
