package com.molybdenum.alloyed.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class SteelShearsModifier extends LootModifier {
    private final Item FINAL_LOOT;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     * @param final_loot the final loot that is dropped
     */
    public SteelShearsModifier(ILootCondition[] conditionsIn, Item final_loot) {
        super(conditionsIn);
        FINAL_LOOT = final_loot;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // Remove all unwanted loot
        generatedLoot.removeIf(ctx -> ctx.getItem() != FINAL_LOOT);
        // Add wanted loot
        if (generatedLoot.isEmpty()) generatedLoot.add(new ItemStack(FINAL_LOOT, 1));

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SteelShearsModifier> {

        @Override
        public SteelShearsModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
            Item final_loot = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "replacement")));
            return new SteelShearsModifier(conditions, final_loot);
        }

        @Override
        public JsonObject write(SteelShearsModifier instance) {
            JsonObject jsonObject = makeConditions(instance.conditions);
            jsonObject.addProperty("replacement", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.FINAL_LOOT)).toString());
            return jsonObject;
        }
    }
}