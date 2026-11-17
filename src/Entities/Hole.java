package Entities;
import java.awt.Color;

import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.NonBlocking;
import itumulator.world.World;

public class Hole implements NonBlocking, DynamicDisplayInformationProvider {
    Rabbit owner;
    // Hole is a non-blocking actor that can be placed in the world
        public Hole(Rabbit owner){

            this.owner = owner;
        }
        public DisplayInformation getInformation(){
            return new DisplayInformation(Color.black, "hole");

        }
    }

    
