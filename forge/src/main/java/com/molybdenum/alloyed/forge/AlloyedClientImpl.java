package com.molybdenum.alloyed.forge;

import com.molybdenum.alloyed.Railways;
import com.molybdenum.alloyed.RailwaysClient;
import com.molybdenum.alloyed.registry.CRExtraDisplayTags;
import com.molybdenum.alloyed.registry.CRParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class AlloyedClientImpl {
	public static void init() {
		AlloyedClient.init();
	}
}
