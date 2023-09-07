package com.molybdenum.alloyed.util;

import com.mojang.blaze3d.platform.InputConstants;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class Utils {
	@ExpectPlatform
	public static boolean isModLoaded(String id, @Nullable String fabricId) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static boolean isDevEnv() {
		throw new AssertionError();
	}
}
