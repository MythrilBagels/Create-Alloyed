package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.item.ModArmourMaterials;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Alloyed.MOD_ID)
public class Alloyed {

    public static final String MOD_ID = "alloyed";
    public static final Logger LOGGER = LogManager.getLogger();

    // Compat
    public static boolean isFarmersDelightLoaded = false;
    public static boolean isCreateDecoLoaded = false;

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public Alloyed(IEventBus eventBus, ModContainer container) {
        REGISTRATE.registerEventListeners(eventBus);

        isFarmersDelightLoaded = ModList.get().isLoaded("farmersdelight");
        isCreateDecoLoaded = ModList.get().isLoaded("createdeco");

        ModArmourMaterials.register(eventBus);
        ModBlockSetTypes.register();
        ModBlocks.register();
        ModItems.register();
        ModCreativeModeTab.register(eventBus);
        ModCompatItems.register();
        if (isCreateDecoLoaded)
            ModCompatBlocks.register();
        ModSoundEvents.register(eventBus);

        if (FMLEnvironment.dist.isClient())
            AlloyedClient.onClientInit(eventBus);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
