package com.molybdenum.alloyed.util;

import com.mojang.blaze3d.platform.InputConstants;
import com.railwayteam.railways.Railways;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Locale;

public class Utils {
	@ExpectPlatform
	public static boolean isModLoaded(String id, @Nullable String fabricId) {
		throw new AssertionError();
	}

	@ExpectPlatform
	@Contract // shut
	public static boolean isDevEnv() {
		throw new AssertionError();
	}

	@ExpectPlatform
	@Environment(EnvType.CLIENT)
	public static boolean isActiveAndMatches(KeyMapping mapping, InputConstants.Key keyCode) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void sendCreatePacketToServer(SimplePacketBase packet) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void postChunkEventClient(LevelChunk chunk, boolean load) {
		throw new AssertionError();
	}
}
