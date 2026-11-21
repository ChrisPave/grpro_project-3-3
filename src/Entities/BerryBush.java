package Entities;

import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;
import helpers.Spread;
import java.awt.Color;
import java.util.Random;

import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;

//Methods: spread(), dropBerries()

public class BerryBush implements Actor, DynamicDisplayInformationProvider {
    int age = 0;
    @Override 
    public  void act(World world){
        age++;
        spread(world);
        if(age>= 15){
            dropBerries(world);
        }
    }
    void spread(World world){
        if(new Random().nextInt(100)>=8){
            Spread.spread(world, this);
        }
    }
    void dropBerries(World world){
        Location current = world.getLocation(this);
        world.delete(this);
        world.setTile(current, new Bush());
    }
    
    public DisplayInformation getInformation(){
            return new DisplayInformation(Color.green, "bush-berries");

        }
}
