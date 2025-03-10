package com.molybdenum.alloyed.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.molybdenum.alloyed.common.content.extensions.BeltModelExtension;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltModel;
import net.createmod.catnip.nbt.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeltBlockEntity.class)
public class BeltBlockEntityMixin extends KineticBlockEntity implements BeltBlockEntityExtension {
    @Shadow(remap=false) public BeltBlockEntity.CasingType casing;
    @Shadow(remap=false) public boolean covered;
    @Unique
    AlloyedCasingType create_alloyed$alloyedCasing = AlloyedCasingType.NONE;

    public BeltBlockEntityMixin(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Inject(
            method = "getModelData",
            at = @At("TAIL"),
            remap = false,
            cancellable = true
    )
    private void setModelDetails(CallbackInfoReturnable<ModelData> cir) {
        cir.setReturnValue(ModelData.builder()
                .with(BeltModel.CASING_PROPERTY, casing)
                .with(BeltModelExtension.ALLOYED_CASING_PROPERTY, create_alloyed$alloyedCasing)
                .with(BeltModel.COVER_PROPERTY, covered)
                .build());
    }


    @Inject(method = "write", at = @At(value = "RETURN"), remap = false)
    private void writeAlloyedCasingNBT(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket, CallbackInfo ci) {
        NBTHelper.writeEnum(compound, "AlloyedCasing", create_alloyed$alloyedCasing);
    }

    @Inject(method = "read", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/nbt/CompoundTag;getBoolean(Ljava/lang/String;)Z", ordinal = 1))
    private void readAlloyedCasingNBT(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket, CallbackInfo ci, @Local BeltBlockEntity.CasingType casingBefore, @Local(ordinal = 1) boolean coverBefore) {
        AlloyedCasingType previous = create_alloyed$alloyedCasing;
        create_alloyed$alloyedCasing = NBTHelper.readEnum(compound, "AlloyedCasing", AlloyedCasingType.class);

        if (!clientPacket) return;
        if (previous == create_alloyed$alloyedCasing) return;
        if (casingBefore != casing || coverBefore != covered) return; // BE will be updated anyway

        if (!isVirtual())
            requestModelDataUpdate();
        if (hasLevel()) {
            assert level != null;
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 16);
        }
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
        create_alloyed$alloyedCasing = AlloyedCasingType.NONE;
    }


    @Override
    public void create_alloyed$setAlloyedCasingType(AlloyedCasingType type) {
        if (create_alloyed$alloyedCasing == type)
            return;

        BlockState blockState = getBlockState();
        boolean shouldBlockHaveCasing = type != AlloyedCasingType.NONE;

        if (getLevel().isClientSide) {
            create_alloyed$alloyedCasing = type;
            casing = BeltBlockEntity.CasingType.NONE;

            level.setBlock(worldPosition, blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing), 0);
            requestModelDataUpdate();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 16);
            return;
        }

        if (create_alloyed$alloyedCasing != AlloyedCasingType.NONE)
            level.levelEvent(2001, worldPosition,
                    Block.getId(ModBlocks.STEEL_CASING.getDefaultState())); // TODO: if bronze casing is ever added, this must be updated
        if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
            KineticBlockEntity.switchToBlockState(level, worldPosition,
                    blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));

        create_alloyed$alloyedCasing = type;
        casing = BeltBlockEntity.CasingType.NONE;
        setChanged();
        sendData();
    }

    @Override
    public void create_alloyed$setAlloyedCasingTypeRaw(AlloyedCasingType value) {
        create_alloyed$alloyedCasing = value;
    }

    @Override
    public AlloyedCasingType getAlloyedCasingType() {
        return create_alloyed$alloyedCasing;
    }
}
