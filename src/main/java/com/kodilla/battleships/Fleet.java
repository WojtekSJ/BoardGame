package com.kodilla.battleships;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Fleet implements Serializable {
    /*private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(name);

    }*/


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
          //System.out.println("Statek o platach: "+ temp.name + " jest zniszczony: " + temp.isDestroyed);
            if(temp.isDestroyed){
                shipToRemove.add(temp);
                //Ship destroyed
            } else {
                shipsAlive++;
            }
        }
        //fleetList.removeAll(shipToRemove);

        if(shipsAlive==0){
            return true;
        }
        return false;
    }
}
