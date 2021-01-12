package com.kodilla.battleships;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ships {
    String name;
    int health;
    Boolean isDestroyed = false;
    HashMap<Integer, Integer> shipLocation = new HashMap<>();
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

}
