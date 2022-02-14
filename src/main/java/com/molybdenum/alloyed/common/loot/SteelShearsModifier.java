package com.molybdenum.alloyed.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Fixes the `Silk Touch` effect of steel shears to work outside the dev environment.
 */
public class SteelShearsModifier extends LootModifier {
    private final Item FINAL_LOOT;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     * @param final_loot the final loot that is dropped
     */
    public SteelShearsModifier(LootItemCondition[] conditionsIn, Item final_loot) {
        super(conditionsIn);
        FINAL_LOOT = final_loot;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // Remove all unwanted loot
        generatedLoot.removeIf(ctx -> ctx.getItem() != FINAL_LOOT);
        // Add wanted loot
        generatedLoot.add(new ItemStack(FINAL_LOOT, 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SteelShearsModifier> {

        @Override
        public SteelShearsModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
            Item final_loot = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "replacement")));
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
