package com.kodilla.battleships;

public interface CheckWinCondition {
    default boolean CheckIfFleetEradicated(Fleet fleet){
        int shipsAlive = 0;
        for(Ships temp: fleet.fleetList){
            if(!temp.getDestroyed()){
                shipsAlive++;
            }
        }
        if(shipsAlive > 0) {
            return false;
        }
        return true;
    }
}
