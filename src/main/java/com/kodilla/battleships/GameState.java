package com.kodilla.battleships;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class GameState implements Serializable {
    Fleet computerFleet;
    Fleet playerFleet;
    HashSet<Integer> computerGridMiss;
    HashSet<Integer> computerGridHit;
    HashSet<Integer> computerGridSink;
    HashSet<Integer> playerGridMiss;
    HashSet<Integer> playerGridHit;
    HashSet<Integer> playerGridSink;
    String gameStage;
    Set<Integer> listOfPlayerShipLocation;
    Set<Integer> listOfComputerShipLocation;
    boolean firstShoot = false;
    PotentialPlayerShipLocationList playerPotentialShipLocationClass;
    Set<Integer> targetLockedlist;
    Set<Integer> hitCellsList;
    int shipsBeforeShot;
    int shipsAfterShot;
    public void GameStateSave(Fleet computerFleet, Fleet playerFleet, HashSet<Integer> computerGridMiss, HashSet<Integer> computerGridHit, HashSet<Integer> computerGridSink, HashSet<Integer> playerGridMiss, HashSet<Integer> playerGridHit, HashSet<Integer> playerGridSink, String gameStage, Set<Integer> listOfPlayerShipLocation, Set<Integer> listOfComputerShipLocation, boolean firstShoot, PotentialPlayerShipLocationList playerPotentialShipLocationClass, Set<Integer> targetLockedlist, Set<Integer> hitCellsList, int shipsBeforeShot, int shipsAfterShot) {
        this.computerFleet = computerFleet;
        this.playerFleet = playerFleet;
        this.computerGridMiss = computerGridMiss;
        this.computerGridHit = computerGridHit;
        this.computerGridSink = computerGridSink;
        this.playerGridMiss = playerGridMiss;
        this.playerGridHit = playerGridHit;
        this.playerGridSink = playerGridSink;
        this.gameStage = gameStage;
        this.listOfPlayerShipLocation = listOfPlayerShipLocation;
        this.listOfComputerShipLocation = listOfComputerShipLocation;
        this.firstShoot = firstShoot;
        this.playerPotentialShipLocationClass = playerPotentialShipLocationClass;
        this.targetLockedlist = targetLockedlist;
        this.hitCellsList = hitCellsList;
        this.shipsBeforeShot = shipsBeforeShot;
        this.shipsAfterShot = shipsAfterShot;
    }
    public Fleet getComputerFleet() {
        return computerFleet;
    }
    public Fleet getPlayerFleet() {
        return playerFleet;
    }

    public HashSet<Integer> getComputerGridMiss() {
        return computerGridMiss;
    }

    public HashSet<Integer> getComputerGridHit() {
        return computerGridHit;
    }

    public HashSet<Integer> getComputerGridSink() {
        return computerGridSink;
    }

    public HashSet<Integer> getPlayerGridMiss() {
        return playerGridMiss;
    }

    public HashSet<Integer> getPlayerGridHit() {
        return playerGridHit;
    }

    public HashSet<Integer> getPlayerGridSink() {
        return playerGridSink;
    }

    public String getGameStage() {
        return gameStage;
    }

    public Set<Integer> getListOfPlayerShipLocation() {
        return listOfPlayerShipLocation;
    }

    public Set<Integer> getListOfComputerShipLocation() {
        return listOfComputerShipLocation;
    }

    public boolean isFirstShoot() {
        return firstShoot;
    }

    public PotentialPlayerShipLocationList getPlayerPotentialShipLocationClass() {
        return playerPotentialShipLocationClass;
    }

    public Set<Integer> getTargetLockedlist() {
        return targetLockedlist;
    }

    public Set<Integer> getHitCellsList() {
        return hitCellsList;
    }

    public int getShipsBeforeShot() {
        return shipsBeforeShot;
    }

    public int getShipsAfterShot() {
        return shipsAfterShot;
    }
}
