package com.molybdenum.alloyed.common.content.items;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

/**
 * Little added content, but just a handy way to organise all the data and such.
 */
public class SteelUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component STEEL_UPGRADE =
            Component.translatable(Util.makeDescriptionId("upgrade", Alloyed.asResource("netherite_upgrade")))
                    .withStyle(ChatFormatting.GRAY);
    private static final Component APPLIES_TO =
            Component.translatable(Util.makeDescriptionId("item", Alloyed.asResource("smithing_template.steel_upgrade.applies_to")))
                    .withStyle(ChatFormatting.BLUE);
    private static final Component INGREDIENTS =
            Component.translatable(Util.makeDescriptionId("item", Alloyed.asResource("smithing_template.steel_upgrade.ingredients")))
                    .withStyle(ChatFormatting.BLUE);
    private static final Component BASE_SLOT_DESCRIPTION =
            Component.translatable(Util.makeDescriptionId("item", Alloyed.asResource("smithing_template.steel_upgrade.base_slot_description")));
    private static final Component ADDITIONS_SLOT_DESCRIPTION =
            Component.translatable(Util.makeDescriptionId("item", Alloyed.asResource("smithing_template.steel_upgrade.additions_slot_description")));

    public SteelUpgradeSmithingTemplateItem () {
        super(APPLIES_TO, INGREDIENTS, STEEL_UPGRADE, BASE_SLOT_DESCRIPTION, ADDITIONS_SLOT_DESCRIPTION, createNetheriteUpgradeIconList(), createNetheriteUpgradeMaterialList());
    }

    public static void registerLang() {
        Alloyed.REGISTRATE.addLang("item", Alloyed.asResource("smithing_template.steel_upgrade.applies_to"), "Iron Equipment");
        Alloyed.REGISTRATE.addLang("item", Alloyed.asResource("smithing_template.steel_upgrade.ingredients"), "Steel Ingot");
        Alloyed.REGISTRATE.addLang("item", Alloyed.asResource("smithing_template.steel_upgrade.base_slot_description"), "Add iron armor, weapon, or tool");
        Alloyed.REGISTRATE.addLang("item", Alloyed.asResource("smithing_template.steel_upgrade.additions_slot_description"), "Add Steel Ingot");
        Alloyed.REGISTRATE.addLang("upgrade", Alloyed.asResource("steel_upgrade"), "Steel Upgrade");
    }
}
