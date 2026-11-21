package helpers;


import itumulator.world.Location;
import itumulator.world.World;

public class Spread {
    public static void spread(World world, Object actor){
     Location current = world.getLocation(actor);
    for (Location tile : world.getEmptySurroundingTiles(current)) {
        if(!world.containsNonBlocking(tile)){
             world.setTile(tile, (actor));
        }    
            return; // only spread to one tile
        
    }
}
}
