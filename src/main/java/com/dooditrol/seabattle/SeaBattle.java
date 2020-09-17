package com.dooditrol.seabattle;

import com.dooditrol.seabattle.field.Ship;

import java.util.Map;
import java.util.TreeMap;

public class SeaBattle {

    public static void main(String[] args) {
        Map<Ship.Type, Integer> fleet = new TreeMap<Ship.Type, Integer>();

        /*fleet.put(Ship.Type.TORPEDO_BOAT, 2);
        fleet.put(Ship.Type.DESTROYER, 1);
        fleet.put(Ship.Type.CRUISER, 1);
        Game game = new Game(5, fleet);*/

        fleet.put(Ship.Type.BATTLESHIP, 1);
        fleet.put(Ship.Type.CRUISER, 2);
        fleet.put(Ship.Type.DESTROYER, 3);
        fleet.put(Ship.Type.TORPEDO_BOAT, 4);
        Game game = new Game(10, fleet);

        game.play();
    }
}
