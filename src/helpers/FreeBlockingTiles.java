package helpers;
import java.util.*;
import itumulator.world.Location;
import itumulator.world.World;
   public class FreeBlockingTiles {

    // Returns all tiles that are not occupied by Blocking Objects
    public static Set<Location> getFreeTiles(World world) {
        Set<Location> free = new HashSet<>();
        int size = world.getSize();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Location l = new Location(x, y);
                if (!world.isTileEmpty(l)) {
                    free.add(l);
                }
            }
        }
        return free;
    }

    // Chooses a random free tile
    public static Location randomBlockingTile(World world) {
        List<Location> free = new ArrayList<>(getFreeTiles(world));
        if (free.isEmpty()) return null;
        Random r = new Random();
        int index = r.nextInt(free.size());   // random index, 0 to size-1
        Location chosen = free.get(index);    // the tile at that index
        return chosen;
    }
   }
