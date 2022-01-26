package com.molybdenum.alloyed.ponder;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

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
        scene.overlay.showText(60)
                .attachKeyFrame()
                .text("The Bronze Bell is a decorative block made from bronze.")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(groundBronzeBell));
        scene.idle(80); // Wait for the text to go away

        // Show the rest of the bronze bells
        scene.world.hideSection(util.select.position(groundBronzeBell), Direction.UP);
        scene.idle(20);
        scene.world.showSection(util.select.layers(2, 5), Direction.DOWN);
        scene.idle(30);

        // Talk about ways to hang them
        scene.overlay.showText(60)
                .attachKeyFrame()
                .text("A good effect can be achieved by hanging them with chains...")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(chainHungBell, Direction.UP));
        scene.idle(70);

        scene.overlay.showText(30)
                .text("...fences...")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(fenceHungBell, Direction.UP));
        scene.idle(40);

        scene.overlay.showText(40)
                .text("...or iron bars.")
                .placeNearTarget()
                .pointAt(util.vector.blockSurface(barHungBell, Direction.UP));
        scene.idle(60); // End
    }

    public static void instrument(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("bronze_bell_instrument", "Making Music with the Bronze Bell");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate(); // Begin
        scene.idle(5);

        // Show the bronze bell
        scene.idle(5);
        scene.world.showSection(util.select.position(2, 1, 2), Direction.DOWN);
        // Show the noteblock
        scene.idle(5);
        scene.world.showSection(util.select.position(2, 2, 2), Direction.DOWN);

        scene.idle(60); // End
    }
}