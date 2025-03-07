package com.molybdenum.alloyed.client.ponder;

import com.molybdenum.alloyed.client.registry.ModPonders;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderScenes;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class AlloyedPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return "alloyed";
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        ModPonders.register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        helper.addToTag(AllCreatePonderTags.DECORATION, ModBlocks.BRONZE_BELL.getId());
    }
}
