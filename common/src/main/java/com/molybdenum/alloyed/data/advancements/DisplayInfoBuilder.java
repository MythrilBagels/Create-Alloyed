package com.molybdenum.alloyed.data.advancements;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.data.providers.ModAdvancementProvider;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DisplayInfoBuilder {
    private final ModAdvancementProvider.NamedAdvancementBuilder parent;
    private final Component title;
    private final Component description;
    private ItemStack icon = ItemStack.EMPTY;
    private ResourceLocation background = null;
    private FrameType frame = FrameType.TASK;
    private boolean showToast = true;
    private boolean announceChat = true;
    private boolean hidden = false;

    private DisplayInfoBuilder(ModAdvancementProvider.NamedAdvancementBuilder parent,
                               Component title,
                               Component description) {
        this.title = title;
        this.description = description;
        this.parent = parent;
    }

    public static DisplayInfoBuilder create(String name, ModAdvancementProvider.NamedAdvancementBuilder parent) {
        Component title =
                Component.translatable("advancements." + Alloyed.MOD_ID + "." + name + ".title");
        Component description =
                Component.translatable("advancements." + Alloyed.MOD_ID + "." + name + ".description");

        return new DisplayInfoBuilder(
                parent,
                title,
                description
        );
    }

    public DisplayInfoBuilder title(String title) {
        Alloyed.registrate().addDataGenerator(ProviderType.ADVANCEMENT,
                prov -> prov.title(Alloyed.MOD_ID, parent.name, title));
        return this;
    }

    public DisplayInfoBuilder description(String desc) {
        Alloyed.registrate().addDataGenerator(ProviderType.ADVANCEMENT,
                prov -> prov.desc(Alloyed.MOD_ID, parent.name, desc));
        return this;
    }

    public DisplayInfoBuilder icon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public DisplayInfoBuilder icon(ItemLike icon) {
        this.icon = new ItemStack(icon);
        return this;
    }

    public DisplayInfoBuilder visibility(boolean showToast, boolean announceChat, boolean hidden) {
        this.showToast = showToast;
        this.announceChat = announceChat;
        this.hidden = hidden;
        return this;
    }

    public DisplayInfoBuilder frame(FrameType frame) {
        this.frame = frame;
        return this;
    }

    public DisplayInfoBuilder background(ResourceLocation bg) {
        this.background = bg;
        return this;
    }

    public DisplayInfo build() {
        return new DisplayInfo(
                icon,
                title,
                description,
                background,
                frame,
                showToast,
                announceChat,
                hidden);
    }
}