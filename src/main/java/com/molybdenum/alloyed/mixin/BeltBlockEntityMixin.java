package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.molybdenum.alloyed.common.content.extensions.BeltModelExtension;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltModel;
import com.simibubi.create.foundation.utility.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BeltBlockEntity.class)
public class BeltBlockEntityMixin extends KineticBlockEntity implements BeltBlockEntityExtension {
    @Shadow(remap=false) public BeltBlockEntity.CasingType casing;
    @Shadow(remap=false) public boolean covered;
    AlloyedCasingType alloyedCasing = AlloyedCasingType.NONE;

    public BeltBlockEntityMixin(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Inject(
            method = "getModelData()Lnet/minecraftforge/client/model/data/ModelData;",
            at = @At("TAIL"),
            remap = false,
            cancellable = true
    )
    private void setModelDetails(CallbackInfoReturnable<ModelData> cir) {
        cir.setReturnValue(ModelData.builder()
                .with(BeltModel.CASING_PROPERTY, casing)
                .with(BeltModelExtension.ALLOYED_CASING_PROPERTY, alloyedCasing)
                .with(BeltModel.COVER_PROPERTY, covered)
                .build());
    }

    @Inject(
            method = "write(Lnet/minecraft/nbt/CompoundTag;Z)V",
            at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/utility/NBTHelper;writeEnum(Lnet/minecraft/nbt/CompoundTag;Ljava/lang/String;Ljava/lang/Enum;)V", ordinal = 0),
            remap = false
    )
    private void writeAlloyedCasingNBT(CompoundTag compound, boolean clientPacket, CallbackInfo ci) {
        NBTHelper.writeEnum(compound, "AlloyedCasing", alloyedCasing);
    }

    @Inject(
            method = "read(Lnet/minecraft/nbt/CompoundTag;Z)V",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/nbt/CompoundTag;getBoolean(Ljava/lang/String;)Z",
                    ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private void readAlloyedCasingNBT(CompoundTag compound, boolean clientPacket, CallbackInfo ci, int prevBeltLength, BeltBlockEntity.CasingType casingBefore, boolean coverBefore) {
        AlloyedCasingType previous = alloyedCasing;
        alloyedCasing = NBTHelper.readEnum(compound, "AlloyedCasing", AlloyedCasingType.class);

        if (!clientPacket) return;
        if (previous == alloyedCasing) return;
        if (casingBefore != casing || coverBefore != covered) return; // BE will be updated anyway

        if (!isVirtual())
            requestModelDataUpdate();
        if (hasLevel())
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 16);
    }

    @Inject(
            method = "setCasingType(Lcom/simibubi/create/content/kinetics/belt/BeltBlockEntity$CasingType;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/simibubi/create/content/kinetics/belt/BeltBlockEntity;casing:Lcom/simibubi/create/content/kinetics/belt/BeltBlockEntity$CasingType;",
                    opcode = Opcodes.PUTFIELD),
            remap = false
    )
    private void clearAlloyedCasing(BeltBlockEntity.CasingType type, CallbackInfo ci) {
        alloyedCasing = AlloyedCasingType.NONE;
    }


    @Override
    public void setAlloyedCasingType(AlloyedCasingType type) {
        if (alloyedCasing == type)
            return;

        BlockState blockState = getBlockState();
        boolean shouldBlockHaveCasing = type != AlloyedCasingType.NONE;

        if (level.isClientSide) {
            alloyedCasing = type;
            casing = BeltBlockEntity.CasingType.NONE;

            level.setBlock(worldPosition, blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing), 0);
            requestModelDataUpdate();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 16);
            return;
        }

        if (alloyedCasing != AlloyedCasingType.NONE)
            level.levelEvent(2001, worldPosition,
                    Block.getId(ModBlocks.STEEL_CASING.getDefaultState())); // TODO: if bronze casing is ever added, this must be updated
        if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
            KineticBlockEntity.switchToBlockState(level, worldPosition,
                    blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));

        alloyedCasing = type;
        casing = BeltBlockEntity.CasingType.NONE;
        setChanged();
        sendData();
    }

    @Override
    public void setAlloyedCasingTypeRaw(AlloyedCasingType value) {
        alloyedCasing = value;
    }

    @Override
    public AlloyedCasingType getAlloyedCasingType() {
        return alloyedCasing;
    }
}
