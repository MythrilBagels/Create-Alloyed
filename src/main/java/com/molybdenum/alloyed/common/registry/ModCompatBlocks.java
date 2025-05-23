package com.molybdenum.alloyed.common.registry;

import com.github.talrey.createdeco.api.Catwalks;
import com.github.talrey.createdeco.blocks.CatwalkBlock;
import com.github.talrey.createdeco.blocks.CatwalkRailingBlock;
import com.github.talrey.createdeco.blocks.CatwalkStairBlock;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelCatwalkCTBehaviour;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;

import java.util.List;

import static com.github.talrey.createdeco.BlockRegistry.*;

@SuppressWarnings("removal")
public class ModCompatBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.REGISTRATE;
    private static final String metal = "steel";


    // Create: Deco
    public static final BlockEntry<CatwalkBlock> STEEL_CATWALK = Catwalks.build(
                    REGISTRATE, metal)
            .recipe( (ctx, prov)-> {
                Catwalks.recipeCatwalk(metal, ModBlocks.STEEL_BARS, ctx, prov);
                Catwalks.recipeStonecutting(ModItems.STEEL_INGOT::asItem, ctx, prov, 4);
            }).onRegister(CreateRegistrate.connectedTextures(SteelCatwalkCTBehaviour::new)).register();

    public static final BlockEntry<CatwalkStairBlock> STEEL_CATWALK_STAIRS = Catwalks.buildStair(
                    REGISTRATE, metal)
            .recipe( (ctx, prov)-> {
                Catwalks.recipeStairs(metal, ModBlocks.STEEL_BARS, ctx, prov);
                Catwalks.recipeStonecutting(ModItems.STEEL_INGOT::asItem, ctx, prov, 2);
            }).register();

    public static final BlockEntry<CatwalkRailingBlock> STEEL_CATWALK_RAILING = Catwalks.buildRailing(
                    REGISTRATE, metal)
            .recipe( (ctx, prov)-> {
                Catwalks.recipeRailing(metal, ModBlocks.STEEL_BARS, ctx, prov);
                Catwalks.recipeStonecutting(ModItems.STEEL_INGOT::asItem, ctx, prov, 8);
            }).register();


    public static void register() {
        CATWALKS.put(metal, STEEL_CATWALK);
        CATWALK_RAILINGS.put(metal, STEEL_CATWALK_RAILING);
        CATWALK_STAIRS.put(metal, STEEL_CATWALK_STAIRS);
        Alloyed.LOGGER.debug("Registering ModCompatBlocks!");
        REGISTRATE.setCreativeTab(ModCreativeModeTab.MAIN_TAB);
    }

    public static List<ItemProviderEntry<?, ?>> getDecoBlocks() {
        return List.of(STEEL_CATWALK, STEEL_CATWALK_STAIRS, STEEL_CATWALK_RAILING);
    }
}
