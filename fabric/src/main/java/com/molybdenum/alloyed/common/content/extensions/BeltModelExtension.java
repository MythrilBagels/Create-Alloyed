package com.molybdenum.alloyed.common.content.extensions;

import io.github.fabricators_of_create.porting_lib.model.data.ModelProperty;

public interface BeltModelExtension {
    ModelProperty<BeltBlockEntityExtension.AlloyedCasingType> ALLOYED_CASING_PROPERTY
            = new ModelProperty<>();
}
