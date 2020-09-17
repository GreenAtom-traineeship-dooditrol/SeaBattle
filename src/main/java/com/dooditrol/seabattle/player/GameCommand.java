package com.dooditrol.seabattle.player;

public class GameCommand {

    private final int i;
    private final int j;
    private final Type type;

    public GameCommand(int i, int j, Type type) {
        this.i = i;
        this.j = j;
        this.type = type;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SHOOT,
        EXIT,
        WRONG
    }
}
