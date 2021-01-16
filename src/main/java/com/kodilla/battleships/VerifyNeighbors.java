package com.kodilla.battleships;

import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Set;

public class VerifyNeighbors extends Exception{
    public boolean checkIfAnyNeighbors(int location, GridPane gridBoard, Set<Integer> alreadyOccupiedCells){
        //If location 00, 10, 20, ... 90 then
        if (location == 0 || location%10==0) {
            if(alreadyOccupiedCells.contains((Integer)(location-10))
                    || alreadyOccupiedCells.contains((Integer)(location-9))
                    || alreadyOccupiedCells.contains((Integer)(location+1))
                    || alreadyOccupiedCells.contains((Integer)(location+10))
                    || alreadyOccupiedCells.contains((Integer)(location+11))
            ) {
                System.out.println("Pierwsza petla.");
                System.out.println(location);
                return true;
            }
        } else if (location == 9 || location == 19 ||location == 29 ||location == 39 ||location == 49 ||location == 59
                ||location == 69 ||location == 79 ||location == 89 ||location == 99)
        {
            if(alreadyOccupiedCells.contains((Integer)(location-10))
                    || alreadyOccupiedCells.contains((Integer)(location-11))
                    || alreadyOccupiedCells.contains((Integer)(location-1))
                    || alreadyOccupiedCells.contains((Integer)(location+9))
                    || alreadyOccupiedCells.contains((Integer)(location+10))
            ) {
                System.out.println("Druga petla.");
                System.out.println(location);
                return true;
            }
        } else {
            if(alreadyOccupiedCells.contains((Integer)(location-10))
                    || alreadyOccupiedCells.contains((Integer)(location-11))
                    || alreadyOccupiedCells.contains((Integer)(location-10))
                    || alreadyOccupiedCells.contains((Integer)(location-9))
                    || alreadyOccupiedCells.contains((Integer)(location-1))
                    || alreadyOccupiedCells.contains((Integer)(location+1))
                    || alreadyOccupiedCells.contains((Integer)(location+9))
                    || alreadyOccupiedCells.contains((Integer)(location+10))
                    || alreadyOccupiedCells.contains((Integer)(location+11))
            ) {
                System.out.println("Trzecia petla.");
                System.out.println(location);
                return true;
            }
        }
        return false;
    }

    public void createAllowedCellList(Set<Integer> tempShipLocation, Set<Integer> listOfAllowedLocation){
       //Create list of all adjacent but not diagonal fields
        for(Integer temp: tempShipLocation){
            if (temp == 0 || temp%10==0) {
                if((temp-10)<0){} //out of matrix
                else {
                    listOfAllowedLocation.add((Integer) (temp - 10));
                }
                    listOfAllowedLocation.add((Integer) (temp + 1));
                if((temp+10)>99){} //out of matrix
                else {
                    listOfAllowedLocation.add((Integer) (temp + 10));
                }
            } else if (temp == 9 || temp == 19 ||temp == 29 ||temp == 39 ||temp == 49 ||temp == 59
                    ||temp == 69 ||temp == 79 ||temp == 89 ||temp == 99)
            {
                if((temp-10)<0){} //out of matrix
                else {
                    listOfAllowedLocation.add((Integer) (temp - 10));
                }
                    listOfAllowedLocation.add((Integer) (temp - 1));
                if((temp+10)>99){} //out of matrix
                else {
                    listOfAllowedLocation.add((Integer) (temp + 10));
                }
            } else {
                if((temp-10)<0){} //out of matrix
                else {
                    listOfAllowedLocation.add((Integer) (temp - 10));
                }
                    listOfAllowedLocation.add((Integer) (temp - 1));
                    listOfAllowedLocation.add((Integer) (temp + 1));
                if((temp+10)>99){} //out of matrix
                else {
                    listOfAllowedLocation.add((Integer) (temp + 10));
                }
            }
            for(Integer tempToClear: tempShipLocation){
                listOfAllowedLocation.remove(tempToClear);
            }
        }
    }

    public boolean checkIfAllowed(int location, Set<Integer> listOfAllowedLocation){
        if(listOfAllowedLocation.contains(location)){
            return true;
        }
        return false;
    }

public int getColumnLocation(Integer cellNumber) throws Exception{
        if(cellNumber!=null) {
            return (cellNumber / 10);
        } else {
            throw new Exception();
        }
}
public int getRowLocation(Integer cellNumber) throws Exception{
    if(cellNumber!=null) {
        return (cellNumber%10);
    } else {
        throw new Exception();
    }
    }

}
