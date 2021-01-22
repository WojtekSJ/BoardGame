package com.kodilla.battleships;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GameState implements Serializable {
    Fleet computerFleet = new Fleet();
    Fleet playerFleet = new Fleet();
    Set<Integer> computerGridMiss = new HashSet<>();
    Set<Integer> computerGridHit = new HashSet<>();
    Set<Integer> computerGridSink = new HashSet<>();

    Set<Integer> playerGridMiss = new HashSet<>();
    Set<Integer> playerGridHit = new HashSet<>();
    Set<Integer> playerGridSink = new HashSet<>();

    String gameStage;

    Set<Integer> listOfPlayerShipLocation = new HashSet<>();
    Set<Integer> listOfComputerShipLocation = new HashSet<>();

    boolean firstShoot = false;
    PotentialPlayerShipLocationList playerPotentialShipLocationClass = new PotentialPlayerShipLocationList();
    Set<Integer> targetLockedlist = new HashSet<>();
    Set<Integer> hitCellsList = new HashSet<>();
    int shipsBeforeShot;
    int shipsAfterShot;
}
