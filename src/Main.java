import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

import Entities.Grass;
import Entities.Hole;
import Entities.Rabbit;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {
    //Remember to test the code b4 handing in assignment!!!!

    public static void main(String[] args) {
        //Parameters for the simulation
        int size = 5;
        int delay = 1000;
        int display_size = 800;
        Program p = new Program(size, delay, display_size);

        World w = p.getWorld();
        //Add a rabbit to the world
       
       Scanner scanner = new Scanner(System.in);
       //Choose how many Rabbits and Holes the simulation starts with
    int amountOfRabbits = scanner.nextInt();
    int amountOfHoles = scanner.nextInt();
    int amountOfGrass = scanner.nextInt();
      Random r = new Random();
//Places the rabbits from the scanner in the simulation @Random
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
      //Places the holes from the scanner in the simulation @Random
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
            if(!w.containsNonBlocking(l)){
                w.setTile(l, new Hole(null));
            }
            //w.setTile(l, new Hole(null));
      } 
      for(int i = 0; i < amountOfHoles; i++){
        if(amountOfGrass>size *size){
            throw new IllegalArgumentException("Too much grass for this size");
        }
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            Location l = new Location(x,y);
            while(!w.isTileEmpty(l)){
                x = r.nextInt(size);
                y=r.nextInt(size);
                l = new Location(x,y);
            }
            if(!w.containsNonBlocking(l)){
                w.setTile(l, new Grass());
            }
           
      } 
    
       //Do the same thing for grass here
         
        p.show();
        //Simulate for 200 steps
        for(int i = 0; i<200; i++){
            p.simulate();
            
        }
    }
}
