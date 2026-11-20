package helpers;

import java.util.Set;

import itumulator.world.Location;
import itumulator.world.World;



public class MoveTowardsTarget {
    
    public static void moveTowardsTarget(World world, Object mover, Object target){
        if(!world.isOnTile(mover) || !world.contains(target)){
            return;
        } 
            
            Location current = world.getLocation(mover);
            Location targetLocation = world.getLocation(target);
            if(current.equals(targetLocation)){
                return;
            } else if (!current.equals(target)) {
                Set<Location> freetiles = world.getEmptySurroundingTiles(current);
                int xDistance = Math.abs(current.getX() - targetLocation.getX());
                int yDistance = Math.abs(current.getY() - targetLocation.getY());
                int distance = Math.max(xDistance, yDistance);
                for(Location tile : freetiles){
                    int xDistanceToTarget = Math.abs(tile.getX() - targetLocation.getX());
                    int yDistanceToTarget = Math.abs(tile.getY() - targetLocation.getY());
                    int distanceToTarget = Math.max(xDistanceToTarget, yDistanceToTarget);
                    if(distanceToTarget < distance && world.isTileEmpty(tile)){
                        world.move(mover, tile);
                        break;
                    }
                }
    }

        }
    }

