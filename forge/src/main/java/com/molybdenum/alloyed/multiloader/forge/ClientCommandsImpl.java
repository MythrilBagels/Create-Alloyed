package com.molybdenum.alloyed.multiloader.forge;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.Railways;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.ClientCommandSourceStack;

public class ClientCommandsImpl {
	public static void sendSuccess(SharedSuggestionProvider provider, Component text) {
		if (provider instanceof ClientCommandSourceStack forge)
			forge.sendSuccess(text, true);
		else Alloyed.LOGGER.error("Invalid command source: " + provider);
	}

	public static void sendFailure(SharedSuggestionProvider provider, Component text) {
		if (provider instanceof ClientCommandSourceStack forge)
			forge.sendFailure(text);
		else Alloyed.LOGGER.error("Invalid command source: " + provider);
	}
}
