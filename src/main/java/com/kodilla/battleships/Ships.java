package com.kodilla.battleships;

import javafx.scene.layout.GridPane;

import java.util.*;

public class Ships {
    String name;
    int health;
    GridPane gridPaneAssigned = new GridPane();
    Boolean isDestroyed = false;
    HashMap<Integer, Integer> shipLocation = new HashMap<>();
    Set<Integer> playerPotentialShipLocation = new HashSet<>();

    public Ships(int platowiec, Set<Integer> locations, GridPane gridPaneAssigned){
        this.name = platowiec+"";
        health = platowiec;
        this.gridPaneAssigned= gridPaneAssigned;
        for(Integer tempLocation: locations){
            shipLocation.put(tempLocation, 1);
        }

    }

    public Ships(int platowiec, Set<Integer> locations, GridPane gridPaneAssigned, Set<Integer> playerPotentialShipLocation){
        this.name = platowiec+"";
        health = platowiec;
        this.gridPaneAssigned= gridPaneAssigned;
        this.playerPotentialShipLocation = playerPotentialShipLocation;
        for(Integer tempLocation: locations){
            shipLocation.put(tempLocation, 1);
        }

    }

    public Boolean getDestroyed() {
        return isDestroyed;
    }

    public void getHit(int location) {

    }

    public void checkStatus() {
        int status = 0;
        for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
           // System.out.println("Platowiec: " + name +" Lokalizacja: " + entry.getKey() + "Ma status: " + entry.getValue());
                if(entry.getValue()>0){
                   status++;
                }
        }
      if(status==0){
          GameButton toChangeButton = new GameButton();
          VerifyNeighbors verificator = new VerifyNeighbors();
          List<Integer> shipNumbersLocation = new ArrayList<>();
          Set<Integer> shipNeighborsLocation = new HashSet<>();


          for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
              shipNumbersLocation.add(entry.getKey());
          }

          for(Integer positionToColor: shipNumbersLocation) {
              try {
                  toChangeButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(positionToColor), verificator.getColumnLocation(positionToColor), gridPaneAssigned);
                  toChangeButton.setStyle("-fx-background-color: #FF0000;");
              } catch (Exception e) {
                  System.out.println("There is no ship to deploy");
              }
          }
          Set<Integer> listOfNeighborCells = new HashSet<>();
          listOfNeighborCells = verificator.getListOfNeighbors(shipNumbersLocation);
          for(Integer positionToColor: listOfNeighborCells) {
              try {
                  toChangeButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(positionToColor), verificator.getColumnLocation(positionToColor), gridPaneAssigned);
                  toChangeButton.setStyle("-fx-background-color: #000000;");
                  toChangeButton.setDisable(true);
                  // if computer need to remove button from the list of potential targets
                  if (playerPotentialShipLocation.size()>0){
                      playerPotentialShipLocation.removeAll(listOfNeighborCells);
                  }

              } catch (Exception e) {
                  System.out.println("There is no ship to deploy");
              }
          }
          //shipNeighborsLocation
          //validator.


          isDestroyed = true;
          System.out.println("Statek " + name + " zniszczony");
          //comunicator.

      }
    }
    public boolean checkIfHit(Integer location, GameButton gameButton){
        for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
            if(entry.getKey()==location){

                shipLocation.replace(entry.getKey(), 0);
                health--;
                gameButton.setStyle("-fx-background-color: #FFD700;");
                checkStatus();
                return true;
            }
        }
        gameButton.setStyle("-fx-background-color: #000000;");

        return false;
    }
}
