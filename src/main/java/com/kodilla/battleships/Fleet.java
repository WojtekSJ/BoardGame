package com.kodilla.battleships;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fleet implements Serializable {
    String name;
    List<Ships> fleetList = new ArrayList<>();
    public Fleet(String name) {
        this.name = name;
    }
    public Fleet() {
    }
    public void addToFleet(Ships ship) {
        fleetList.add(ship);
    }
    public boolean checkIfFleetDestroyed(){
        int shipsAlive=0;
        List<Ships> shipToRemove = new ArrayList<>();
        for(Ships temp: fleetList){
            if(temp.isDestroyed){
                shipToRemove.add(temp);
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
