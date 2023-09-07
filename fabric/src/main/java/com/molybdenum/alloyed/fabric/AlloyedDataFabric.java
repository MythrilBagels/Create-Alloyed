package com.molybdenum.alloyed.fabric;

import com.molybdenum.alloyed.Alloyed;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class AlloyedDataFabric implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
//		// Ensure that all mods are present if they are needed for data gen
//		for (Mods mod : Mods.values())
//			mod.assertForDataGen();

		Path railwaysResources = Paths.get(System.getProperty(ExistingFileHelper.EXISTING_RESOURCES));
		// fixme re-enable the existing file helper when porting lib's ResourcePackLoader.createPackForMod is fixed
		ExistingFileHelper helper = new ExistingFileHelper(
			Set.of(railwaysResources), Set.of("create"), false, null, null
		);
		Alloyed.registrate().setupDatagen(gen, helper);
		Alloyed.gatherData(gen);
	}
}
