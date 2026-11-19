import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import Entities.Grass;
import Entities.Hole;
import Entities.Rabbit;
import helpers.ScannerTest;
import itumulator.world.World;

public class JUnit {

    // Checks that the simulation contains the same objects as the input file
    @Test
    public void simulationMatchesFile() throws FileNotFoundException {
        ScannerTest input = new ScannerTest("data/tf1-1.txt");
        int rabbits = input.amountOf("rabbit");
        int holes = input.amountOf("burrow");
        int grass = input.amountOf("grass");

        World w = new World(input.size());
        Main.placeEntities(w, rabbits, holes, grass);

        assertEquals(rabbits, count(w, Rabbit.class));
        assertEquals(holes, count(w, Hole.class));
        assertEquals(grass, count(w, Grass.class));
    }

    // Counts how many objects of a type are in the world
    private int count(World w, Class<?> type) {
        int count = 0;
        for (Object o : w.getEntities().keySet()) {
            if (type.isInstance(o)) count++;
        }
        return count;
    }
}
