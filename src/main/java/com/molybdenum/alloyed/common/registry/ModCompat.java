package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.tterrag.registrate.util.entry.ItemProviderEntry;

import java.util.List;
import java.util.function.Supplier;

public enum ModCompat {
    CREATE_DECO(ModCompatBlocks::getDecoBlocks, !Alloyed.isCreateDecoLoaded),
    FARMERS_DELIGHT(ModCompatItems::getFDItems, !Alloyed.isFarmersDelightLoaded)
    ;

    private final Supplier<List<ItemProviderEntry<?>>> entries;
    private final boolean shouldHide;

    ModCompat(Supplier<List<ItemProviderEntry<?>>> entries, boolean shouldHide) {
        this.entries = entries;
        this.shouldHide = shouldHide;
    }


    public List<ItemProviderEntry<?>> getEntries () {
        return entries.get();
    }

    public Boolean shouldHide () {
        return shouldHide;
    }
}
