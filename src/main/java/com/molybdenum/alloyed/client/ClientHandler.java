package com.molybdenum.alloyed.client;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.registry.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Alloyed.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
    // Proxy class for client setup
    public static void setupClient() {
        // Set up steel fishing rod
        ItemModelsProperties.register(ModItems.STEEL_FISHING_ROD.get(), new ResourceLocation("cast"), (heldStack, world, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                boolean isMainhand = livingEntity.getMainHandItem() == heldStack;
                boolean isOffHand = livingEntity.getOffhandItem() == heldStack;
                if (livingEntity.getMainHandItem().getItem() instanceof FishingRodItem) {
                    isOffHand = false;
                }
                return (isMainhand || isOffHand) && livingEntity instanceof PlayerEntity && ((PlayerEntity) livingEntity).fishing != null ? 1.0F : 0.0F;
            }
        });
    }
}
