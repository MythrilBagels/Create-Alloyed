package com.molybdenum.alloyed.forge;

import com.mojang.brigadier.CommandDispatcher;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.multiloader.Env;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.MavenVersionStringHelper;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

@Mod(Alloyed.MOD_ID)
@Mod.EventBusSubscriber
public class AlloyedImpl {
	static IEventBus bus;

	public AlloyedImpl() {
		bus = FMLJavaModLoadingContext.get().getModEventBus();
		Alloyed.init();
		//noinspection Convert2MethodRef
		Env.CLIENT.runIfCurrent(() -> () -> AlloyedClientImpl.init());
	}

	public static String findVersion() {
		String versionString = "UNKNOWN";

		List<IModInfo> infoList = ModList.get().getModFileById(Alloyed.MOD_ID).getMods();
		if (infoList.size() > 1) {
			Alloyed.LOGGER.error("Multiple mods for MOD_ID: " + Alloyed.MOD_ID);
		}
		for (IModInfo info : infoList) {
			if (info.getModId().equals(Alloyed.MOD_ID)) {
				versionString = MavenVersionStringHelper.artifactVersionToString(info.getVersion());
				break;
			}
		}
		return versionString;
	}

	public static void finalizeRegistrate() {
		Alloyed.registrate().registerEventListeners(bus);
	}
}
