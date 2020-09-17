package com.dooditrol.seabattle.field;

public abstract class Cell {

    private boolean hit;

    public Cell() {
        hit = false;
    }

    public boolean isHit() {
        return hit;
    }

    protected void hit() {
        hit = true;
    }

    abstract public CellState getState();
    abstract public CellState getStateForEnemy();
    abstract public ResultOfShot shoot();
}
