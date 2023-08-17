package com.molybdenum.alloyed.events;

import com.railwayteam.railways.Config;
import com.railwayteam.railways.content.schedule.RedstoneLinkInstruction;
import com.railwayteam.railways.multiloader.PlayerSelection;
import com.railwayteam.railways.registry.CRExtraRegistration;
import com.railwayteam.railways.registry.CRPackets;
import com.railwayteam.railways.util.packet.PacketSender;
import com.railwayteam.railways.util.packet.TrainMarkerDataUpdatePacket;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.entity.Train;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class CommonEvents {
    public static void onWorldTickStart(Level level) {
        if (level.isClientSide)
            return;
        RedstoneLinkInstruction.tick(level);
        long ticks = level.getGameTime();
        for (Train train : Create.RAILWAYS.trains.values()) {
            long offsetTicks = ticks + train.id.hashCode();
            if (offsetTicks % Config.FAR_TRAIN_SYNC_TICKS.get() == 0) {
                CRPackets.PACKETS.sendTo(PlayerSelection.all(), new TrainMarkerDataUpdatePacket(train));
            }
            if (offsetTicks % Config.NEAR_TRAIN_SYNC_TICKS.get() == 0) { //DONE train *might* not have any carriages if it just got coupled, fix that
                if (train.carriages.size() >= 1) {
                    Entity trainEntity = train.carriages.get(0).anyAvailableEntity();
                    if (trainEntity != null)
                        CRPackets.PACKETS.sendTo(PlayerSelection.tracking(trainEntity), new TrainMarkerDataUpdatePacket(train));
                }
            }
        }
    }

    public static void onPlayerJoin(ServerPlayer player) {
        PacketSender.notifyServerVersion(player);
    }

    // if, for some reason, CRExtraDisplays has not yet successfully registered
    // the signal source by the time we join a world, we have a last chance here
    public static void backupDisplayRegister() {
        CRExtraRegistration.register();
    }
}
