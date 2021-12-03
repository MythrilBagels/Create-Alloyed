package com.spottytheturtle.create_composition;

import com.spottytheturtle.create_composition.blocks.ModBlocks;
import com.spottytheturtle.create_composition.fluids.ModFluids;
import com.spottytheturtle.create_composition.items.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(create_composition.MOD_ID)
public class create_composition {

    public static final String MOD_ID = "create_composition";



    public create_composition() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModFluids.register(eventBus);
        //ModTileEntities.register(eventBus);
        //ModContainers.register(eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        /*
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());
        ScreenManager.registerFactory(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(), LightningChannelerScreen::new);
        */

    }


}
