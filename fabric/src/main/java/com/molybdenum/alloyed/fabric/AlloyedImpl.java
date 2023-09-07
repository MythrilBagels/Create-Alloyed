package com.molybdenum.alloyed.fabric;

import com.molybdenum.alloyed.Alloyed;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class AlloyedImpl implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
	}

	public static String findVersion() {
		return FabricLoader.getInstance()
				.getModContainer(Alloyed.MOD_ID)
				.orElseThrow()
				.getMetadata()
				.getVersion()
				.getFriendlyString();
	}

	public static void finalizeRegistrate() {
		Alloyed.registrate().register();
	}
}
