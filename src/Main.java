import java.awt.Color;
import java.util.Random;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {

    public static void main(String[] args) {
        //Parameters for the simulation
        int size = 5;
        int delay = 1000;
        int display_size = 800;
        Program p = new Program(size, delay, display_size);

        World w = p.getWorld();
        //Add a rabbit to the world
        w.setTile(new Location(0,0), new Rabbit());
       DisplayInformation di = new DisplayInformation(Color.red, "rabbit-small");
        DisplayInformation di2 = new DisplayInformation(Color.black, "hole");
       p.setDisplayInformation(Rabbit.class, di);
       p.setDisplayInformation(Hole.class, di2);
      
       
         
        p.show();
        //Simulate for 200 steps
        for(int i = 0; i<200; i++){
            p.simulate();
            
        }
    }
}