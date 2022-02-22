package com.molybdenum.alloyed.common.compat.farmersdelight;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.items.ModItemTiers;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import vectorwing.farmersdelight.setup.Configuration;
import vectorwing.farmersdelight.tile.dispenser.CuttingBoardDispenseBehavior;

public class FarmersDelightCompat {
    public static Item newSteelKnife(Item.Properties properties) {
        return new SteelKnife(ModItemTiers.STEEL, 0.5F, -2.0F, properties);
    }


    public static void steelKnifeDispenseBehaviour() {
        SteelKnifeDispenserBehaviour.register();
    }
}