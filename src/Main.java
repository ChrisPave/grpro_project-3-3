import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

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
       Scanner scanner = new Scanner(System.in);
    int amountOfRabbits = scanner.nextInt();
    int amountOfHoles = scanner.nextInt();
      Random r = new Random();

      for(int i = 0; i < amountOfRabbits; i++){
        if(amountOfRabbits>size *size){
            throw new IllegalArgumentException("Too many rabbits for this size");
        }
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            Location l = new Location(x,y);
            while(!w.isTileEmpty(l)){
                x = r.nextInt(size);
                y=r.nextInt(size);
                l = new Location(x,y);
            }
            w.setTile(l, new Rabbit());
      } 
      for(int i = 0; i < amountOfHoles; i++){
        if(amountOfHoles>size *size){
            throw new IllegalArgumentException("Too many holes for this size");
        }
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            Location l = new Location(x,y);
            while(!w.isTileEmpty(l)){
                x = r.nextInt(size);
                y=r.nextInt(size);
                l = new Location(x,y);
            }
            w.setTile(l, new Hole(null));
      } 
       
         
        p.show();
        //Simulate for 200 steps
        for(int i = 0; i<200; i++){
            p.simulate();
            
        }
    }
}