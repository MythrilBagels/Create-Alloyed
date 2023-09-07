package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;

@SuppressWarnings("deprecation")
public class ModItemProperties {
    // Properties
    private static final ClampedItemPropertyFunction FISHING_ROD_CAST = (stack, level, entity, i) -> {
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
    };

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModItemProperties!");
        ItemProperties.register(ModItems.STEEL_FISHING_ROD.get(), new ResourceLocation("cast"), FISHING_ROD_CAST);
    }
}
