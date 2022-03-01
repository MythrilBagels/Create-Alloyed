package com.molybdenum.alloyed.data.util;

import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateBlockstateProvider;
import com.simibubi.create.repack.registrate.providers.RegistrateItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModelUtils {
    public static <T extends Item> void customTexture(DataGenContext<Item, T> ctx, RegistrateItemModelProvider prov) {
        prov.singleTexture(
                ctx.getName(),
                prov.mcLoc("item/generated"),
                "layer0",
                prov.modLoc("item/" + ctx.getName())
        );
    }
}
