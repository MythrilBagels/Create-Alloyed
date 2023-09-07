package com.molybdenum.alloyed.fabric;

import com.molybdenum.alloyed.AlloyedClient;
import net.fabricmc.api.ClientModInitializer;

public class AlloyedClientImpl implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AlloyedClient.init();
	}
}
