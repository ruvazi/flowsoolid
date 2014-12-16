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

    private ArrayList<Position> posArray = new ArrayList<>();
    private ArrayList<Position> posArrayHit = new ArrayList<Position>(); //bliver slettet løbende
    private ArrayList<Position> posArrayHitSave = new ArrayList<Position>(); //gemmer alle positive for hele runden
    private ArrayList<Position> newCords = new ArrayList<Position>();       //array med nye cordinater til skud

    public int posCount = 0;

    public Torpedo_1() {
    }

    @Override           // leg her
    public void placeShips(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        int state = rnd.nextInt(4);

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
//    }
        switch (state) {
            case 0:
                Position pos1 = new Position(0, 4);
                Ship s1 = fleet.getShip(2);
                board.placeShip(pos1, s1, false);

                Position pos2 = new Position(0, 3);
                Ship s2 = fleet.getShip(3);
                board.placeShip(pos2, s2, false);

                Position pos3 = new Position(2, 9);
                Ship s3 = fleet.getShip(3);
                board.placeShip(pos3, s3, false);

                Position pos4 = new Position(0, 1);
                Ship s4 = fleet.getShip(4);
                board.placeShip(pos4, s4, false);

                Position pos5 = new Position(3, 0);
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

        Position pos;
        pos = new Position(nextX, nextY);
        posArray.add(pos);
        pos = getFiringPosition();

        return pos;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {

        if (hit) {

            posCount = 1;
            Position pos;
            pos = new Position(posArray.get(posArray.size() - 1).x, posArray.get(posArray.size() - 1).y);
            posArrayHit.add(pos);
        }
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
        Position pos;
        int x;
        int y;

        
            if (posCount > 0) {
//            System.out.println("hmm2");
                addToArrays();
                createNewCords();
                if (newCords.size() > 0) {
                    pos = newCords.get(0);
                    newCords.remove(0);
//                System.out.println(" postion i newCords x: " + newCords.get(0).x + " og y: " + newCords.get(0).y);
                    return pos;

                }
            }

            x = rnd.nextInt(sizeX);
            y = rnd.nextInt(sizeY);
            pos = new Position(x, y);
            System.out.println("x: " + x + "y: " + y);
            return pos;
        
    }
    

    

    public void addToArrays() {

        Position pos;

        int s = posArray.size();

        pos = posArray.get(s - 1);        //pos er lig med sidst tilføjet position

        posArrayHit.add(pos);

        posCount = 0;
    }

    public void createNewCords() {
        int x;
        int y;
        Position pos1;
        Position pos2;
        Position pos3;
        Position pos4;
//        System.out.println("hkkk2");

        if (posArrayHit.size() > 0) {
//            System.out.println("posArrayHit større end 0");
            y = posArrayHit.get(0).y;
            x = posArrayHit.get(0).x;
            System.out.println("x :" + x + " y: " + y);
            pos1 = new Position(x, y + 1);
            pos2 = new Position(x + 1, y);
            pos3 = new Position(x, y - 1);
            pos4 = new Position(x - 1, y);

            if (x < 1 && y > 0 && y < 9) {
                newCords.add(pos1);
                newCords.add(pos2);
                newCords.add(pos3);
            }

            if (x > 8 && y > 0 && y < 9) {
                newCords.add(pos1);
                newCords.add(pos3);
                newCords.add(pos4);
            }

            if (x < 9 && x > 0 && y > 0 && y < 9) {
                newCords.add(pos1);
                newCords.add(pos2);
                newCords.add(pos3);
                newCords.add(pos4);
            }

            if (x < 1 && y > 8) {
                newCords.add(pos2);
                newCords.add(pos3);
            }

            if (x > 8 && y > 8) {
                newCords.add(pos4);
                newCords.add(pos3);
            }

            if (x < 1 && y < 1) {
                newCords.add(pos1);
                newCords.add(pos2);
            }

            if (x > 8 && y < 1) {
                newCords.add(pos1);
                newCords.add(pos4);
            }

            if (x > 0 && x < 9 && y < 1) {
                newCords.add(pos4);
                newCords.add(pos1);
                newCords.add(pos2);
            }

            if (x > 0 && x < 9 && y > 8) {
                newCords.add(pos4);
                newCords.add(pos3);
                newCords.add(pos2);
            }
        }
    }
}
