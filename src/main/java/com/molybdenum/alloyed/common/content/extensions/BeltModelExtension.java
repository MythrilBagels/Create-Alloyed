package com.molybdenum.alloyed.common.content.extensions;

import net.minecraftforge.client.model.data.ModelProperty;

public interface BeltModelExtension {
    ModelProperty<BeltBlockEntityExtension.AlloyedCasingType> ALLOYED_CASING_PROPERTY
            = new ModelProperty<>();
}
