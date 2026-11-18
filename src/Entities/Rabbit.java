package Entities;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;


public class Rabbit implements Actor, DynamicDisplayInformationProvider {
     // Called by itumulator once every step
    int age = 0;
    Hole myHole;
    int hunger = 0;
   
    @Override
    public void act(World world) {
        age++;
        
        dig(world);
        breed(world);
        // flee(world);
       // flee(world);
       eatGrass(world);
        starve(world);
        moveAccordingToAge(world);
    }
    //Rabbits have a higher chance of moving, if they are young
    //Rabbits only move during day-time
    //Rabbits only get hungry during day time
    void moveAccordingToAge(World world){
        if(world.isDay()==true){
            hunger++;
            Object o = Rabbit.this;
        if(!world.contains(o)){
            return; // Rabbit has been removed from the world, so stop acting
        } else if(age <= 10){
            move(world);
        }
        else if(age > 10 && age <= 20){
            if(new Random().nextInt(100) < 90){
                move(world);
            }
        }
        else if(age > 20 && age <= 30){
            if(new Random().nextInt(100) < 70){
                move(world);
            }
        }
        else if(age > 30 && age <= 40){
            if(new Random().nextInt(100) < 50){
                move(world);
            }
        }
        else if(age > 40 && age <= 50){
            if(new Random().nextInt(100) < 30){
                move(world);
            }
        }
        else if (age > 50) {          
            world.delete(this);
            return;              
        }
   
    }
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

//Rabbits seek towards thir hole at nighttime
    void flee(World world) {
        if(world.isNight()==true){
        if(getHole() != null){
            world.getLocation(myHole);
            world.move(this, world.getLocation(myHole));
            world.remove(this);
        } else{
            //Find closest hole
        }
    }//If time is day reappear
    
       
       }
       
       
    //Makes a rabbit move to a random empty surrounding tile.
    void move(World world) {
       
    Set<Location> free = world.getEmptySurroundingTiles(world.getLocation(this));
    if (free.isEmpty()) return;                    // nowhere to go
    List<Location> options = new ArrayList<>(free);
    Location next = options.get(new Random().nextInt(options.size()));
    world.move(this, next);
}
//Rabbit breeds with another rabbit, and another rabbit gets placed
void breed(World world){
    if(world.isDay()==true && age > 15){
    if (!world.isOnTile(this)) return;                 // rabbit is hiding/removed
    Location current = world.getLocation(this);

    // find rabbits on the tiles around this rabbit
    Set<Rabbit> neighbours = world.getAll(Rabbit.class, world.getSurroundingTiles(current));
    if (neighbours.isEmpty()) return;                  // no partner nearby

    if (new Random().nextInt(100) < 5) {              // 5% chance to breed
        Set<Location> free = world.getEmptySurroundingTiles(current);
        if (free.isEmpty()) return;                    // no room for a baby
        List<Location> options = new ArrayList<>(free);
        Location next = options.get(new Random().nextInt(options.size()));
        world.setTile(next, new Rabbit());
    }
}
}
    

//Rabbits may eat grass, if they are standing on it. Hunger resets after eating
void eatGrass(World world){
   if(new Random().nextInt(100) < 40){
   Location current = world.getLocation(this);
       Object below = world.getNonBlocking(current);
if (below instanceof Grass) {
        world.delete(below);
        hunger = 0; // Reset hunger after eating
       }
    }    
}
//If rabbits go 16 ticks wihtout eating grass, they die
void starve(World world){ //
    {
        if(hunger > 16){
            world.delete(this);
        }
    }
}




public DisplayInformation getInformation(){
            return new DisplayInformation(Color.white, "rabbit-large");
        }
     }



