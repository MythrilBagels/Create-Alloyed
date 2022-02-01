package com.molybdenum.alloyed;

import com.molybdenum.alloyed.registry.ModBlocks;
import com.molybdenum.alloyed.client.ClientHandler;
import com.molybdenum.alloyed.registry.ModItems;
import com.molybdenum.alloyed.registry.ModSounds;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.NonNullLazyValue;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Alloyed.MOD_ID)
public class Alloyed {

    public static final String MOD_ID = "alloyed";

    private static final NonNullLazyValue<CreateRegistrate> registrate = CreateRegistrate.lazy(MOD_ID);



    public Alloyed() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register();
        ModBlocks.register();
        ModSounds.register(eventBus);

        eventBus.addListener(this::setupClient);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }

    public static CreateRegistrate getRegistrate() {
        return registrate.get();
    }


}
