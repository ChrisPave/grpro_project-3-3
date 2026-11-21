package Entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.random.*;

import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;
//Methods: move(), eatAnimal(), eatBerries(), breed(), die(), killBear() 

public class Bear implements Actor, DynamicDisplayInformationProvider{
int hunger = 16;
int age = 0;
Location home;
    

    @Override
    public void act(World world) {
       
    }
     void getHome(World world) {
        
        if(age == 0) { 
            Location loc = world.getLocation(this);
            home = loc;
        } else {
            return;
        }
    }
    void move(World world){
        Set<Location> terretory =  world.getSurroundingTiles(home);
        List<Location> free = new ArrayList<>();
        for(Location loc : terretory){
            if(world.isTileEmpty(loc)){
                free.add(loc);
            }
            if(free.isEmpty()){
                return;
            } else {
                Location next = free.get(new Random().nextInt(free.size()));
                world.move(this, next);
            }
        }
       
    }








    @Override
    public DisplayInformation getInformation() {
        
        return new DisplayInformation(Color.white, "bear");
    }
    
}
