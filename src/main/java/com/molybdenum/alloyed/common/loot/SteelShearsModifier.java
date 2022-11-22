package com.molybdenum.alloyed.common.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Fixes the `Silk Touch` effect of steel shears to work outside the dev environment.
 */
public class SteelShearsModifier extends LootModifier {
    public static final Supplier<Codec<SteelShearsModifier>> CODEC = Suppliers.memoize(() -> {
        return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst)
                    .and(ForgeRegistries.ITEMS.getCodec().fieldOf("replacement").forGetter((m) -> m.FINAL_LOOT))
                    .apply(inst, SteelShearsModifier::new);
        });
    });
    private final Item FINAL_LOOT;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the LootItemConditions that need to be matched before the loot is modified.
     * @param finalLoot the final loot that is dropped
     */
    public SteelShearsModifier(LootItemCondition[] conditionsIn, Item finalLoot) {
        super(conditionsIn);
        FINAL_LOOT = finalLoot;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // Remove all unwanted loot
        generatedLoot.removeIf(ctx -> ctx.getItem() != FINAL_LOOT);
        // Add wanted loot
        if (generatedLoot.isEmpty()) generatedLoot.add(new ItemStack(FINAL_LOOT, 1));
        return generatedLoot;
    }

    /**
     * Returns the registered codec for this modifier
     */
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
