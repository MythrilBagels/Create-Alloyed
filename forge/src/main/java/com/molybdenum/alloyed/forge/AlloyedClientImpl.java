package com.molybdenum.alloyed.forge;

import com.molybdenum.alloyed.AlloyedClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class AlloyedClientImpl {
	public static void init() {
		AlloyedClient.init();
	}
}
