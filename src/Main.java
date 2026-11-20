import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import helpers.FreeNonBlockingTiles;
import helpers.FreeBlockingTiles;
import helpers.ScannerHelper;
import Entities.Grass;
import Entities.Hole;
import Entities.Rabbit;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {
    //Remember to test the code b4 handing in assignment!!!!

    public static void main(String[] args)throws FileNotFoundException {
   //Uses the ScannerHelper helper class, to read the files, and insert
   //The right amount of enteties, and set the world size
    ScannerHelper input = new ScannerHelper("data/tf1-1.txt");
    int size = input.size();
    int amountOfRabbits = input.amountOf("rabbit");
    int amountOfHoles   = input.amountOf("burrow");
    int amountOfGrass   = input.amountOf("grass");
        //Parameters for the simulation
        
        
        int delay = 1000;
        int display_size = 800;
        Program p = new Program(size, delay, display_size);

        World w = p.getWorld();
       
    
      
//Places the rabbits from the scanner in the simulation @Random
      for(int i = 0; i < amountOfRabbits; i++){
        if(amountOfRabbits>size *size){
            throw new IllegalArgumentException("Too many rabbits for this size");
        }
            Location l = FreeBlockingTiles.randomBlockingTile(w);
            if(l!=null){
                w.setTile(l, new Rabbit());
            }
            }
            
      //Places the holes from the scanner in the simulation @Random
      for(int i = 0; i < amountOfHoles; i++){
        if(amountOfHoles>size *size){
            throw new IllegalArgumentException("Too many holes for this size");
        }
             Location l = FreeNonBlockingTiles.randomNonBlockingTile(w);
            if(l!=null){
                w.setTile(l, new Hole(null));
            }
            
      } 
      //Places amount of 
      for(int i = 0; i < amountOfGrass; i++){
        if(amountOfGrass>size *size){
            throw new IllegalArgumentException("Too much grass for this size");
        }
            Location l = FreeNonBlockingTiles.randomNonBlockingTile(w);
            if(l!=null){
                w.setTile(l, new Grass());
            }
           
      } 
    
      
         
        p.show();
        //Simulate for 200 steps
        for(int i = 0; i<200; i++){
            p.simulate();
            
        }
    }
}
