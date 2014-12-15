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
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;
            if (vertical) {
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                int x = rnd.nextInt(sizeX - (s.size() - 1));
                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);
            }
            board.placeShip(pos, s, vertical);
        }
//        Position pos1 = new Position(1,1);
//        Ship s1 = new Ship(2);
//        board.placeShip(pos1, s1, true);
//        
//        Position pos2 = new Position(1,1);
//        Ship s2 = new Ship(3);
//        board.placeShip(pos1, s1, true);
//        
//        Position pos3 = new Position(1,1);
//        Ship s3 = new Ship(3);
//        board.placeShip(pos1, s1, true);
//        
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
