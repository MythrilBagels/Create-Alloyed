package com.molybdenum.alloyed.util.fabric;

import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;

public class ItemUtilsImpl {
    public static int nextTabId() {
        return ItemGroupUtil.expandArrayAndGetId();
    }
}
