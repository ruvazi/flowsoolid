/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package y7;

import battleship.examples.*;
import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class Torpedo_1 implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int nextX;
    private int nextY;

    private ArrayList<Position> posArray = new ArrayList<Position>();

    public Torpedo_1() {
    }

    @Override           // leg her
    public void placeShips(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        int state = rnd.nextInt();

//        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
//            Ship s = fleet.getShip(i);
//            boolean vertical = rnd.nextBoolean();
//            Position pos;
//
//            if (vertical) {
//                int x = rnd.nextInt(sizeX);
//                int y = rnd.nextInt(sizeY - (s.size() - 1));
//                pos = new Position(x, y);
//            } else {
//                int x = rnd.nextInt(sizeX - (s.size() - 1));
//                int y = rnd.nextInt(sizeY);
//                pos = new Position(x, y);
//            }
//
//            board.placeShip(pos, s, vertical);
//        }
        switch (state) {
            case 0:
                Position pos1 = new Position(4, 0);
                Ship s1 = fleet.getShip(2);
                board.placeShip(pos1, s1, false);

                Position pos2 = new Position(3, 0);
                Ship s2 = fleet.getShip(3);
                board.placeShip(pos2, s2, false);

                Position pos3 = new Position(2, 0);
                Ship s3 = fleet.getShip(3);
                board.placeShip(pos3, s3, false);

                Position pos4 = new Position(1, 0);
                Ship s4 = fleet.getShip(4);
                board.placeShip(pos4, s4, false);

                Position pos5 = new Position(0, 0);
                Ship s5 = fleet.getShip(5);
                board.placeShip(pos5, s5, false);
                break;
            case 1:
                Position pos6 = new Position(1, 3);
                Ship s6 = fleet.getShip(2);
                board.placeShip(pos6, s6, true);

                Position pos7 = new Position(0, 0);
                Ship s7 = fleet.getShip(3);
                board.placeShip(pos7, s7, false);

                Position pos8 = new Position(2, 0);
                Ship s8 = fleet.getShip(3);
                board.placeShip(pos8, s8, false);

                Position pos9 = new Position(6, 6);
                Ship s9 = fleet.getShip(4);
                board.placeShip(pos9, s9, true);

                Position pos10 = new Position(9, 0);
                Ship s10 = fleet.getShip(5);
                board.placeShip(pos10, s10, false);
                break;
            case 2:
                Position pos11 = new Position(1, 3);
                Ship s11 = fleet.getShip(2);
                board.placeShip(pos11, s11, false);

                Position pos12 = new Position(7, 3);
                Ship s12 = fleet.getShip(3);
                board.placeShip(pos12, s12, true);

                Position pos13 = new Position(6, 9);
                Ship s13 = fleet.getShip(3);
                board.placeShip(pos13, s13, false);

                Position pos14 = new Position(4, 4);
                Ship s14 = fleet.getShip(4);
                board.placeShip(pos14, s14, false);

                Position pos15 = new Position(3, 5);
                Ship s15 = fleet.getShip(5);
                board.placeShip(pos15, s15, true);
                break;
            case 3:
                Position pos16 = new Position(9, 3);
                Ship s16 = fleet.getShip(2);
                board.placeShip(pos16, s16, false);

                Position pos17 = new Position(2, 8);
                Ship s17 = fleet.getShip(3);
                board.placeShip(pos17, s17, true);

                Position pos18 = new Position(0, 4);
                Ship s18 = fleet.getShip(3);
                board.placeShip(pos18, s18, false);

                Position pos19 = new Position(4, 4);
                Ship s19 = fleet.getShip(4);
                board.placeShip(pos19, s19, false);

                Position pos20 = new Position(3, 5);
                Ship s20 = fleet.getShip(5);
                board.placeShip(pos20, s20, true);
                break;
        }

    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {

//        for (Position posObject : posArray) {
//            if (posObject != pos) {
//                System.out.println("Tilføjer til array");
//                posArray.add(pos);
//            } else if (posObject == pos) {
//                pos = getFiringPosition();
//                System.out.println("Ny  " + pos.toString());
//                break;
//            }
//
//        }
//        return pos;
        Position shot = new Position(nextX, nextY);
        ++nextX;
        if (nextX >= sizeX) {
            nextX = 0;
            ++nextY;
            if (nextY >= sizeY) {
                nextY = 0;
            }
        }
        return shot;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        //Do nothing
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
        //Do nothing
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        //Do nothing
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }

    private Position getFiringPosition() {
        int x = rnd.nextInt(sizeX);
        int y = rnd.nextInt(sizeY);
        Position pos = new Position(x, y);
        return pos;
    }

}
