package com.molybdenum.alloyed.util.forge;

import com.molybdenum.alloyed.forge.ConductorFakePlayerForge;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.items.ItemHandlerHelper;

public class EntityUtilsImpl {
	public static void givePlayerItem(Player player, ItemStack stack) {
		ItemHandlerHelper.giveItemToPlayer(player, stack);
	}
	public static double getReachDistance(Player player) {
		return player.getReachDistance();
	}

	public static boolean handleUseEvent(Player player, InteractionHand hand, BlockHitResult hit) {
		PlayerInteractEvent.RightClickBlock event = ForgeHooks.onRightClickBlock(player, InteractionHand.MAIN_HAND, hit.getBlockPos(), hit);
		return event.getResult() != Result.DENY;
	}
}
