import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;
import itumulator.simulator.Actor;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.awt.Color;




public class Grass implements Actor, NonBlocking, DynamicDisplayInformationProvider{
public void act(World world) {
    if (new Random().nextInt(100) >= 8) {
        return; // 8% chance that grass spreads
    } else{
         Location current = world.getLocation(this);
    for (Location tile : world.getEmptySurroundingTiles(current)) {
        if (!world.containsNonBlocking(tile)) {
            world.setTile(tile, new Grass());
            return; // only spread to one tile
        }
    }
}
    }
   
     public DisplayInformation getInformation(){
            return new DisplayInformation(Color.green, "grass");

        }

   }
    

