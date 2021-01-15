package com.kodilla.battleships;

import java.util.*;

public class Ships {
    String name;
    int health;
    Boolean isDestroyed = false;
    HashMap<Integer, Integer> shipLocation = new HashMap<>();
    public Ships(int platowiec, Set<Integer> locations){
        this.name = platowiec+"";
        health = platowiec;
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
                if(entry.getValue()>0){
                   status++;
                }
        }
      if(status>0){
          isDestroyed = true;
      }
    }
    public boolean checkIfHit(Integer location){
        for(Map.Entry<Integer, Integer> entry: shipLocation.entrySet()){
            if(entry.getKey()==location){
                shipLocation.replace(entry.getKey(), 0);
                health--;
                checkStatus();
                return true;
            }
        }
        return false;
    }
}
