package com.molybdenum.alloyed.client.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.instruction.EmitParticlesInstruction;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

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
        Selection redstoneSegment = util.select.fromTo(2,1,3,2,1,4);
        BlockPos deployer = util.grid.at(2, 1, 0);
        Selection deployerSection = util.select.fromTo(3,1,0,5,1,0)
                .add(util.select.position(5,0,1));
        // ----------------

        scene.showBasePlate(); // Begin
        scene.idle(5);

        // Show the bronze bell
        scene.idle(5);
        scene.world.showSection(util.select.position(bell), Direction.DOWN);

        // Hitting them
        scene.idle(30);
        scene.overlay.showText(90)
                .attachKeyFrame()
                .text("Bronze Bells will play a sound when they are hit...")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(bell));
        scene.idle(30);

        scene.overlay.showControls(new InputWindowElement(util.vector.centerOf(bell), Pointing.UP).rightClick(), 30);
        scene.idle(10);
        scene.effects.emitParticles(util.vector.blockSurface(bell, Direction.UP),
                EmitParticlesInstruction.Emitter.simple(ParticleTypes.NOTE, Vec3.ZERO) , 1, 1);

        // Show the lever and redstone
        scene.idle(50);
        scene.world.showSection(redstoneSegment, Direction.DOWN);

        // Powering them
        scene.idle(30);
        scene.overlay.showText(90)
                .attachKeyFrame()
                .text("...or when they are powered by redstone.")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(bell));
        scene.idle(30);

        scene.world.toggleRedstonePower(redstoneSegment);
        scene.effects.indicateRedstone(util.grid.at(2, 1, 3));
        scene.idle(10);
        scene.effects.emitParticles(util.vector.blockSurface(bell, Direction.UP),
                EmitParticlesInstruction.Emitter.simple(ParticleTypes.NOTE, Vec3.ZERO), 1, 1);

        // Show how to tune it
        scene.idle(50);
        scene.overlay.showText(90)
                .attachKeyFrame()
                .text("Wrenches can be used to tune them.")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(bell));
        scene.idle(30);

        scene.overlay.showControls(new InputWindowElement(util.vector.centerOf(bell), Pointing.UP)
                .rightClick().withWrench(), 30);
        scene.idle(10);
        scene.effects.emitParticles(util.vector.blockSurface(bell, Direction.UP),
                EmitParticlesInstruction.Emitter.simple(ParticleTypes.NOTE, new Vec3(0.5, 0, 0)), 1, 1);

        // Show the deployers
        scene.idle(50);
        scene.rotateCameraY(-90);
        scene.world.showSection(util.select.position(deployer), Direction.DOWN);
        scene.idle(20);
        scene.world.showSection(deployerSection, Direction.DOWN);

        // Deployers
        scene.idle(30);
        scene.overlay.showText(90)
                .attachKeyFrame()
                .text("Deployers can be used to automatically ring them.")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(bell));
        scene.idle(30);

        scene.world.setKineticSpeed(util.select.position(5, 0, 1), 16);
        scene.world.setKineticSpeed(util.select.layer(1), -32);
        scene.idle(10);
        scene.world.moveDeployer(deployer, 1, 25);
        scene.idle(25);
        scene.effects.emitParticles(util.vector.blockSurface(bell, Direction.UP),
                EmitParticlesInstruction.Emitter.simple(ParticleTypes.NOTE, new Vec3(0.5, 0, 0)) , 1, 1);
        scene.world.moveDeployer(deployer, -1, 25);

        scene.idle(60); // End
    }
}