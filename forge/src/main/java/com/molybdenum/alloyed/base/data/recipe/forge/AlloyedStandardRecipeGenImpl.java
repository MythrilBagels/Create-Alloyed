package com.molybdenum.alloyed.base.data.recipe.forge;

import com.molybdenum.alloyed.base.data.recipe.RailwaysStandardRecipeGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class AlloyedStandardRecipeGenImpl extends AlloyedStandardRecipeGen {
	protected AlloyedStandardRecipeGenImpl(DataGenerator pGenerator) {
		super(pGenerator);
	}

	public static RecipeProvider create(DataGenerator gen) {
		AlloyedStandardRecipeGen provider = new AlloyedStandardRecipeGenImpl(gen);
		return new RecipeProvider(gen) {
			@Override
			protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
				provider.registerRecipes(consumer);
			}
		};
	}
}
