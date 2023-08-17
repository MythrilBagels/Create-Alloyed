package com.molybdenum.alloyed.base.data.recipe.fabric;

import com.molybdenum.alloyed.base.data.recipe.RailwaysSequencedAssemblyRecipeGen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class AlloyedSequencedAssemblyRecipeGenImpl extends RailwaysSequencedAssemblyRecipeGen {
	protected AlloyedSequencedAssemblyRecipeGenImpl(DataGenerator pGenerator) {
		super(pGenerator);
	}

	public static RecipeProvider create(DataGenerator gen) {
		AlloyedSequencedAssemblyRecipeGenImpl provider = new AlloyedSequencedAssemblyRecipeGenImpl(gen);
		return new FabricRecipeProvider((FabricDataGenerator) gen) {
			@Override
			protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
				provider.registerRecipes(exporter);
			}
		};
	}
}
