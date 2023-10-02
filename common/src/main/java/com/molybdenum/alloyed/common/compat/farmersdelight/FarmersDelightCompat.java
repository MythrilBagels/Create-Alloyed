package com.molybdenum.alloyed.common.compat.farmersdelight;

import com.molybdenum.alloyed.common.item.ModItemTiers;
import net.minecraft.world.item.Item;

public class FarmersDelightCompat {
    public static Item newSteelKnife(Item.Properties properties) {
        return new SteelKnife(ModItemTiers.STEEL, 0.5F, -2.0F, properties);
    }

    public static void steelKnifeDispenseBehaviour() {
        SteelKnifeDispenseBehaviour.register();
    }
}
