package com.molybdenum.alloyed.forge.events;

import com.molybdenum.alloyed.Railways;
import com.molybdenum.alloyed.content.conductor.ConductorEntity;
import com.molybdenum.alloyed.content.conductor.toolbox.MountedToolbox;
import com.molybdenum.alloyed.events.CommonEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CommonEventsForge {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == Phase.START)
			CommonEvents.onWorldTickStart(event.level);
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer player)
			CommonEvents.onPlayerJoin(player);
	}

	@SubscribeEvent
	public static void onWorldJoin(LevelEvent.Load event) {
		CommonEvents.backupDisplayRegister();
	}
}
