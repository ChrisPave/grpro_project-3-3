package Entities;

import java.util.*;

public class Pack {
    private List<Wolf> wolves;
    // Constructor to initialize the pack with an empty list of wolves
    public Pack() {
        this.wolves = new ArrayList<>();
    }
    // Add a wolf to the pack
    public void addWolf(Wolf wolf) {
        wolves.add(wolf);
    }
    // Remove a wolf from the pack
    public void removeWolf(Wolf wolf) {
        wolves.remove(wolf);
    }
    // Get the list of wolves in the pack
    public List<Wolf> getWolves() {
        return wolves;
    }
}
