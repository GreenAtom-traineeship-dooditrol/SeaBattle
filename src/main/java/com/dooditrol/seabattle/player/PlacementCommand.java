package com.dooditrol.seabattle.player;

import com.dooditrol.seabattle.Orientation;

public class PlacementCommand {

    private final int i;
    private final int j;
    private final Orientation orientation;
    private final boolean wrong;

    public PlacementCommand(int i, int j, Orientation orientation, boolean wrong) {
        this.i = i;
        this.j = j;
        this.orientation = orientation;
        this.wrong = wrong;
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

    public boolean isWrong() {
        return wrong;
    }
}
