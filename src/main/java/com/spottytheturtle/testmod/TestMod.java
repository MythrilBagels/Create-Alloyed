package com.spottytheturtle.testmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;




@Mod(TestMod.MOD_ID)
public class TestMod {

    public static final String MOD_ID = "testmod";
    public static final ItemGroup TEST_GROUP = new TestGroup("testtab");


    public TestMod() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.init();

    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class TestGroup extends ItemGroup {

        public TestGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return RegistryHandler.TEST_ITEM.get().getDefaultInstance();
        }


    }
}
