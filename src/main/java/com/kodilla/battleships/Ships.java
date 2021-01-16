package com.kodilla.battleships;

import javafx.scene.layout.GridPane;

import java.util.*;

public class Ships {
    String name;
    int health;
    GridPane gridPaneAssigned = new GridPane();
    Boolean isDestroyed = false;
    HashMap<Integer, Integer> shipLocation = new HashMap<>();
    public Ships(int platowiec, Set<Integer> locations, GridPane gridPaneAssigned){
        this.name = platowiec+"";
        health = platowiec;
        this.gridPaneAssigned= gridPaneAssigned;
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
          VerifyNeighbors validator = new VerifyNeighbors();
          List<Integer> shipNumbersLocation = new ArrayList<>();
          Set<Integer> shipNeighborsLocation = new HashSet<>();


          for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
              shipNumbersLocation.add(entry.getKey());
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
                checkStatus();
                gameButton.setStyle("-fx-background-color: #FF0000;");
                return true;
            }
        }
        gameButton.setStyle("-fx-background-color: #000000;");

        return false;
    }
}
