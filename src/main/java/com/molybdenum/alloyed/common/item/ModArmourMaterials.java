package com.molybdenum.alloyed.common.item;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Either;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum ModArmourMaterials implements Holder<ArmorMaterial> {
    STEEL("steel", 30, new int[]{3, 5, 7, 3}, 10, SoundEvents.ARMOR_EQUIP_CHAIN.value(), 1F, 0.1F,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmourMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = Suppliers.memoize(pRepairIngredient::get);
    }

    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_PER_SLOT[pType.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type pType) {
        return this.slotProtections[pType.getSlot().getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return Alloyed.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Override
    public ArmorMaterial value() {
        return null;
    }

    @Override
    public boolean isBound() {
        return false;
    }

    @Override
    public boolean is(ResourceLocation arg) {
        return false;
    }

    @Override
    public boolean is(ResourceKey<ArmorMaterial> arg) {
        return false;
    }

    @Override
    public boolean is(Predicate<ResourceKey<ArmorMaterial>> predicate) {
        return false;
    }

    @Override
    public boolean is(TagKey<ArmorMaterial> arg) {
        return false;
    }

    @Override
    public boolean is(Holder<ArmorMaterial> arg) {
        return false;
    }

    @Override
    public Stream<TagKey<ArmorMaterial>> tags() {
        return Stream.empty();
    }

    @Override
    public Either<ResourceKey<ArmorMaterial>, ArmorMaterial> unwrap() {
        return null;
    }

    @Override
    public Optional<ResourceKey<ArmorMaterial>> unwrapKey() {
        return Optional.empty();
    }

    @Override
    public Kind kind() {
        return null;
    }

    @Override
    public boolean canSerializeIn(HolderOwner<ArmorMaterial> arg) {
        return false;
    }
}
