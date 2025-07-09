package com.molybdenum.alloyed.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.molybdenum.alloyed.common.content.extensions.BeltModelExtension;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltModel;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(BeltModel.class)
public class BeltModelMixin implements BeltModelExtension {

    @Inject(
            method = "getQuads",
            at = @At(value = "RETURN", ordinal = 1),
            cancellable = true,
            remap = false
    )
    private void handleAlloyedCasingRendering(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir, @Local List quads, @Local(ordinal = 0) boolean cover) {
        BeltBlockEntityExtension.AlloyedCasingType alloyedType = extraData.get(ALLOYED_CASING_PROPERTY);
        if (alloyedType == BeltBlockEntityExtension.AlloyedCasingType.NONE) return;

        ArrayList<BakedQuad> newQuads = new ArrayList<>(quads);
        var belt_cover_x = ModPartialModels.STEEL_BELT_COVER_X;
        var belt_cover_z = ModPartialModels.STEEL_BELT_COVER_Z;
        var belt_casing = ModSpriteShifts.STEEL_BELT_CASING;
        if (alloyedType == BeltBlockEntityExtension.AlloyedCasingType.BRONZE) {
            belt_cover_x = ModPartialModels.BRONZE_BELT_COVER_X;
            belt_cover_z = ModPartialModels.BRONZE_BELT_COVER_Z;
            belt_casing = ModSpriteShifts.BRONZE_BELT_CASING;
        }

        if (cover) {
            boolean alongX = state.getValue(BeltBlock.HORIZONTAL_FACING)
                    .getAxis() == Direction.Axis.X;
            BakedModel coverModel =
                    (alongX ? belt_cover_x : belt_cover_z).get();
            newQuads.addAll(coverModel.getQuads(state, side, rand));
        }

        for (int i = 0; i < newQuads.size(); i++) {
            BakedQuad quad = newQuads.get(i);
            TextureAtlasSprite original = quad.getSprite();
            if (original != belt_casing.getOriginal())
                continue;

            BakedQuad newQuad = BakedQuadHelper.clone(quad);
            int[] vertexData = newQuad.getVertices();

            for (int vertex = 0; vertex < 4; vertex++) {
                float u = BakedQuadHelper.getU(vertexData, vertex);
                float v = BakedQuadHelper.getV(vertexData, vertex);
                BakedQuadHelper.setU(vertexData, vertex, belt_casing.getTargetU(u));
                BakedQuadHelper.setV(vertexData, vertex, belt_casing.getTargetV(v));
            }

            newQuads.set(i, newQuad);
        }

        cir.setReturnValue(newQuads);
    }

    @Inject(
            method = "getParticleIcon",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void returnAlloyedSpritesIfNeeded(ModelData data, CallbackInfoReturnable<TextureAtlasSprite> cir) {
        if (!data.has(ALLOYED_CASING_PROPERTY)) return;
        if (data.get(ALLOYED_CASING_PROPERTY) == BeltBlockEntityExtension.AlloyedCasingType.STEEL) {
            cir.setReturnValue(ModSpriteShifts.STEEL_CASING.getOriginal());
            cir.cancel();
        }
        if (data.get(ALLOYED_CASING_PROPERTY) == BeltBlockEntityExtension.AlloyedCasingType.BRONZE) {
            cir.setReturnValue(ModSpriteShifts.BRONZE_CASING.getOriginal());
            cir.cancel();
        }
    }
}
