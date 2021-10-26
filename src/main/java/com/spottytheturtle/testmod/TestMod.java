package com.spottytheturtle.testmod;

import com.spottytheturtle.testmod.fluids.ModFluids;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(TestMod.MOD_ID)
public class TestMod {

    public static final String MOD_ID = "testmod";
    public static final ItemGroup TEST_GROUP = new TestGroup("testtab");


    public TestMod() {
        // Register ourselves for server and other game events we are interested in
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RegistryHandler.init();
        ModFluids.register(eventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());
    }

    public static class TestGroup extends ItemGroup {

        public TestGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.TEST_ITEM.get());
        }


    }
}
