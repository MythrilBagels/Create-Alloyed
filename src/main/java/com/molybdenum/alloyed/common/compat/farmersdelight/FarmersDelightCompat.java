package com.molybdenum.alloyed.common.compat.farmersdelight;

import com.molybdenum.alloyed.common.item.ModItemTiers;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class FarmersDelightCompat {
    public static Item newSteelKnife(Item.Properties properties) {
        return new SteelKnife(ModItemTiers.STEEL, properties.attributes(KnifeItem.createAttributes(ModItemTiers.STEEL, 0.5F, -2.0F)));
    }

    public static void steelKnifeDispenseBehaviour() {
        SteelKnifeDispenseBehaviour.register();
    }
}
