package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import itumulator.world.Location;
import itumulator.world.World;

public class Move {
   public static void move(World world, Object actor) {
       
    Set<Location> free = world.getEmptySurroundingTiles(world.getLocation(actor));
    if (free.isEmpty()) return;                    // nowhere to go
    List<Location> options = new ArrayList<>(free);
    Location next = options.get(new Random().nextInt(options.size()));
    world.move(actor, next);
}
}
