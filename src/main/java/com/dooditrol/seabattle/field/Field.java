package com.dooditrol.seabattle.field;

import com.dooditrol.seabattle.Orientation;

import java.util.ArrayList;

public class Field {

    private final int size;
    //private static final int COUNT_OF_SHIPS = 1;

    private Cell[][] cells;
    private ArrayList<Ship> ships;

    public Field(int size) {
        this.size = size;
        cells = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new SeaCell();
            }
        }
        ships = new ArrayList<Ship>();
    }

    public int getSize() {
        return size;
    }
    
    public CellState getCellState(int i, int j) {
        return cells[i][j].getState();
    }

    public CellState getCellStateForEnemy(int i, int j) {
        return cells[i][j].getStateForEnemy();
    }

    public boolean checkCorrectPosition(int i, int j, Ship.Type typeShip, Orientation orientation) {

        if (i < 0 || j < 0 || (orientation == Orientation.NON && (size <= i || size <= j))
                || (orientation == Orientation.HORIZONTAL && size < (j + typeShip.getSize()))
                || (orientation == Orientation.VERTICAL && size < (i + typeShip.getSize()))) {

            return false;
        }
        int sizePlaceH;
        int sizePlaceV;

        if (orientation == Orientation.HORIZONTAL) {
            sizePlaceV = 3;
            sizePlaceH = typeShip.getSize() + 2;
        }
        else {
            sizePlaceH = 3;
            sizePlaceV = typeShip.getSize() + 2;
        }

        int topCoordinate = i - 1;
        int leftCoordinate = j - 1;

        if (topCoordinate < 0) {
            topCoordinate++;
            sizePlaceV--;
        }
        else if (topCoordinate + sizePlaceV > size) {
            sizePlaceV--;
        }

        if (leftCoordinate < 0) {
            leftCoordinate++;
            sizePlaceH--;
        }
        else if (leftCoordinate + sizePlaceH > size) {
            sizePlaceH--;
        }

        for (int indexI = topCoordinate; indexI < (topCoordinate + sizePlaceV); indexI++) {
            for (int indexJ = leftCoordinate; indexJ < (leftCoordinate + sizePlaceH); indexJ++) {

                if (cells[indexI][indexJ] instanceof ShipCell) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setShip(int i, int j, Ship.Type typeShip, Orientation orientation) {
        Ship ship = new Ship(i, j, typeShip, orientation);
        ships.add(ship);

        for (int index = 0; index < typeShip.getSize(); index++) {

            if (orientation == Orientation.HORIZONTAL) {
                cells[i][j + index] = new ShipCell(ship, index);
            }
            else {
                cells[i + index][j] = new ShipCell(ship, index);
            }
        }
    }

    public ResultOfShot shoot(int i, int j) {

        if (i < 0 || size <= i || j < 0 || size <= j) {
            return ResultOfShot.WRONG_COORDINATES;
        }
        ResultOfShot result = cells[i][j].shoot();

        if (result == ResultOfShot.KILLED) {
            Ship killedShip = ((ShipCell) cells[i][j]).getShip();

            int sizePlaceH;
            int sizePlaceV;

            if (killedShip.getOrientation() == Orientation.HORIZONTAL) {
                sizePlaceV = 3;
                sizePlaceH = killedShip.getType().getSize() + 2;
            }
            else {
                sizePlaceH = 3;
                sizePlaceV = killedShip.getType().getSize() + 2;
            }

            int topCoordinate = killedShip.getI() - 1;
            int leftCoordinate = killedShip.getJ() - 1;

            if (topCoordinate < 0) {
                topCoordinate++;
                sizePlaceV--;
            }
            else if (topCoordinate + sizePlaceV > size) {
                sizePlaceV--;
            }

            if (leftCoordinate < 0) {
                leftCoordinate++;
                sizePlaceH--;
            }
            else if (leftCoordinate + sizePlaceH > size) {
                sizePlaceH--;
            }

            for (int indexI = topCoordinate; indexI < (topCoordinate + sizePlaceV); indexI++) {
                for (int indexJ = leftCoordinate; indexJ < (leftCoordinate + sizePlaceH); indexJ++) {
                    cells[indexI][indexJ].hit();
                }
            }

            for (Ship ship: ships) {
                if (!ship.isKilled()) {
                    return result;
                }
            }
            return ResultOfShot.KILLED_ALL_SHIPS;
        }
        return result;
    }
}
