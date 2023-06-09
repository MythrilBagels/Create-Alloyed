package com.molybdenum.alloyed.client.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.molybdenum.alloyed.Alloyed;

public class ModPartialModels {

    public static final PartialModel
            STEEL_BELT_COVER_X = block("belt_cover/steel_belt_cover_x"),
            STEEL_BELT_COVER_Z = block("belt_cover/steel_belt_cover_z");

    private static PartialModel block(String path) {
        return new PartialModel(Alloyed.asResource("block/" + path));
    }
    public static void register() {}
}
