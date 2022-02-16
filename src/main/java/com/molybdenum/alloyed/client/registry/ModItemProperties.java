package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class ModItemProperties {
    // Properties
    private static final IItemPropertyGetter FISHING_ROD_CAST = (heldStack, world, livingEntity) -> {
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
    };

    public static void register() {
        Alloyed.LOGGER.info("Registering ModItemProperties!");
        ItemModelsProperties.register(ModItems.STEEL_FISHING_ROD.get(), new ResourceLocation("cast"), FISHING_ROD_CAST);
    }
}
