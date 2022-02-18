package com.molybdenum.alloyed.data.registry;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.data.advancements.DisplayInfoBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class ModAdvancementProvider implements IDataProvider {
    private final DataGenerator generator;
    private static List<NamedAdvancementBuilder> advancements = new ArrayList<>();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    public ModAdvancementProvider(DataGenerator generator) {
        this.generator = generator;
    }

    public static void addAdvancement(NamedAdvancementBuilder advancementBuilder) {
        advancements.add(advancementBuilder);
    }

    public static List<NamedAdvancementBuilder> getAdvancements() {
        return advancements;
    }

    public static void register(DataGenerator generator) {
        generator.addProvider(new ModAdvancementProvider(generator));
    }

    /**
     * Performs this provider's action.
     *
     * @param pCache
     */
    @Override
    public void run(DirectoryCache pCache) throws IOException {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();

        for (NamedAdvancementBuilder advancement: advancements) {
            if (!set.add(advancement.id)) {
                throw new IllegalStateException("Duplicate advancement " + advancement.id);
            } else {
                Path path1 = createPath(path, advancement);

                try {
                    IDataProvider.save(GSON, pCache, advancement.builder.serializeToJson(), path1);
                } catch (IOException ioexception) {
                    Alloyed.LOGGER.error("Couldn't save advancement {}", path1, ioexception);
                }

            }
        }
    }

    private static Path createPath(Path pPath, NamedAdvancementBuilder pAdvancement) {
        return pPath.resolve("data/" + pAdvancement.id.getNamespace() + "/advancements/" + pAdvancement.id.getPath() + ".json");
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return "Create: Alloyed's Advancements";
    }

    public static class NamedAdvancementBuilder {
        public String name;
        public ResourceLocation id;
        public Advancement.Builder builder;

        public NamedAdvancementBuilder(ResourceLocation id, Advancement.Builder builder, String name) {
            this.id = id;
            this.name = name;
            this.builder = builder;
        }

        public NamedAdvancementBuilder(ResourceLocation id, Advancement.Builder builder) {
            this(id, builder, id.getPath());
        }

        public NamedAdvancementBuilder displayInfo(Function<DisplayInfoBuilder, DisplayInfoBuilder> info) {
            DisplayInfoBuilder displayInfoBuilder = DisplayInfoBuilder.create(name, this);
            builder = builder.display(info.apply(displayInfoBuilder).build());
            return this;
        }

        public NamedAdvancementBuilder save() {
            ModAdvancementProvider.addAdvancement(this);
            return this;
        }
    }
}
