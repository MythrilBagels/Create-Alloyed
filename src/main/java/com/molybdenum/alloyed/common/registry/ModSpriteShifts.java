package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter.CTType;

public class ModSpriteShifts {
    public static final CTSpriteShiftEntry STEEL_CATWALK_TOP =
            CTSpriteShifter.getCT(CTType.OMNIDIRECTIONAL,
                    Alloyed.asResource("block/steel_catwalk"),
                    Alloyed.asResource("block/steel_catwalk_connected"));

    public static final CTSpriteShiftEntry STEEL_SHEET_METAL =
            CTSpriteShifter.getCT(CTType.VERTICAL,
                    Alloyed.asResource("block/steel_sheet_metal"),
                    Alloyed.asResource("block/steel_sheet_metal_connected"));

    public static void register() { Alloyed.LOGGER.debug("Registering ModSpriteShifts!"); }
}
