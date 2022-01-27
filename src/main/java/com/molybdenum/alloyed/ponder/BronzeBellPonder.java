package com.molybdenum.alloyed.ponder;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.elements.InputWindowElement;
import com.simibubi.create.foundation.ponder.instructions.EmitParticlesInstruction;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.client.particle.EmitterParticle;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class BronzeBellPonder {

    public static void decoration(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("bronze_bell_decoration", "The Bronze Bell");
        scene.configureBasePlate(0, 0, 5);

        // Useful positions
        BlockPos groundBronzeBell = util.grid.at(2,1,2);
        BlockPos chainHungBell = util.grid.at(4, 2, 2);
        BlockPos fenceHungBell = util.grid.at(2, 2, 2);
        BlockPos barHungBell = util.grid.at(0, 2, 2);
        // ----------------

        scene.showBasePlate(); // Begin
        scene.idle(5);

        // Show the first bronze bell
        scene.idle(5);
        scene.world.showSection(util.select.position(groundBronzeBell), Direction.DOWN);

        // Talk about bronze bells in general
        scene.idle(30);
        scene.overlay.showText(80)
                .attachKeyFrame()
                .text("The Bronze Bell is a decorative block made from bronze.")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(groundBronzeBell));
        scene.idle(90); // Wait for the text to go away

        // Show the rest of the bronze bells
        scene.world.hideSection(util.select.position(groundBronzeBell), Direction.UP);
        scene.idle(20);
        scene.world.showSection(util.select.layers(2, 5), Direction.DOWN);
        scene.idle(30);

        // Talk about ways to hang them
        scene.overlay.showText(70)
                .attachKeyFrame()
                .text("A good effect can be achieved by hanging them with chains...")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(chainHungBell, Direction.UP));
        scene.idle(80);

        scene.overlay.showText(40)
                .text("...fences...")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(fenceHungBell, Direction.UP));
        scene.idle(50);

        scene.overlay.showText(50)
                .text("...or iron bars.")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(barHungBell, Direction.UP));
        scene.idle(60); // End
    }

    public static void instrument(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("bronze_bell_instrument", "Making Music with the Bronze Bell");
        scene.configureBasePlate(0, 0, 5);

        // Useful positions
        BlockPos bell = util.grid.at(2,1,2);
        BlockPos noteblock = util.grid.at(2, 2, 2);
        // ----------------

        scene.showBasePlate(); // Begin
        scene.idle(5);

        // Show the bronze bell
        scene.idle(5);
        scene.world.showSection(util.select.position(bell), Direction.DOWN);
        // Show the noteblock
        scene.idle(5);
        scene.world.showSection(util.select.position(noteblock), Direction.DOWN);

        // Talk about bronze bells in general
        scene.idle(30);
        scene.overlay.showText(90)
                .attachKeyFrame()
                .text("Noteblocks placed above a Bronze Bell will play a special bell sound.")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(noteblock));
        scene.idle(100); // Wait for the text to go away

        scene.overlay.showControls(new InputWindowElement(util.vector.centerOf(noteblock), Pointing.UP).rightClick(), 30);
        scene.idle(10);
        scene.effects.emitParticles(util.vector.blockSurface(noteblock, Direction.UP), EmitParticlesInstruction.Emitter.simple(ParticleTypes.NOTE, Vector3d.ZERO) , 1, 1);

        scene.idle(60); // End
    }
}