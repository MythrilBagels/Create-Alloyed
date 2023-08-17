package com.molybdenum.alloyed.fabric;

import com.mojang.brigadier.CommandDispatcher;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.Railways;
import com.molybdenum.alloyed.fabric.events.CommonEventsFabric;
import com.molybdenum.alloyed.registry.fabric.CRParticleTypesParticleEntryImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.util.function.BiConsumer;

public class AlloyedImpl implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
		CommonEventsFabric.init();
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

	public static void registerCommands(BiConsumer<CommandDispatcher<CommandSourceStack>, Boolean> consumer) {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> consumer.accept(dispatcher, environment.includeDedicated));
	}

	public static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
		ModLoadingContext.registerConfig(Alloyed.MOD_ID, type, spec);
	}
}
