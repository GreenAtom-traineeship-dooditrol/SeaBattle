package com.dooditrol.seabattle.player;

import com.dooditrol.seabattle.Orientation;

import java.util.Scanner;

public class Human extends Player {

    //private final Scanner in;

    public Human(String name) {
        super(name);
    }

    public boolean isRandom() {
        Scanner in = new Scanner(System.in);
        String str = in.next();

        if (str.equals("random")) {
            return true;
        }
        else {
            return false;
        }
    }

    public PlacementCommand getPlacementCommand(boolean sizeOne) {
        Scanner in = new Scanner(System.in);

        int j = in.nextInt();
        int i = in.nextInt();
        Orientation orientation = Orientation.NON;
        boolean wrong = false;

        if (!sizeOne) {
            String str = in.next();

            if (str.equals("vertical")) {
                orientation = Orientation.VERTICAL;
            }
            else if (str.equals("horizontal")) {
                orientation = Orientation.HORIZONTAL;
            }
            else {
                wrong = true;
            }
        }
        return new PlacementCommand(i, j, orientation, wrong);
    }

    @Override
    public GameCommand getGameCommand() {
        Scanner in = new Scanner(System.in);

        String str = in.next();

        if (str.equals("exit")) {
            return new GameCommand(-1 ,-1, GameCommand.Type.EXIT);
        }
        else if (str.equals("shoot")) {
            int j = in.nextInt();
            int i = in.nextInt();
            return new GameCommand(i , j, GameCommand.Type.SHOOT);
        }
        else {
            return new GameCommand(-1,-1, GameCommand.Type.WRONG);
        }
    }
}
