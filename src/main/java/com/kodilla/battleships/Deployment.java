package com.kodilla.battleships;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;

public class Deployment {

    private Set<Integer> randomSetUpTempLocation = new HashSet<>();
    private List<Integer> randomSetUpListOfAllowedLocation = new ArrayList<>();
    private Set<Integer> potentialNextCellsForShip = new HashSet<>();
    private Set<Integer> computerTempShipLocation = new HashSet<>();


    String name;
    int shipSize;
    int occupiedCells;
    boolean shipOnBoard = false;
    List<Integer> cellsLocation = new ArrayList<>();
    GameButton temp = new GameButton();

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
    public void randomComputerDeployment(Set<Integer> listOfComputerShipLocation, GridPane computerGridBoard,
                                         Fleet computerFleet, int fiveCells, int fourCells, int threeCells,
                                         int twoCells, int oneCells) {
        List<Integer> numberOfShips = new ArrayList<>();
        Integer nextCell = -1;

        for(int i=0;i<fiveCells;i++){
            numberOfShips.add(5);
        }
        for(int i=0;i<fourCells;i++){
            numberOfShips.add(4);
        }
        for(int i=0;i<threeCells;i++){
            numberOfShips.add(3);
        }
        for(int i=0;i<twoCells;i++){
            numberOfShips.add(2);
        }
        for(int i=0;i<oneCells;i++){
            numberOfShips.add(1);
        }
        VerifyNeighbors verificator = new VerifyNeighbors();
        //losowanie pierwszej pustej komorki
        ////////////////////////////////////////////////
        List<Integer> computerPotentialShipLocationList = new ArrayList<>();
        List<Integer> computerPotentialShipLocation = new ArrayList<>();
        for (Integer i = 0; i < 100; i++) {
            computerPotentialShipLocationList.add(i);
        }

        for(int i=0; i<numberOfShips.size(); i++) {
            shipOnBoard = false;
            //dla pierwszej pozycji
            computerPotentialShipLocationList.clear();
            for (Integer newList : computerPotentialShipLocation) {
                computerPotentialShipLocationList.add(newList);
            }
            Random rn = new Random();

            Integer randomShoot = -1;// = computerPotentialShipLocationList.get(rn.nextInt(computerPotentialShipLocationList.size()));
            boolean shipPossibleToLocate = false;
            do{
                shipPossibleToLocate =false;
                computerTempShipLocation.clear();
            do {
                //poki nie trafi pierwszeh wolnej komorki
                //verificator.checkIfAnyNeighbors()
                randomShoot = computerPotentialShipLocationList.get(rn.nextInt(computerPotentialShipLocationList.size()));
            } while (!verificator.checkIfAnyNeighbors(randomShoot, computerGridBoard, listOfComputerShipLocation));

            computerTempShipLocation.add(randomShoot);
            //verificator.createAllowedCellList(computerTempShipLocation, potentialNextCellsForShip);
            if (computerTempShipLocation.size() == numberOfShips.get(i)){
                listOfComputerShipLocation.addAll(computerTempShipLocation);
                computerFleet.addToFleet(new Ships(numberOfShips.get(i), computerTempShipLocation));
                shipOnBoard = true;
                shipPossibleToLocate =true;
            }
            else {
                do {
                    //while(potentialNextCellsForShip.size()>0)
                    verificator.createAllowedCellList(computerTempShipLocation, potentialNextCellsForShip);
                    potentialNextCellsForShip.removeAll(computerTempShipLocation);
                    for (Integer temp : potentialNextCellsForShip) {
                        if (verificator.checkIfAnyNeighbors(temp, computerGridBoard, listOfComputerShipLocation)) {
                            potentialNextCellsForShip.remove(temp);
                        }
                    }
                    randomSetUpListOfAllowedLocation.addAll(potentialNextCellsForShip);

                    nextCell = randomSetUpListOfAllowedLocation.get(rn.nextInt(randomSetUpListOfAllowedLocation.size()));
                    computerTempShipLocation.add(nextCell);
                    if (computerTempShipLocation.size() == numberOfShips.get(i)) {
                        listOfComputerShipLocation.addAll(computerTempShipLocation);
                        computerFleet.addToFleet(new Ships(numberOfShips.get(i), computerTempShipLocation));
                        shipOnBoard = true;
                        shipPossibleToLocate = true;
                    } else {
                        potentialNextCellsForShip.remove(nextCell);
                    }
                }while (potentialNextCellsForShip.size()!=0&&!shipOnBoard);
            }
            }while(!shipPossibleToLocate);

            for(Ships tempShip: computerFleet.fleetList) {
                System.out.println("Statek na koordynatach: " + tempShip.shipLocation);
            }
            //computerPotentialShipLocation.remove(randomShoot);


            //
            //////////////////////////////////////////////////



            /*if (tempShipLocation.size() == listOfShipToDeploy.get(0)) {

                Ships ship = new Ships(4, tempShipLocation);

                addToFleet(playerFleet, ship, listOfPlayerShipLocation, tempShipLocation);


                listOfShipToDeploy.remove(0);
                firstCell = true;
                tempShipLocation.clear();
            }*/
        }
        }

    public void computerDeployment(Set<Integer> listOfComputerShipLocation, GridPane computerGridBoard, Fleet computerFleet)   {

        Set<Integer> tempLocation = new HashSet<>();
        tempLocation.add(0);
        tempLocation.add(10);
        tempLocation.add(20);
        tempLocation.add(30);
        tempLocation.add(40);
        listOfComputerShipLocation.add(0);
        listOfComputerShipLocation.add(10);
        listOfComputerShipLocation.add(20);
        listOfComputerShipLocation.add(30);
        listOfComputerShipLocation.add(40);
        Ships fiecioplat = new Ships(5,tempLocation);
        temp = getNodeByRowColumnIndex(0,0,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(0,1,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(0,2,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(0,3,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(0,4,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");

        tempLocation.clear();

        tempLocation.add(4);
        tempLocation.add(5);
        tempLocation.add(6);
        tempLocation.add(7);
        listOfComputerShipLocation.add(4);
        listOfComputerShipLocation.add(5);
        listOfComputerShipLocation.add(6);
        listOfComputerShipLocation.add(7);
        temp = getNodeByRowColumnIndex(4,0,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(5,0,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(6,0,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(7,0,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        Ships czteroplat = new Ships(4,tempLocation);

        tempLocation.clear();

        tempLocation.add(43);
        tempLocation.add(44);
        tempLocation.add(45);
        listOfComputerShipLocation.add(43);
        listOfComputerShipLocation.add(44);
        listOfComputerShipLocation.add(45);
        temp = getNodeByRowColumnIndex(3,4,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(4,4,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(5,4,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        Ships trzyplat = new Ships(3,tempLocation);

        tempLocation.clear();


        tempLocation.add(98);
        tempLocation.add(99);
        listOfComputerShipLocation.add(98);
        listOfComputerShipLocation.add(99);
        temp = getNodeByRowColumnIndex(8,9,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        temp = getNodeByRowColumnIndex(9,9,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        Ships dwuplat = new Ships(2,tempLocation);

        tempLocation.clear();

        tempLocation.add(90);
        listOfComputerShipLocation.add(90);
        temp = getNodeByRowColumnIndex(0,9,computerGridBoard);
        temp.setStyle("-fx-background-color: #FFD700;");
        Ships jednoplat = new Ships(2,tempLocation);

        computerFleet.addToFleet(fiecioplat);
        computerFleet.addToFleet(czteroplat);
        computerFleet.addToFleet(trzyplat);
        computerFleet.addToFleet(dwuplat);
        computerFleet.addToFleet(jednoplat);














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


