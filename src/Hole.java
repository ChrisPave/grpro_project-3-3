import itumulator.executable.DisplayInformation;
import itumulator.simulator.Actor;
import itumulator.world.NonBlocking;
import itumulator.world.World;

public class Hole implements NonBlocking {
    Rabbit owner;
    // Hole is a non-blocking actor that can be placed in the world
        public Hole(Rabbit owner){

            this.owner = owner;
        }
    }

    
