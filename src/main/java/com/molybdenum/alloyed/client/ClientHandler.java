package com.molybdenum.alloyed.client;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.registry.ModItems;
import com.molybdenum.alloyed.registry.ModPonders;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        Alloyed.LOGGER.debug("Entering client setup for Alloyed ...");
        // Set up steel fishing rod
        ItemProperties.register(ModItems.STEEL_FISHING_ROD.get(), new ResourceLocation("cast"), (stack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                boolean isMainhand = entity.getMainHandItem() == stack;
                boolean isOffHand = entity.getOffhandItem() == stack;
                if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                    isOffHand = false;
                }
                return (isMainhand || isOffHand) && entity instanceof Player && ((Player) entity).fishing != null ? 1.0F : 0.0F;
            }
        });

        // Set up ponder scenes
        ModPonders.safeRegister();

        Alloyed.LOGGER.debug("Finished client setup for Alloyed");
    }
}
