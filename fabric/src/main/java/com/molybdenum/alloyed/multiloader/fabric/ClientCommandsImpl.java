package com.molybdenum.alloyed.multiloader.fabric;

import com.molybdenum.alloyed.Alloyed;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

public class ClientCommandsImpl {
	public static void sendSuccess(SharedSuggestionProvider provider, Component text) {
		if (provider instanceof FabricClientCommandSource fabric)
			fabric.sendFeedback(text);
		else Alloyed.LOGGER.error("Invalid command source: " + provider);
	}

	public static void sendFailure(SharedSuggestionProvider provider, Component text) {
		if (provider instanceof FabricClientCommandSource fabric)
			fabric.sendError(text);
		else Alloyed.LOGGER.error("Invalid command source: " + provider);
	}
}
