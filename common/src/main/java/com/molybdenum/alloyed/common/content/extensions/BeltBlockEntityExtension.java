package com.molybdenum.alloyed.common.content.extensions;

public interface BeltBlockEntityExtension {
    void setAlloyedCasingType(AlloyedCasingType value);
    void setAlloyedCasingTypeRaw(AlloyedCasingType value);

    AlloyedCasingType getAlloyedCasingType();

    enum AlloyedCasingType {
        NONE, STEEL, BRONZE
    }
}
