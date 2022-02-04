package com.molybdenum.alloyed.data.registrate;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.providers.ProviderType;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PostRegistrationHelper {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate();

    // Lang

    public static void addBlockLang(String name, String lang) {
        BlockEntry<Block> blockEntry = (BlockEntry<Block>) REGISTRATE.get(name, Block.class);
        REGISTRATE.setDataGenerator(name, Block.class, ProviderType.LANG, registrateLangProvider -> registrateLangProvider.add(blockEntry.get(), lang));
    }
}