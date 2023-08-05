package com.molybdenum.alloyed.data.recipes;

import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.molybdenum.alloyed.data.providers.ModProcessingRecipes;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ItemApplicationRecipes extends ModProcessingRecipes {

    GeneratedRecipe STEEL_CASING = casing("steel", ModTags.Items.STEEL_INGOT, ModBlocks.STEEL_CASING::get);

    protected GeneratedRecipe casing(String type, TagKey<Item> tag,
                                                   Supplier<ItemLike> output) {
        Supplier<Ingredient> ingredient = () -> Ingredient.of(tag);
        create(type + "_casing_from_log", b -> b.require(AllTags.AllItemTags.STRIPPED_LOGS.tag)
                .require(ingredient.get())
                .output(output.get()));
        return create(type + "_casing_from_wood", b -> b.require(AllTags.AllItemTags.STRIPPED_WOOD.tag)
                .require(ingredient.get())
                .output(output.get()));
    }

    public ItemApplicationRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.ITEM_APPLICATION;
    }

    @Override
    public @NotNull String getName() {
        return "Create: Alloyed's Item Application Recipes";
    }
}
