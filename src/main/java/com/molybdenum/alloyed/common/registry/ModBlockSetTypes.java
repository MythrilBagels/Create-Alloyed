package com.molybdenum.alloyed.common.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType STEEL = BlockSetType.register(
            new BlockSetType("steel",
                    false,
                    SoundType.METAL,
                    SoundEvents.IRON_DOOR_CLOSE,
                    SoundEvents.IRON_DOOR_OPEN,
                    SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON));
    public static final BlockSetType FLIMSY_STEEL = BlockSetType.register(
            new BlockSetType("flimsy_steel",
                    true,
                    SoundType.METAL,
                    SoundEvents.IRON_DOOR_CLOSE,
                    SoundEvents.IRON_DOOR_OPEN,
                    SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON));
//    public static final BlockSetType BRONZE = BlockSetType.register(
//            new BlockSetType("gold",
//                    false,
//                    SoundType.METAL,
//                    SoundEvents.IRON_DOOR_CLOSE,
//                    SoundEvents.IRON_DOOR_OPEN,
//                    SoundEvents.IRON_TRAPDOOR_CLOSE,
//                    SoundEvents.IRON_TRAPDOOR_OPEN,
//                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
//                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
//                    SoundEvents.STONE_BUTTON_CLICK_OFF,
//                    SoundEvents.STONE_BUTTON_CLICK_ON));

    public static void register() {}
}
