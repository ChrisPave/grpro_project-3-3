import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import Entities.Bear;
import Entities.BerryBush;

public class Main {
    //Remember to test the code b4 handing in assignment!!!!

    public static void main(String[] args)throws FileNotFoundException {
   //Uses the ScannerHelper helper class, to read the files, and insert
   //The right amount of enteties, and set the world size
    ScannerHelper input = new ScannerHelper("data/tf2-2.txt");
    int size = input.size();
    int amountOfRabbits = input.amountOf("rabbit");
    int amountOfHoles   = input.amountOf("burrow");
    int amountOfGrass   = input.amountOf("grass");
    int amountOfBears = input.amountOf("bear");
    int amountOfBerryBushes = input.amountOf("berry");
    ArrayList<Location> bearLocation = input.locationOf("bear");
        //Parameters for the simulation
        
        
        int delay = 1000;
        int display_size = 800;
        Program p = new Program(size, delay, display_size);

        World w = p.getWorld();
       
    
      
//Places the rabbits from the scanner in the simulation @Random
for(int i = 0; i < amountOfBerryBushes; i++){
    if(amountOfBerryBushes > size*size){
        throw new IllegalArgumentException("Too many bushes for this size");
    }
    Location l = FreeBlockingTiles.randomBlockingTile(w);
            if(l!=null){
                w.setTile(l, new BerryBush());
            }

}
      for(int i = 0; i < amountOfRabbits; i++){
        if(amountOfRabbits>size *size){
            throw new IllegalArgumentException("Too many rabbits for this size");
        }
            Location l = FreeBlockingTiles.randomBlockingTile(w);
            if(l!=null){
                w.setTile(l, new Rabbit());
            }
            }
            //If a Bear isn't given a location from the file, it is placed randomly
            //If a Bear IS given a location from the file, place it on said location
            if(amountOfBears>0){
                for(int i = 0; i < amountOfBears; i++){
                if(amountOfBears>size*size){
            throw new IllegalArgumentException("Too many bears for this size");
                }
                Location l = FreeBlockingTiles.randomBlockingTile(w);
                w.setTile(l, new Bear());

                
            }
            } else if(bearLocation != null){
                for(Location l : bearLocation)
                w.setTile(l, new Bear());
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
