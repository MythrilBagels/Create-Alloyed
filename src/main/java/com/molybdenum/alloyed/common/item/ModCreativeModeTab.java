package com.molybdenum.alloyed.common.item;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModCompat;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Alloyed.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = REGISTER.register("main_tab",
            () -> CreativeModeTab.builder()
                    .title(Components.translatable("itemGroup.alloyed.main_tab"))
                    .icon(ModItems.STEEL_INGOT::asStack)
                    .displayItems(new DisplayItemsGenerator())
                    .build());

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

    public static void registerLang() {
        Alloyed.REGISTRATE.addRawLang("itemGroup.alloyed.main_tab", "Create: Alloyed");
    }

    // Credit to Create's AllCreativeModeTabs#RegistrateDisplayItemsGenerator for the code structure
    private static class DisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

        private static Predicate<Item> hideCompatItemPredicate() {
            Set<Item> hiddenItems = new ReferenceOpenHashSet<>();

            for (ModCompat mod : ModCompat.values()) {
                List<ItemProviderEntry<?>> entries = mod.getEntries();

                for (ItemProviderEntry<?> entry : entries) {
                    if (mod.shouldHide()) hiddenItems.add(entry.asItem());
                }
            }

            return hiddenItems::contains;
        }
        @Override
        public void accept (CreativeModeTab.ItemDisplayParameters pParameters, CreativeModeTab.Output pOutput) {
            Predicate<Item> shouldHide = hideCompatItemPredicate();

            List<Item> items = new LinkedList<>();
            items.addAll(getBlocksUnless(shouldHide));
            items.addAll(getItemsUnless(shouldHide));

            for (Item item : items) {
                pOutput.accept(new ItemStack(item));
            }
        }

        private List<Item> getBlocksUnless(Predicate<Item> shouldHidePredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Block> entry : Alloyed.REGISTRATE.getAll(Registries.BLOCK)) {
                Item item = entry.get()
                        .asItem();
                if (item == Items.AIR)
                    continue;
                if (!shouldHidePredicate.test(item))
                    items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }

        private List<Item> getItemsUnless(Predicate<Item> shouldHidePredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Item> entry : Alloyed.REGISTRATE.getAll(Registries.ITEM)) {
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                if (!shouldHidePredicate.test(item))
                    items.add(item);
            }
            return items;
        }
    }
}
