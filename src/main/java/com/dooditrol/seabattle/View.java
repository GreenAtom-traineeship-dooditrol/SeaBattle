package com.dooditrol.seabattle;

import com.dooditrol.seabattle.field.CellState;
import com.dooditrol.seabattle.field.Field;
import com.dooditrol.seabattle.field.ResultOfShot;
import com.dooditrol.seabattle.field.Ship;
import com.dooditrol.seabattle.player.Computer;
import com.dooditrol.seabattle.player.Human;
import com.dooditrol.seabattle.player.Player;

public class View {

    public void drawField(String name, Field field) {
        System.out.println("Player field " + name);
        int sizeOfField = field.getSize();

        System.out.print("  ");

        for (int i = 0; i < sizeOfField; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < sizeOfField; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < sizeOfField; j++) {

                drawCell(field.getCellState(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void drawField(Player player, Field field, Player enemy, Field enemyField) {
        //System.out.println("Player's turn " + player.getName());
        if (player instanceof Computer) {
            System.out.println(player.getName() + "'s turn.");
        }
        else {
            System.out.print("  " + player.getName());

            for (int i = 0; i < (2 * field.getSize() - player.getName().length() + 2); i++) {
                System.out.print(" ");
            }
            System.out.print("     ");
            System.out.println(enemy.getName());
//
            System.out.print("  ");
            for (int i = 0; i < field.getSize(); i++) {
                System.out.print(i + " ");
            }
            System.out.print("       ");

            for (int i = 0; i < enemyField.getSize(); i++) {
                System.out.print(i + " ");
            }
            System.out.println();
//
            for (int i = 0; i < field.getSize(); i++) {
                System.out.print(i + " ");

                for (int j = 0; j < field.getSize(); j++) {

                    drawCell(field.getCellState(i, j));
                }
                System.out.print("     ");
                System.out.print(i + " ");

                for (int j = 0; j < field.getSize(); j++) {

                    drawCell(enemyField.getCellStateForEnemy(i, j));
                }
                System.out.println();
            }
        }
    }

    public void drawResultShoot(Player player, Field field, Player enemy, Field enemyField,
                                ResultOfShot resultOfShot) {

        switch (resultOfShot) {
            case KILLED_ALL_SHIPS:

                if (player instanceof Human) {
                    System.out.println("You win!!!");
                    drawField(player, field, enemy, enemyField);
                }
                else {
                    System.out.println("You loos!!!");
                    drawField(enemy, enemyField, player, field);
                }
                break;
            case KILLED:
            case WOUNDED:

                if (player instanceof Human) {

                    if (resultOfShot == ResultOfShot.WOUNDED) {
                        System.out.println("You hit!!!");
                    }
                    else {
                        System.out.println("You sunk enemy ship!!!");
                    }
                    //drawField(player, field, enemy, enemyField);
                }
                else {

                    if (resultOfShot == ResultOfShot.WOUNDED) {
                        System.out.println("Your ship wounded!!!");
                    }
                    else {
                        System.out.println("Your ship was sunk!!!");
                    }
                    drawField(enemy, enemyField, player, field);
                }
                break;
            case MISSED:

                if (player instanceof Human) {
                    System.out.println("You missed.");
                    drawField(player, field, enemy, enemyField);
                }
                else {
                    System.out.println("Computer missed.");
                }
                break;
            case WRONG_COORDINATES:

                if (player instanceof Human) {
                    System.out.println("wrong coordinates! try again.");
                }
                break;
        }
    }

    private void drawCell(CellState cell) {

        switch (cell) {
            case FOG:
                System.out.print("~ ");
                break;
            case SEA:
                System.out.print("  ");
                break;
            case HIT:
                System.out.print("* ");
                break;
            case WHOLE_SHIP:
                System.out.print("k ");
                break;
            case WOUNDED_SHIP:
                System.out.print("x ");
                break;
            case KILLED_SHIP:
                System.out.print("# ");
                break;
        }
    }

    public void drawEntryShip(Ship.Type typeShip) {
        System.out.print("Entry the coordinates(X and Y) of the rostrum ");

        switch (typeShip) {
            case BATTLESHIP:
                System.out.println("battleship (4-decks) and orientation (vertical or horizontal).");
                break;
            case CRUISER:
                System.out.println("cruiser (3-decks) and orientation (vertical or horizontal).");
                break;
            case DESTROYER:
                System.out.println("destroyer (2-decks) and orientation (vertical or horizontal).");
                break;
            case TORPEDO_BOAT:
                System.out.println("torpedo boat (single-decks)");
                break;
        }
    }

    public void drawWrongCommand() {
        System.out.println("wrong command!");
    }

    public void drawWrongPosition() {
        System.out.println("Wrong coordinates and position!!!\nEnter again.");
    }
}
