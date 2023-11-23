package com.molybdenum.alloyed.data.providers;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.data.advancements.DisplayInfoBuilder;
import com.simibubi.create.foundation.advancement.CreateAdvancement;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class ModAdvancementProvider implements DataProvider {
    private final PackOutput output;
    private static final List<Advancement> advancements = new ArrayList<>();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    public ModAdvancementProvider(PackOutput output) {
        this.output = output;
    }

    public static void addAdvancement(Advancement advancementBuilder) {
        advancements.add(advancementBuilder);
    }

    public static List<Advancement> getAdvancements() {
        return advancements;
    }

    public static void register(DataGenerator generator) {
        generator.addProvider(true, new ModAdvancementProvider(generator.getPackOutput()));
    }

    @Override
    public CompletableFuture<?> run (CachedOutput pOutput) {
        PackOutput.PathProvider pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "advancements");
        List<CompletableFuture<?>> futures = new ArrayList<>();

        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (advancement) -> {
            ResourceLocation id = advancement.getId();
            if (!set.add(id))
                throw new IllegalStateException("Duplicate advancement " + id);
            Path path = pathProvider.json(id);
            futures.add(DataProvider.saveStable(pOutput, advancement.deconstruct()
                    .serializeToJson(), path));
        };

        for (Advancement advancement : advancements)
            consumer.accept(advancement);

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public @NotNull String getName() {
        return "Create: Alloyed's Advancements";
    }
}
