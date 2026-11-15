import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import itumulator.executable.DisplayInformation;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;

public class Rabbit implements Actor {
    // Methods: birth, death, dig, seek towards hole
    int age = 0;

    // Called by itumulator once every step
    @Override
    public void act(World world) {
        age++;

        if (age > 50) {          // rabbit dies of old age
            world.delete(this);
            return;              // stop here, the rabbit is gone
        }

        dig(world);
        // flee(world);
        move(world);
    }

    void dig(World world) {
        // Rabbit digs a hole
        if(age > 10 && new Random().nextInt(100) < 5) { // 5% chance to dig a hole
            Location loc = world.getLocation(this);
            if(world.containsNonBlocking(loc)) {
               return; // Can't dig a hole if there's already a non-blocking object
            }
            world.setTile(loc, new Hole(this));
           
           
        }
    }

    void flee(World world) {
        // Rabbits seek towards hole
        // Ideally the closest hole, but can be towards own hole
    }
    void move(World world) {
        //Makes a rabbit move to a random empty surrounding tile.
    Set<Location> free = world.getEmptySurroundingTiles(world.getLocation(this));
    if (free.isEmpty()) return;                    // nowhere to go
    List<Location> options = new ArrayList<>(free);
    Location next = options.get(new Random().nextInt(options.size()));
    world.move(this, next);
}
void breed(){
    //If the world contains >2 rabbits, there is a chance that they breed a new rabbit
}
}