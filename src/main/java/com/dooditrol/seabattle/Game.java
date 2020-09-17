package com.dooditrol.seabattle;

import com.dooditrol.seabattle.field.Field;
import com.dooditrol.seabattle.field.ResultOfShot;
import com.dooditrol.seabattle.field.Ship;
import com.dooditrol.seabattle.player.*;

import java.util.Map;
import java.util.Random;

public class Game {

    private final Map<Ship.Type, Integer> fleet;
    private final Field fieldOfFirstPlayer;
    private final Field fieldOfSecondPlayer;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final View view;

    public Game(int sizeField, Map<Ship.Type, Integer> fleet) {
        this.fleet = fleet;
        fieldOfFirstPlayer = new Field(sizeField);
        fieldOfSecondPlayer = new Field(sizeField);
        firstPlayer = new Human("human");
        secondPlayer = new Computer("computer", sizeField);
        view = new View();
    }

    public void play() {
        placeShips(fieldOfFirstPlayer, firstPlayer);
        placeShips(fieldOfSecondPlayer, secondPlayer);

        Player player = firstPlayer;
        Field field = fieldOfFirstPlayer;
        Player enemy = secondPlayer;
        Field fieldOfEnemy = fieldOfSecondPlayer;

        while (true) {
            view.drawField(player, field, enemy, fieldOfEnemy);

            GameCommand command = player.getGameCommand();

            switch (command.getType()) {
                case EXIT:

                    return;
                case SHOOT:
                    ResultOfShot resultOfShot = fieldOfEnemy.shoot(command.getI(), command.getJ());
                    view.drawResultShoot(player, field, enemy, fieldOfEnemy, resultOfShot);

                    switch (resultOfShot) {
                        case KILLED_ALL_SHIPS:
                            return;
                        case KILLED:
                        case WOUNDED:
                        case WRONG_COORDINATES:
                            continue;
                        case MISSED:
                            break;
                    }
                    break;
                case WRONG:
                    view.drawWrongCommand();
                    continue;
            }

            Player tmpPlayer = player;
            player = enemy;
            enemy = tmpPlayer;

            Field tmpField = field;
            field = fieldOfEnemy;
            fieldOfEnemy = tmpField;
        }
    }

    private void placeShips(Field field, Player player) {

        if (player instanceof Human) {
            placeShips(field, (Human) player);
        }
        else {
            randomlyPlaceShips(field);
        }
    }

    private void placeShips(Field field, Human player) {

        if (player.isRandom()) {
            randomlyPlaceShips(field);
            return;
        }

        for (Map.Entry<Ship.Type, Integer> countOfShips : fleet.entrySet()) {
            for (int i = 0; i < countOfShips.getValue(); i++) {

                view.drawField(player.getName(), field);
                view.drawEntryShip(countOfShips.getKey());

                while (true) {
                    PlacementCommand cmdOfPlace;

                    if (countOfShips.getKey() == Ship.Type.TORPEDO_BOAT) {
                        cmdOfPlace = player.getPlacementCommand(true);
                    }
                    else {
                        cmdOfPlace = player.getPlacementCommand(false);
                    }

                    if (cmdOfPlace.isWrong()) {
                        view.drawWrongCommand();
                        continue;
                    }

                    if (field.checkCorrectPosition(cmdOfPlace.getI(), cmdOfPlace.getJ(), countOfShips.getKey(),
                            cmdOfPlace.getOrientation())) {
                        field.setShip(cmdOfPlace.getI(), cmdOfPlace.getJ(), countOfShips.getKey(),
                                cmdOfPlace.getOrientation());
                        break;
                    }
                    else {
                        view.drawWrongPosition();
                    }
                }
            }
        }
    }

    private void randomlyPlaceShips(Field field) {
        Random random = new Random();

        for (Map.Entry<Ship.Type, Integer> countOfShips : fleet.entrySet()) {
            for (int n = 0; n < countOfShips.getValue(); n++) {

                while (true) {
                    int i = random.nextInt(field.getSize());
                    int j = random.nextInt(field.getSize());
                    Orientation orientation;

                    if (countOfShips.getKey() == Ship.Type.TORPEDO_BOAT) {
                        orientation = Orientation.NON;
                    }
                    else  if (random.nextBoolean()) {
                        orientation = Orientation.HORIZONTAL;
                    }
                    else {
                        orientation = Orientation.VERTICAL;
                    }

                    if (field.checkCorrectPosition(i, j, countOfShips.getKey(), orientation)) {
                        field.setShip(i, j, countOfShips.getKey(), orientation);
                        break;
                    }
                }
            }
        }
    }
}
