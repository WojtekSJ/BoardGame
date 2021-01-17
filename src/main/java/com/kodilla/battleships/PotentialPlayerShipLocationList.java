package com.kodilla.battleships;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PotentialPlayerShipLocationList {
    private Set<Integer> playerPotentialShipLocation = new HashSet<>();

    public void addPlayerPotentialShipLocation(Integer position) {
        playerPotentialShipLocation.add(position);
    }

    public Set<Integer> getPlayerPotentialShipLocation() {
        return playerPotentialShipLocation;
    }

    public void removeListFromPosition(List<Integer> listToRemove){
        playerPotentialShipLocation.removeAll(listToRemove);
    }
    public void removeSetFromPosition(Set<Integer> setToRemove){
        playerPotentialShipLocation.removeAll(setToRemove);
    }
    public void remove(Integer toRemove){
        playerPotentialShipLocation.remove(toRemove);
    }
}
