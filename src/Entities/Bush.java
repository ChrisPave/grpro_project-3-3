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

public class Bush implements Actor, DynamicDisplayInformationProvider {
    int age = 0;
    @Override
    public  void act(World world){
        age++;
        spread(world);
        if(age>10){
            growBerries(world);
        }
    }
    //Spreads a new bush to a surrounding empty tile
    void spread(World world){
        if(new Random().nextInt(100) >=8){
            return;
        } else {
             Spread.spread(world, this);
        }
       
    }
    //Deletes the bush, and places a new berry bush
    void growBerries(World world){
        Location current =world.getLocation(this);
        world.delete(this);
        world.setTile(current, new BerryBush());
    }
    public DisplayInformation getInformation(){
            return new DisplayInformation( Color.black,"bush");

        }
    
    
}
