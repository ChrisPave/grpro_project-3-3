package Entities;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import helpers.MoveTowardsTarget;
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
        //Rabbits move differently according to time of day
        //When the day starts, they reappear from their holes, if hidden, or move, if not
        //If it is close to night, they seek towards their holes
        //Rabbits don't move at night
        if(world.getCurrentTime() == 0){
            if(world.isOnTile(this)){
                moveAccordingToAge(world);
            } else{
                //reappear(world);
            }
            
        } else if(world.getCurrentTime() > 0 && world.getCurrentTime() <= 5){
           moveAccordingToAge(world);
        } else if(world.getCurrentTime() > 5 && world.getCurrentTime() <= 9){
            flee(world);
        } else {
            return;
        }
        dig(world);
        breed(world);
       eatGrass(world);
        starve(world);
        
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
        if(new Random().nextInt(100) < 5) { // 5% chance to dig a hole
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
        if(myHole!=null){
            //If rabbits DO have a hole
             MoveTowardsTarget.moveTowardsTarget(world, this, myHole);
             Location current = world.getLocation(this);
             Location holeLocation = world.getLocation(myHole);
             if(current.equals(holeLocation)){
                 world.remove(this);
             }
            
    
        } else{
            //If rabbits DO NOT have a hole
            //They find the nearest hole, and claim it as theirs
           Location current = world.getLocation(this);
           Hole nearest = null;
           int best = Integer.MAX_VALUE;
           for(Location loc : world.getSurroundingTiles(current, 4)){
            Object o = world.getTile(loc);
            if(o instanceof Hole){
               int d = Math.max(Math.abs(loc.getX() - current.getX()),
                             Math.abs(loc.getY() - current.getY()));
            if (d < best) {
                best = d; 
                nearest = (Hole) o;
            }
            
           }
            
        }
        myHole = nearest;
        if(myHole == null){
            return;
        }
        MoveTowardsTarget.moveTowardsTarget(world, this, myHole);
          Location currentLocation = world.getLocation(this);
             Location currentHoleLocation = world.getLocation(myHole);
             if(currentLocation.equals(currentHoleLocation)){
                 world.remove(this);
             }
       
       
       }
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
    if (!world.isOnTile(this)) return;                
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
//If rabbits go 16 daylight hours wihtout eating grass, they die
void starve(World world){ //
    {
        if(hunger > 16){
            world.delete(this);
        }
    }
}
//Rabbits reappear on the map
//If another blocking object is on the tile, they can't get out, and sufficate:(
void reappear(World world){
    if(!world.isOnTile(this)){
        Location holeLocation = world.getLocation(myHole);
        if(world.isTileEmpty(holeLocation)==true){
            world.setTile(holeLocation, this);
        }
    } else {
        world.delete(this);
    }
}




public DisplayInformation getInformation(){
            return new DisplayInformation(Color.white, "rabbit-large");
        }
     }



