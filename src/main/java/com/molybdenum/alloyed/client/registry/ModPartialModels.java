package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class ModPartialModels {

    public static final PartialModel
            STEEL_BELT_COVER_X = block("belt_cover/steel_belt_cover_x"),
            STEEL_BELT_COVER_Z = block("belt_cover/steel_belt_cover_z");

    private static PartialModel block(String path) {
        return PartialModel.of(Alloyed.asResource("block/" + path));
    }
    public static void register() {}
}
