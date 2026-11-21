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

public class Wolf implements Actor, DynamicDisplayInformationProvider{
    int energy = 100;
    int age = 0;
    Pack pack;

    public Wolf(Pack pack) {
        this.pack = pack;
        pack.addWolf(this);
    }

    @Override
    public void act(World world) {
        eatAnimals(world, world.getLocation(this));
        energyDecay();
        wolfAge();
        wolfDies(world, world.getLocation(this));

    }
    
 //Wolf eats rabbit(s)
    
 
    public void eatAnimals(World world, Location location) {
        Set<Location> adjacentLocations = world.getSurroundingTiles(location);
        for (Location adjacentLocation : adjacentLocations) {
            Object entity = world.getTile(adjacentLocation);
            if (entity instanceof Rabbit) {
                world.delete(entity); // Remove the rabbit from the world
                energy += 20; // Increase energy after eating a rabbit
                break; // Eat only one rabbit per act
            }
        }
        }
        // Wolf loses energy every act / day
        public void energyDecay() {
            energy -= 5;

        }
        // Wolf increases age by 1 each act / day
        public void wolfAge() {
            age += 1; 
        }
        // Remove the wolf from the world if it has no energy or is too old
        public void wolfDies(World world, Location location){
            
            if (energy <= 0 || age >= 50) {
                world.delete(this); 
                pack.removeWolf(this);
            }
        }

       
            

        




















     @Override
    public DisplayInformation getInformation() {
        return new DisplayInformation(Color.gray, "wolf");
    }
}
