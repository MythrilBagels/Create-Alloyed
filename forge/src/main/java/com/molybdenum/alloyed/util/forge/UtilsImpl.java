package com.molybdenum.alloyed.util.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.Nullable;

public class UtilsImpl {
	public static boolean isModLoaded(String id, @Nullable String fabricId) {
		return ModList.get().isLoaded(id);
	}

	public static boolean isDevEnv() {
		return !FMLLoader.isProduction();
	}
}
