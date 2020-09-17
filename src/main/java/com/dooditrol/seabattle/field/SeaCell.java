package com.dooditrol.seabattle.field;

public class SeaCell extends Cell {

    public SeaCell() {
        super();
    }

    @Override
    public CellState getState() {

        if (isHit()) {
            return CellState.HIT;
        }
        else {
            return CellState.SEA;
        }
    }

    @Override
    public CellState getStateForEnemy() {

        if (isHit()) {
            return CellState.HIT;
        }
        else {
            return CellState.FOG;
        }
    }

    @Override
    public ResultOfShot shoot() {
        super.hit();
        return ResultOfShot.MISSED;
    }
}
