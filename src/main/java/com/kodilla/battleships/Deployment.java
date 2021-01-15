package com.kodilla.battleships;

import java.util.ArrayList;
import java.util.List;

public class Deployment {
    String name;
    int shipSize;
    int occupiedCells;
    boolean shipOnBoard = false;
    List<Integer> cellsLocation = new ArrayList<>();

    public boolean checkIfShipOnBoard(){
        if(occupiedCells==shipSize){
            shipOnBoard = true;
            return true;
        }
        return false;
    }

    public void createListOfShipTODeploy(List<Integer> listOfShipToDeploy) {
        listOfShipToDeploy.add(5);
        listOfShipToDeploy.add(4);
        listOfShipToDeploy.add(3);
        listOfShipToDeploy.add(2);
        listOfShipToDeploy.add(1);

    }

}
