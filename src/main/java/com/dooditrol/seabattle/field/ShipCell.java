package com.dooditrol.seabattle.field;

public class ShipCell extends Cell {

    private final Ship ship;
    private final int indexOfDeck;

    public ShipCell(Ship ship, int indexCells) {
        super();
        this.ship = ship;
        this.indexOfDeck = indexCells;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isKilled() {
        return ship.isKilled();
    }

    @Override
    public CellState getState() {

        if (isHit()) {
            if (isKilled()) {
                return CellState.KILLED_SHIP;
            }
            else {
                return CellState.WOUNDED_SHIP;
            }
        }
        else {
            return CellState.WHOLE_SHIP;
        }
    }

    @Override
    public CellState getStateForEnemy() {

        if (isHit()) {
            if (isKilled()) {
                return CellState.KILLED_SHIP;
            }
            else {
                return CellState.WOUNDED_SHIP;
            }
        }
        else {
            return CellState.FOG;
        }
    }

    @Override
    public ResultOfShot shoot() {
        if (isHit()) {
            return ResultOfShot.MISSED;
        }
        else {
            super.hit();
            return ship.shoot(indexOfDeck);
        }
    }
}
