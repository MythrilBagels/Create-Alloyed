package com.spottytheturtle.testmod;

import com.spottytheturtle.testmod.blocks.ModBlocks;
import com.spottytheturtle.testmod.container.ModContainers;
import com.spottytheturtle.testmod.fluids.ModFluids;
import com.spottytheturtle.testmod.items.ModItems;
import com.spottytheturtle.testmod.items.custom.Firestone;
import com.spottytheturtle.testmod.screen.LightningChannelerScreen;
import com.spottytheturtle.testmod.tileentity.ModTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(TestMod.MOD_ID)
public class TestMod {

    public static final String MOD_ID = "testmod";



    public TestMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        //RegistryHandler.init();
        ModFluids.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());
        ScreenManager.registerFactory(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(), LightningChannelerScreen::new);
    }


}
