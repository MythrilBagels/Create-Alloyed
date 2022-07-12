package com.molybdenum.alloyed;

import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModCompatBlocks;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.client.registry.ModSounds;
import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.content.contraptions.components.structureMovement.interaction.DoorMovingInteraction;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Alloyed.MOD_ID)
public class Alloyed {

    public static final String MOD_ID = "alloyed";
    public static final Logger LOGGER = LogManager.getLogger();

    // Compat
    public static boolean isFarmersDelightLoaded = false;
    public static boolean isCreateDecoLoaded = false;

    private static final NonNullSupplier<CreateRegistrate> REGISTRATE = CreateRegistrate.lazy(MOD_ID);

    public Alloyed() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        isFarmersDelightLoaded = ModList.get().isLoaded("farmersdelight");
        isCreateDecoLoaded = ModList.get().isLoaded("createdeco");

        ModItems.register();
        ModBlocks.register();
        ModCompatItems.register();
        ModCompatBlocks.register();
        ModSounds.register(eventBus);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static CreateRegistrate getRegistrate() {
        return REGISTRATE.get();
    }

}
