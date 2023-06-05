package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;

import static com.simibubi.create.foundation.block.connected.AllCTTypes.*;

public class ModSpriteShifts {

    public static final CTSpriteShiftEntry STEEL_CASING = ct(OMNIDIRECTIONAL, "steel_casing"),
            STEEL_ENCASED_COGWHEEL_SIDE = ct(VERTICAL, "steel_encased_cogwheel_side"),
            STEEL_ENCASED_COGWHEEL_OTHERSIDE = ct(HORIZONTAL, "steel_encased_cogwheel_side");

    public static final CTSpriteShiftEntry STEEL_CATWALK_TOP = ct(OMNIDIRECTIONAL, "steel_catwalk"),
            STEEL_SHEET_METAL = ct(VERTICAL, "steel_sheet_metal");

    public static final CTSpriteShiftEntry STEEL_SCAFFOLD = ct(HORIZONTAL, "scaffold/steel_scaffold"),
            STEEL_SCAFFOLD_INSIDE = ct(HORIZONTAL, "scaffold/steel_scaffold_inside");

    private static CTSpriteShiftEntry ct(AllCTTypes type, String name) {
        return CTSpriteShifter.getCT(type,
                Alloyed.asResource("block/" + name),
                Alloyed.asResource("block/" + name + "_connected"));
    }

    public static void register() { Alloyed.LOGGER.debug("Registering ModSpriteShifts!"); }
}
