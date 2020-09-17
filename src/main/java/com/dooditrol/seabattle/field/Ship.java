package com.dooditrol.seabattle.field;

import com.dooditrol.seabattle.Orientation;

public class Ship {

    private final int i;
    private final int j;
    private final Orientation orientation;
    private final Type typeShip;
    private final boolean[] hitCells;
    private boolean killed;

    public Ship(int i, int j, Ship.Type typeShip, Orientation orientation) {
        this.i = i;
        this.j = j;
        this.typeShip = typeShip;
        this.orientation = orientation;
        hitCells = new boolean[typeShip.getSize()];
        killed = false;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Type getType() {
        return typeShip;
    }

    public boolean isKilled() {
        return killed;
    }

    public ResultOfShot shoot(int index) {
        hitCells[index] = true;

        for (int i = 0; i < typeShip.getSize(); i++) {
            if (!hitCells[i]) {
                return ResultOfShot.WOUNDED;
            }
        }
        killed = true;
        return  ResultOfShot.KILLED;
    }

    public enum Type {
        BATTLESHIP(4),
        CRUISER(3),
        DESTROYER(2),
        TORPEDO_BOAT(1);

        private int size;

        Type(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
