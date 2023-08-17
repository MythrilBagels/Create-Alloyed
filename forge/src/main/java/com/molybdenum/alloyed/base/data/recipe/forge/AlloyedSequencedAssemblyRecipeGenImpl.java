package com.molybdenum.alloyed.base.data.recipe.forge;

import com.molybdenum.alloyed.base.data.recipe.RailwaysSequencedAssemblyRecipeGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class AlloyedSequencedAssemblyRecipeGenImpl extends AlloyedSequencedAssemblyRecipeGen {
	protected AlloyedSequencedAssemblyRecipeGenImpl(DataGenerator pGenerator) {
		super(pGenerator);
	}

	public static RecipeProvider create(DataGenerator gen) {
		AlloyedSequencedAssemblyRecipeGenImpl provider = new AlloyedSequencedAssemblyRecipeGenImpl(gen);
		return new RecipeProvider(gen) {
			@Override
			protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
				provider.registerRecipes(consumer);
			}
		};
	}
}
