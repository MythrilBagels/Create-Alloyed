package com.molybdenum.alloyed;

import com.molybdenum.alloyed.blocks.ModBlocks;
import com.molybdenum.alloyed.fluids.ModFluids;
import com.molybdenum.alloyed.items.ModItems;
import com.molybdenum.alloyed.sounds.ModSounds;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.NonNullLazyValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Alloyed.MOD_ID)
public class Alloyed {

    public static final String MOD_ID = "alloyed";

    // Registrate
    private static final NonNullLazyValue<CreateRegistrate> registrate = CreateRegistrate.lazy(MOD_ID);

    public static final Logger LOGGER = LogManager.getLogger();

    public Alloyed() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModItems.register();
        ModBlocks.register();
        ModFluids.register(eventBus);
        ModSounds.register(eventBus);
        //ModTileEntities.register(eventBus);
        //ModContainers.register(eventBus);

        eventBus.addListener(this::setupClient);
        MinecraftForge.EVENT_BUS.register(this);
    }



    private void setupClient(final FMLClientSetupEvent event) {
        /*
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());
        ScreenManager.registerFactory(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(), LightningChannelerScreen::new);
        */
        ItemProperties.register(ModItems.STEEL_FISHING_ROD.get(), new ResourceLocation("cast"), (heldStack, world, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                boolean isMainhand = livingEntity.getMainHandItem() == heldStack;
                boolean isOffHand = livingEntity.getOffhandItem() == heldStack;
                if (livingEntity.getMainHandItem().getItem() instanceof FishingRodItem) {
                    isOffHand = false;
                }
                return (isMainhand || isOffHand) && livingEntity instanceof Player && ((Player) livingEntity).fishing != null ? 1.0F : 0.0F;
            }
        });
    }

    // Registrate getter
    @SuppressWarnings("deprecation")
    public static CreateRegistrate getRegistrate() {
        return registrate.get();
    }


}
