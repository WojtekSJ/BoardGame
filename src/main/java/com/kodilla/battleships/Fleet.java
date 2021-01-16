package com.kodilla.battleships;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    String name;
    List<Ships> fleetList = new ArrayList<>();

    public Fleet(String name) {
        this.name = name;
    }

    public void addToFleet(Ships ship) {
        fleetList.add(ship);
    }

    public boolean checkIfFleetDestroyed(){
        int shipsAlive=0;
        for(Ships temp: fleetList){
            if(temp.isDestroyed){
                //Ship destroyed
            } else {
                shipsAlive++;
            }
        }
        if(shipsAlive==0){
            return true;
        }
        return false;
    }
}
