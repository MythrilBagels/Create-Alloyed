package com.molybdenum.alloyed;

import com.molybdenum.alloyed.blocks.ModBlocks;
import com.molybdenum.alloyed.client.ClientHandler;
import com.molybdenum.alloyed.items.ModItems;
import com.molybdenum.alloyed.sounds.ModSounds;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.NonNullLazyValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Alloyed.MOD_ID)
public class Alloyed {

    public static final String MOD_ID = "alloyed";

    // Registrate
    private static final NonNullLazyValue<CreateRegistrate> registrate = CreateRegistrate.lazy(MOD_ID);

    public Alloyed() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register();
        ModBlocks.register();
        ModSounds.register(eventBus);

        eventBus.addListener(this::setupClient);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }

    // Registrate getter
    @SuppressWarnings("deprecation")
    public static CreateRegistrate getRegistrate() {
        return registrate.get();
    }

}
