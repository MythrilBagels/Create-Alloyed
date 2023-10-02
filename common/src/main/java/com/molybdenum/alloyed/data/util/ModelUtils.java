package com.molybdenum.alloyed.data.util;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import net.minecraft.world.item.Item;

public class ModelUtils {
    public static <T extends Item> void customTexture(DataGenContext<Item, T> ctx, RegistrateItemModelProvider prov) {
        prov.singleTexture(
                ctx.getName(),
                prov.mcLoc("item/generated"),
                "layer0",
                prov.modLoc("item/" + ctx.getName())
        );
    }

    public static <T extends Item> void customModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider prov, String path) {
        prov.singleTexture(
                ctx.getName(),
                prov.mcLoc("item/generated"),
                "layer0",
                prov.modLoc(path)
        );
    }
}
