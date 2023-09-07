package com.molybdenum.alloyed.util.fabric;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;

public class UtilsImpl {
	public static boolean isModLoaded(String id, @Nullable String fabricId) {
		return FabricLoader.getInstance().isModLoaded(fabricId != null ? fabricId : id);
	}

	public static boolean isDevEnv() {
		return FabricLoader.getInstance().isDevelopmentEnvironment();
	}
}
