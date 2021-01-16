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
          //System.out.println("Statek o platach: "+ temp.name + " jest zniszczony: " + temp.isDestroyed);
            if(temp.isDestroyed){
                //Ship destroyed
            } else {
                shipsAlive++;
            }
        }
        //System.out.println("Ships alive number: " + shipsAlive);
        if(shipsAlive==0){
            return true;
        }
        return false;
    }
}
