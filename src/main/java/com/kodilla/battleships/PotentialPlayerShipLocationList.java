package com.kodilla.battleships;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PotentialPlayerShipLocationList implements Serializable {
    private Set<Integer> playerPotentialShipLocation = new HashSet<>();
    public void addPlayerPotentialShipLocation(Integer position) {
        playerPotentialShipLocation.add(position);
    }
    public Set<Integer> getPlayerPotentialShipLocation() {
        return playerPotentialShipLocation;
    }
    public void removeSetFromPosition(Set<Integer> setToRemove){
        playerPotentialShipLocation.removeAll(setToRemove);
    }
    public void remove(Integer toRemove){
        playerPotentialShipLocation.remove(toRemove);
    }
    public void clear(){
        playerPotentialShipLocation.clear();
    }
}
