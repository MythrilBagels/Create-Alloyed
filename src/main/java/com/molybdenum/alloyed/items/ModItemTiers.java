package com.molybdenum.alloyed.items;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

public enum ModItemTiers implements Tier {
    STEEL(3, 1000, 7.0F, 3.0F, 11, () -> {
        return Ingredient.of(ModItems.STEEL_INGOT.get());
    });

    // Base code for item tier

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModItemTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return getAttackDamageBonus();
    }

    @Override
    public int getLevel() {
        return getLevel();
    }

    @Override
    public int getEnchantmentValue() {
        return getEnchantmentValue();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
