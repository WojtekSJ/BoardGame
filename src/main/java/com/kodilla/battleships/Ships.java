package com.kodilla.battleships;

import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Ships implements Serializable {
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(name);
        oos.writeObject(health);
        oos.writeObject(isDestroyed);
        oos.writeObject(shipLocation);
        oos.writeObject(playerPotentialShipLocation);
    }
    private void readObject(ObjectInputStream oos) throws IOException {
        try
        {
            this.name = (String)oos.readObject();
            this.health = (int)oos.readObject();
            this.isDestroyed = (Boolean) oos.readObject();
            this.shipLocation = (HashMap<Integer, Integer>) oos.readObject();
            this.playerPotentialShipLocation = (PotentialPlayerShipLocationList) oos.readObject();

        }
        catch (Exception e)
        {
            throw new IOException("Error occurred resolving deserialized method '%s.%s'");
        }
    }
    String name;
    int health;



    GridPane gridPaneAssigned;
    Boolean isDestroyed = false;
    HashMap<Integer, Integer> shipLocation = new HashMap<>();
    PotentialPlayerShipLocationList playerPotentialShipLocation;

    public Ships(int platowiec, Set<Integer> locations, GridPane gridPaneAssigned){
        this.name = platowiec+"";
        health = platowiec;
        this.gridPaneAssigned= gridPaneAssigned;
        for(Integer tempLocation: locations){
            shipLocation.put(tempLocation, 1);
        }
    }
    public Ships(int platowiec, Set<Integer> locations, GridPane gridPaneAssigned, PotentialPlayerShipLocationList playerPotentialShipLocation){
        this.name = platowiec+"";
        health = platowiec;
        this.gridPaneAssigned= gridPaneAssigned;
        this.playerPotentialShipLocation = playerPotentialShipLocation;
        for(Integer tempLocation: locations){
            shipLocation.put(tempLocation, 1);
        }
    }
    public void setGridPaneAssigned(GridPane gridPaneAssigned) {
        this.gridPaneAssigned = gridPaneAssigned;
    }
    public void checkStatus() {
        int status = 0;
        for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
                if(entry.getValue()>0){
                   status++;
                }
        }
      if(status==0){
          GameButton toChangeButton;
          VerifyNeighbors verificator = new VerifyNeighbors();
          List<Integer> shipNumbersLocation = new ArrayList<>();
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
          Set<Integer> listOfNeighborCells;
          listOfNeighborCells = verificator.getListOfNeighbors(shipNumbersLocation);
          for(Integer positionToColor: listOfNeighborCells) {
              try {
                  toChangeButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(positionToColor), verificator.getColumnLocation(positionToColor), gridPaneAssigned);
                  toChangeButton.setStyle("-fx-background-color: #000000;");
                  toChangeButton.setDisable(true);
                  if (playerPotentialShipLocation.getPlayerPotentialShipLocation().size()>0){
                      playerPotentialShipLocation.removeSetFromPosition(listOfNeighborCells);
                  }

              } catch (Exception e) {
                  System.out.println("There is no ship to deploy");
              }
          }
          isDestroyed = true;
          System.out.println("Statek " + name + " zniszczony");
      }
    }
    public boolean checkIfHit(Integer location, GameButton gameButton){
        for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
            if(entry.getKey().equals(location)){
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
