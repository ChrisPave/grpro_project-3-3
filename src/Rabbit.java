import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;

public class Rabbit implements Actor, DynamicDisplayInformationProvider {
    // Methods: birth, death, dig, seek towards hole
    int age = 0;
    Hole myHole;
    int hunger = 0;

    // Called by itumulator once every step
    @Override
    public void act(World world) {
        age++;
        //Dies of old age
        if (age > 50) {          
            world.delete(this);
            return;              
        }

        dig(world);
        // flee(world);
        move(world);
       // flee(world);
       eatGrass(world);
       starve(world);
    }

    void dig(World world) {
        // Rabbit digs a hole
        if(age > 10 && new Random().nextInt(100) < 5) { // 5% chance to dig a hole
            Location loc = world.getLocation(this);
            if(world.containsNonBlocking(loc)) {
               return; // Can't dig a hole if there's already a non-blocking object
            }
            myHole = new Hole(this);
            world.setTile(loc, myHole);
           
           
        }
    }
    public Hole getHole(){
        //Returns the hole of a Rabbit
        return myHole;
    }

//Rabbit attemos to flee to a hole, when it is night
    void flee(World world) {
        if(world.isNight()==true){
        if(getHole() != null){
            world.getLocation(myHole);
            world.move(this, world.getLocation(myHole));
            world.remove(this);
        } else{
            //Find closest hole
            //Program crashes because it tries to move a hidden object. Fix b4 tuesday
        }
    }
       
       }
       
        
    
    void move(World world) {
        //Makes a rabbit move to a random empty surrounding tile.
    Set<Location> free = world.getEmptySurroundingTiles(world.getLocation(this));
    if (free.isEmpty()) return;                    // nowhere to go
    List<Location> options = new ArrayList<>(free);
    Location next = options.get(new Random().nextInt(options.size()));
    world.move(this, next);
}
void eatGrass(World world){
    if(new Random().nextInt(100) < 40){
    Location current = world.getLocation(this);
        Object below = world.getNonBlocking(current);
if (below instanceof Grass) {
    world.delete(below);
        }
}   else {
    hunger++;
}
       
    }

void breed(){
    //If the world contains >2 rabbits, there is a chance that they breed a new rabbit
    //Might have 2 put in Main, because this determines what the individual rabbits do
    //And you need 2 or more rabbits to breed
}
//Kaniner dør
void starve(World world){
    if(hunger > 10){
        world.delete(this);
    }
}


public DisplayInformation getInformation(){
            return new DisplayInformation(Color.white, "rabbit-small");
        }
     }
