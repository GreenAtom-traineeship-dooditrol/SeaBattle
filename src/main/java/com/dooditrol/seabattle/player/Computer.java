package com.dooditrol.seabattle.player;

import java.util.Random;
import java.util.Scanner;

public class Computer extends Player {

    public final int sizeField;

    public Computer(String name, int size) {
        super(name);
        this.sizeField = size;
    }

    @Override
    public GameCommand getGameCommand() {
        Scanner in = new Scanner(System.in);
        in.nextLine();

        Random random = new Random();
        int i = random.nextInt(sizeField);
        int j = random.nextInt(sizeField);

        return new GameCommand(i, j, GameCommand.Type.SHOOT);
    }
}
