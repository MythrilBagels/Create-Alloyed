package com.molybdenum.alloyed.common.compat.farmersdelight;

import com.molybdenum.alloyed.common.registry.ModCompatItems;
import vectorwing.farmersdelight.tile.dispenser.CuttingBoardDispenseBehavior;

public class SteelKnifeDispenserBehaviour {
    public static void register() {
        CuttingBoardDispenseBehavior
                .registerBehaviour(ModCompatItems.STEEL_KNIFE.get(), new CuttingBoardDispenseBehavior());
    }
}
