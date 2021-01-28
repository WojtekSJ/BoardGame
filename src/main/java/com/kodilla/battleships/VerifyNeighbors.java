package com.kodilla.battleships;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import java.util.*;

public class VerifyNeighbors extends Exception{
    private Set<Integer> listOfNeighbors = new HashSet<>();
    public boolean checkIfAnyNeighbors(int location, GridPane gridBoard, Set<Integer> alreadyOccupiedCells){
        //If location 00, 10, 20, ... 90 then
        if (location%10==0) {
            if(alreadyOccupiedCells.contains((location-10))
                    || alreadyOccupiedCells.contains(location-9)
                    || alreadyOccupiedCells.contains(location+1)
                    || alreadyOccupiedCells.contains(location+10)
                    || alreadyOccupiedCells.contains(location+11)
            ) {
                return true;
            }
        } else if (location == 9 || location == 19 ||location == 29 ||location == 39 ||location == 49 ||location == 59
                ||location == 69 ||location == 79 ||location == 89 ||location == 99)
        {
            if(alreadyOccupiedCells.contains(location-10)
                    || alreadyOccupiedCells.contains(location-11)
                    || alreadyOccupiedCells.contains(location-1)
                    || alreadyOccupiedCells.contains(location+9)
                    || alreadyOccupiedCells.contains(location+10)
            ) {
                return true;
            }
        } else {
            if(alreadyOccupiedCells.contains(location-10)
                    || alreadyOccupiedCells.contains(location-11)
                    || alreadyOccupiedCells.contains(location-9)
                    || alreadyOccupiedCells.contains(location-1)
                    || alreadyOccupiedCells.contains(location+1)
                    || alreadyOccupiedCells.contains(location+9)
                    || alreadyOccupiedCells.contains(location+10)
                    || alreadyOccupiedCells.contains(location+11)
            ) {
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
                    listOfAllowedLocation.add(temp - 10);
                }
                    listOfAllowedLocation.add(temp + 1);
                if((temp+10)>99){} //out of matrix
                else {
                    listOfAllowedLocation.add(temp + 10);
                }
            } else if (temp == 9 || temp == 19 ||temp == 29 ||temp == 39 ||temp == 49 ||temp == 59
                    ||temp == 69 ||temp == 79 ||temp == 89 ||temp == 99)
            {
                if((temp-10)<0){} //out of matrix
                else {
                    listOfAllowedLocation.add(temp - 10);
                }
                    listOfAllowedLocation.add(temp - 1);
                if((temp+10)>99){} //out of matrix
                else {
                    listOfAllowedLocation.add(temp + 10);
                }
            } else {
                if((temp-10)<0){} //out of matrix
                else {
                    listOfAllowedLocation.add(temp - 10);
                }
                    listOfAllowedLocation.add(temp - 1);
                    listOfAllowedLocation.add(temp + 1);
                if((temp+10)>99){} //out of matrix
                else {
                    listOfAllowedLocation.add(temp + 10);
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

public Set<Integer> getListOfNeighbors(List<Integer> shipNumbersLocation) {
    listOfNeighbors.clear();
    for(Integer location: shipNumbersLocation) {
        if (location == 0 || location % 10 == 0) {
            if(!((location-10)<0)) {listOfNeighbors.add((location-10));
            }
            if(!((location-9)<0)) {listOfNeighbors.add((location-9));
            }
            listOfNeighbors.add((location+1));
            if(!((location+10)>99)) {listOfNeighbors.add((location+10));
            }
            if(!((location+11)>99)) {listOfNeighbors.add((location+11));
            }
        } else if (location%10 == 9) {
            if(!(location-10<0)){ listOfNeighbors.add(location-10);}
            if(!(location-11<0)){ listOfNeighbors.add(location-11);}
            listOfNeighbors.add(location-1);
            if(!(location+9>99)){ listOfNeighbors.add(location+9);}
            if(!(location+10>99)){ listOfNeighbors.add(location+10);}
        } else {
            listOfNeighbors.add(location - 11);
            listOfNeighbors.add(location - 10);
            listOfNeighbors.add(location - 9);
            listOfNeighbors.add(location - 1);
            listOfNeighbors.add(location + 1);
            listOfNeighbors.add(location + 9);
            listOfNeighbors.add(location + 10);
            listOfNeighbors.add(location + 11);
            }
        }
    listOfNeighbors.removeAll(shipNumbersLocation);
    return listOfNeighbors;
    }

public GameButton getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        GameButton result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = (GameButton)node;
                break;
            }
        }
        return result;
    }
}

