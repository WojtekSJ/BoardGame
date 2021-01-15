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

    public boolean checkFleetStatus(){
        return false;
    }
}
