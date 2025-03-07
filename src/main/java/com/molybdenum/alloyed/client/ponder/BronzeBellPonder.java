package com.molybdenum.alloyed.client.ponder;

import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.ParticleEmitter;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.foundation.element.InputWindowElement;
import net.createmod.ponder.foundation.instruction.EmitParticlesInstruction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

public class BronzeBellPonder {

    public static void decoration(SceneBuilder scene, SceneBuildingUtil util) {
        CreateSceneBuilder createSceneBuilder = new CreateSceneBuilder(scene);
        scene.title("bronze_bell_decoration", "The Bronze Bell");
        scene.configureBasePlate(0, 0, 5);

        // Useful positions
        BlockPos groundBronzeBell = util.grid().at(2,1,2);
        BlockPos chainHungBell = util.grid().at(4, 2, 2);
        BlockPos fenceHungBell = util.grid().at(2, 2, 2);
        BlockPos barHungBell = util.grid().at(0, 2, 2);
        // ----------------

        scene.showBasePlate(); // Begin
        scene.idle(5);

        // Show the first bronze bell
        scene.idle(5);
        scene.world().showSection(util.select().position(groundBronzeBell), Direction.DOWN);

        // Talk about bronze bells in general
        scene.idle(30);
        scene.overlay().showText(80)
                .attachKeyFrame()
                .text("The Bronze Bell is a decorative block made from bronze.")
                .placeNearTarget()
                .pointAt(util.vector().centerOf(groundBronzeBell));
        scene.idle(90); // Wait for the text to go away

        // Show the rest of the bronze bells
        scene.world().hideSection(util.select().position(groundBronzeBell), Direction.UP);
        scene.idle(20);
        scene.world().showSection(util.select().layers(2, 5), Direction.DOWN);
        scene.idle(30);

        // Talk about ways to hang them
        scene.overlay().showText(70)
                .attachKeyFrame()
                .text("A good effect can be achieved by hanging them with chains...")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(chainHungBell, Direction.UP));
        scene.idle(80);

        scene.overlay().showText(40)
                .text("...fences...")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(fenceHungBell, Direction.UP));
        scene.idle(50);

        scene.overlay().showText(50)
                .text("...or iron bars.")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(barHungBell, Direction.UP));
        scene.idle(60); // End
    }

    public static void instrument(SceneBuilder scene, SceneBuildingUtil util) {
        CreateSceneBuilder createSceneBuilder = new CreateSceneBuilder(scene);
        scene.title("bronze_bell_instrument", "Making Music with the Bronze Bell");
        scene.configureBasePlate(0, 0, 5);

        // Useful positions
        BlockPos bell = util.grid().at(2,1,2);
        Selection redstoneSegment = util.select().fromTo(2,1,3,2,1,4);
        BlockPos deployer = util.grid().at(2, 1, 0);
        Selection deployerSection = util.select().fromTo(3,1,0,5,1,0)
                .add(util.select().position(5,0,1));
        // ----------------

        scene.showBasePlate(); // Begin
        scene.idle(5);

        // Show the bronze bell
        scene.idle(5);
        scene.world().showSection(util.select().position(bell), Direction.DOWN);

        // Hitting them
        scene.idle(30);
        scene.overlay().showText(90)
                .attachKeyFrame()
                .text("Bronze Bells will play a sound when they are hit...")
                .placeNearTarget()
                .pointAt(util.vector().centerOf(bell));
        scene.idle(30);

        scene.overlay().showControls(util.vector().centerOf(bell), Pointing.UP, 30).rightClick();
        scene.idle(10);
        scene.effects().emitParticles(util.vector().blockSurface(bell, Direction.UP),
                (world, x, y, z) -> world.addParticle(ParticleTypes.NOTE, x, y, z, Vec3.ZERO.x(), Vec3.ZERO.y(), Vec3.ZERO.z()), 1, 1);

        // Show the lever and redstone
        scene.idle(50);
        scene.world().showSection(redstoneSegment, Direction.DOWN);

        // Powering them
        scene.idle(30);
        scene.overlay().showText(90)
                .attachKeyFrame()
                .text("...or when they are powered by redstone.")
                .placeNearTarget()
                .pointAt(util.vector().centerOf(bell));
        scene.idle(30);

        scene.world().toggleRedstonePower(redstoneSegment);
        scene.effects().indicateRedstone(util.grid().at(2, 1, 3));
        scene.idle(10);
        scene.effects().emitParticles(util.vector().blockSurface(bell, Direction.UP),
                (world, x, y, z) -> world.addParticle(ParticleTypes.NOTE, x, y, z, Vec3.ZERO.x(), Vec3.ZERO.y(), Vec3.ZERO.z()), 1, 1);

        // Show how to tune it
        scene.idle(50);
        scene.overlay().showText(90)
                .attachKeyFrame()
                .text("Wrenches can be used to tune them.")
                .placeNearTarget()
                .pointAt(util.vector().centerOf(bell));
        scene.idle(30);

        scene.overlay().showControls(util.vector().centerOf(bell), Pointing.UP, 30).rightClick().withItem(AllItems.WRENCH.asStack());
        scene.idle(10);
        scene.effects().emitParticles(util.vector().blockSurface(bell, Direction.UP),
                (world, x, y, z) -> world.addParticle(ParticleTypes.NOTE, x, y, z, 0.5d, Vec3.ZERO.y(), Vec3.ZERO.z()), 1, 1);

        // Show the deployers
        scene.idle(50);
        scene.rotateCameraY(-90);
        scene.world().showSection(util.select().position(deployer), Direction.DOWN);
        scene.idle(20);
        scene.world().showSection(deployerSection, Direction.DOWN);

        // Deployers
        scene.idle(30);
        scene.overlay().showText(90)
                .attachKeyFrame()
                .text("Deployers can be used to automatically ring them.")
                .placeNearTarget()
                .pointAt(util.vector().centerOf(bell));
        scene.idle(30);


        createSceneBuilder.world().setKineticSpeed(util.select().position(5, 0, 1), 16);
        createSceneBuilder.world().setKineticSpeed(util.select().layer(1), -32);
        scene.idle(10);
        createSceneBuilder.world().moveDeployer(deployer, 1, 25);
        scene.idle(25);
        scene.effects().emitParticles(util.vector().blockSurface(bell, Direction.UP),
                (world, x, y, z) -> world.addParticle(ParticleTypes.NOTE, x, y, z, 0.5d, Vec3.ZERO.y(), Vec3.ZERO.z()), 1, 1);
        createSceneBuilder.world().moveDeployer(deployer, -1, 25);

        scene.idle(60); // End
    }
}