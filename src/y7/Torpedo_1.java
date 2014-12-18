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
 * @author RVZ
 */
public class Torpedo_1 implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int nextX;
    private int nextY;

    private ArrayList<Position> posArray = new ArrayList<>();
    private ArrayList<Position> posArrayHit = new ArrayList<>();        //bliver slettet løbende
    private ArrayList<Position> posStartArray = new ArrayList<>();      //gemmer alle positive for hele runden
    private ArrayList<Position> newCords = new ArrayList<>();           //array med nye cordinater til skud
    private ArrayList<Position> ramtArray = new ArrayList<>();          // til at printe sucsesr ud
    private ArrayList<Position> nextHit = new ArrayList<>();            // hvis et skib er ramt 2 forsæt
    private ArrayList<Position> Hit = new ArrayList<>();
    private ArrayList<Position> shipPlacement = new ArrayList<>();      //bruges til at finde pladser til skibe
    private ArrayList<Position> shipNewPlacement = new ArrayList<>();   // bruges til at gemme nye skibs positioner
    private ArrayList<Position> shootArray = new ArrayList<>();         // der vi skyder

    public int posCount = 0;

    public Torpedo_1() {

    }

    public void fillPosStartArray() {
        posCount = 0;
        posArray.clear();
        posArray.clear();
        posStartArray.clear();
        newCords.clear();
        ramtArray.clear();
        nextHit.clear();
        shootArray.clear();
        shipPlacement.clear();
        shipNewPlacement.clear();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Position pos = new Position(i, j);
                posStartArray.add(pos);
                shipPlacement.add(pos);
            }
        }

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                int y = i * 10;
                shootArray.add(posStartArray.get(y));
                y = i * 10 + 2;
                shootArray.add(posStartArray.get(y));
                y = i * 10 + 4;
                shootArray.add(posStartArray.get(y));
                y = i * 10 + 6;
                shootArray.add(posStartArray.get(y));
                y = i * 10 + 8;
                shootArray.add(posStartArray.get(y));
            }
            if (i % 2 != 0) {
                shootArray.add(posStartArray.get(i * 10 + 1));
                shootArray.add(posStartArray.get(i * 10 + 3));
                shootArray.add(posStartArray.get(i * 10 + 5));
                shootArray.add(posStartArray.get(i * 10 + 7));
                shootArray.add(posStartArray.get(i * 10 + 9));
            }
        }

    }

    @Override           // leg her
    public void placeShips(Fleet fleet, Board board) {
//        nextX = 0;
//        nextY = 0;
//        sizeX = board.sizeX();
//        sizeY = board.sizeY();
//        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
//            Ship s = fleet.getShip(i);
//            boolean vertical = rnd.nextBoolean();
//            Position pos;
//            if (vertical) {
//                int x = rnd.nextInt(sizeX);
//                int y = rnd.nextInt(sizeY - (s.size() - 1));
//                pos = new Position(x, y);
//            } else {
//                int x = rnd.nextInt(sizeX - (s.size() - 1));
//                int y = rnd.nextInt(sizeY);
//                pos = new Position(x, y);
//            }
//            board.placeShip(pos, s, vertical);
//        }

        sizeX = board.sizeX();
        sizeY = board.sizeY();
        String[][] shipPlacementVisual = new String[sizeX][sizeY];
        for (int i = 0; i < fleet.getNumberOfShips(); i++) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;

            pos = getShipPosition(s, vertical, shipPlacementVisual);

            board.placeShip(pos, s, vertical);
        }

//        print2DArray(shipPlacementVisual);
    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {

        Position pos;
        pos = getFiringPosition();

        posArray.add(pos);
        System.out.println("tur nr: " + posArray.size() + "| x-y: " + posArray.get(posArray.size() - 1).x + " " + posArray.get(posArray.size() - 1).y);

        return pos;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {

        if (hit == true) {

            posCount = 1;
            Position pos;
            pos = new Position(posArray.get(posArray.size() - 1).x, posArray.get(posArray.size() - 1).y);
            posArrayHit.add(pos);
            ramtArray.add(pos);
            nextHit.add(pos);
            System.out.println("ramt!");
            createNewCords();

        }
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
        fillPosStartArray();
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        printRamtArray();
        printNewCord();
        printShootArray();
    }

    @Override
    public void endMatch(int won, int lost, int draw) {

    }

    public void printRamtArray() {
        for (int i = 0; i < ramtArray.size(); i++) {
            System.out.println("ramtArray: plads " + i + " : x-y: " + ramtArray.get(i).x + " " + ramtArray.get(i).y);
        }
    }

    public void printNewCord() {
        for (int i = 0; i < newCords.size(); i++) {
            System.out.println("newcord: plads " + i + " : x-y: " + newCords.get(i).x + " " + newCords.get(i).y);

        }
    }

    public void printShootArray() {
        for (int i = 0; i < shootArray.size(); i++) {
            System.out.println("shootArray: plads: " + i + " x-y: " + shootArray.get(i).x + " " + shootArray.get(i).y);
        }
    }

    private Position getFiringPosition() {
        Position pos;
        int x;

        if (Hit.size() > 0) {
            pos = new Position(Hit.get(0).x, Hit.get(0).y);
//            System.out.println("\n" + "Hit: plads " + 0 + " : x-y: " + Hit.get(0).x + " " + Hit.get(0).y);
            Hit.remove(0);
            checkHitInposArray();
            if (Hit.isEmpty()) {
                nextHit.clear();
                getFiringPosition();
            }
            checkAllArrayInHit(pos);

            return pos;
        }

        if (nextHit.size() > 1) {
            createNextHit();
            newCords.clear();
            getFiringPosition();
        }

        if (newCords.size() > 0) {

            pos = newCords.get(0);

            for (int i = newCords.size() - 1; i > -1; i--) {
                checknewCordInposArray(pos);
            }

            pos = newCords.get(0);
            checkAllArraysInNewCords(pos);
            newCords.remove(0);
            return pos;

        }
        nextHit.clear();
        Hit.clear();
        if (!shootArray.isEmpty()) {
            int p = 0;
            pos = shootArray.get(p);
            checkshootArrayInPosArray(pos, p);
            shootArray.remove(p);

            return pos;
        }
        x = posStartArray.size() - 1;
        pos = posStartArray.get(x);
        posStartArray.remove(x);

        return pos;

    }

    public void checkAllArraysInNewCords(Position pos) {
        for (int i = posStartArray.size() - 1; i > -1; i--) {
            if (pos.x == posStartArray.get(i).x && pos.y == posStartArray.get(i).y) {
                posStartArray.remove(i);
                break;
            }
        }

        for (int i = shootArray.size() - 1; i > -1; i--) {
            if (pos.x == shootArray.get(i).x && pos.y == shootArray.get(i).y) {
                shootArray.remove(i);
                break;
            }
        }
    }

    public void checknewCordInposArray(Position pos) {
        for (int i = posArray.size() - 1; i > -1; i--) {
            if (pos.x == posArray.get(i).x && pos.y == posArray.get(i).y) {
                newCords.remove(0);
                break;
            }

        }
    }

    public void checkHitInposArray() {
        for (int j = Hit.size() - 1; j > -1; j--) {

            for (int i = posArray.size() - 1; i > -1; i--) {
                if (Hit.get(j).x == posArray.get(i).x && Hit.get(j).y == posArray.get(i).y) {
                    Hit.remove(j);
                    break;
                }

            }

        }
    }

    public void checkAllArrayInHit(Position pos) {
        for (int i = posStartArray.size() - 1; i > -1; i--) {
            if (pos.x == posStartArray.get(i).x && pos.y == posStartArray.get(i).y) {
                posStartArray.remove(i);
                break;
            }
        }
        for (int i = shootArray.size() - 1; i > -1; i--) {
            if (pos.x == shootArray.get(i).x && pos.y == shootArray.get(i).y) {
                shootArray.remove(i);
                break;
            }
        }

    }

    public void checkshootArrayInPosArray(Position pos, int x) {
        for (int i = posArray.size() - 1; i > -1; i--) {
            if (pos.x == posArray.get(i).x && pos.y == posArray.get(i).y) {
                shootArray.remove(x);
                break;
            }
        }
        getFiringPosition();
    }

    public void createNextHit() {
        int x0, x1, x2, x3;
        int y0, y1, y2, y3;
        Position pos1, pos2;

        x0 = nextHit.get(0).x;
        y0 = nextHit.get(0).y;
        x1 = nextHit.get(1).x;
        y1 = nextHit.get(1).y;
//        newCords.clear();

        if (x0 == x1) {
            if (y0 > y1) {
                pos1 = new Position(x0, y0 + 1);
                pos2 = new Position(x0, y1 - 1);

                if (y1 == 0) {
                    Hit.add(pos1);
                    nextHit.clear();
                }
                if (y0 == 9) {
                    Hit.add(pos2);
                    nextHit.clear();
                }
                if (y1 > 0 && y0 < 9) {
                    Hit.add(pos1);
                    Hit.add(pos2);
                    nextHit.clear();
                }

            } else {
                pos1 = new Position(x0, y0 - 1);
                pos2 = new Position(x0, y1 + 1);

                if (y0 == 0) {
                    Hit.add(pos2);
                    nextHit.clear();
                }
                if (y1 == 9) {
                    Hit.add(pos1);
                    nextHit.clear();
                }
                if (y0 > 0 && y1 < 9) {
                    Hit.add(pos1);
                    Hit.add(pos2);
                    nextHit.clear();
                }
            }
        }

        if (y0 == y1) {
            if (x0 > x1) {
                pos1 = new Position(x0 + 1, y0);
                pos2 = new Position(x1 - 1, y0);

                if (x1 == 0) {
                    Hit.add(pos1);
                    nextHit.clear();
                }
                if (x0 == 9) {
                    Hit.add(pos2);
                    nextHit.clear();
                }
                if (x1 > 0 && x0 < 9) {
                    Hit.add(pos1);
                    Hit.add(pos2);
                    nextHit.clear();
                }

            } else {
                pos1 = new Position(x0 - 1, y0);
                pos2 = new Position(x1 + 1, y0);

                if (x0 == 0) {
                    Hit.add(pos2);
                    nextHit.clear();
                }
                if (x1 == 9) {
                    Hit.add(pos1);
                    nextHit.clear();
                }
                if (x0 > 0 && x1 < 9) {
                    Hit.add(pos1);
                    Hit.add(pos2);
                    nextHit.clear();
                }
            }
        }

    }

    public void createNewCords() {
        int x;
        int y;
        Position pos1;
        Position pos2;
        Position pos3;
        Position pos4;

        if (posArrayHit.size() > 0) {

            y = posArrayHit.get(0).y;
            x = posArrayHit.get(0).x;
            posArrayHit.clear();

            pos1 = new Position(x, y - 1);
            pos2 = new Position(x + 1, y);
            pos3 = new Position(x, y + 1);
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
                newCords.add(pos1);
                newCords.add(pos2);
            }

            if (x > 8 && y > 8) {
                newCords.add(pos1);
                newCords.add(pos4);
            }

            if (x < 1 && y < 1) {
                newCords.add(pos2);
                newCords.add(pos3);
            }

            if (x > 8 && y < 1) {
                newCords.add(pos3);
                newCords.add(pos4);
            }

            if (x > 0 && x < 9 && y < 1) {
                newCords.add(pos2);
                newCords.add(pos3);
                newCords.add(pos4);
            }

            if (x > 0 && x < 9 && y > 8) {
                newCords.add(pos1);
                newCords.add(pos2);
                newCords.add(pos4);
            }
        }
    }

    public Position getShipPosition(Ship s, Boolean vertical, String[][] shipPlacementVisual) {

        Position pos1, pos2, pos3, pos4, pos5;

        int random = rnd.nextInt(shipPlacement.size());

        if (vertical) {

            pos1 = shipPlacement.get(random);
            int x = pos1.x;
            int y = pos1.y;

            pos2 = new Position(x, y + 1);
            pos3 = new Position(x, y + 2);
            pos4 = new Position(x, y + 4);
            pos5 = new Position(x, y + 5);

            switch (s.size()) {
                case 2:
                    if (y == 0) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                    }
                    break;
                case 3:
                    if (y <= 1) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                        shipNewPlacement.add(pos3);
                    }
                    break;
                case 4:
                    if (y <= 2) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                        shipNewPlacement.add(pos3);
                        shipNewPlacement.add(pos4);
                    }
                    break;
                case 5:
                    if (y <= 3) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                        shipNewPlacement.add(pos3);
                        shipNewPlacement.add(pos4);
                        shipNewPlacement.add(pos5);
                    }
                    break;

            }

        } else {

            pos1 = shipPlacement.get(random);
            int x = pos1.x;
            int y = pos1.y;

            pos2 = new Position(x + 1, y);
            pos3 = new Position(x + 2, y);
            pos4 = new Position(x + 3, y);
            pos5 = new Position(x + 4, y);

            switch (s.size()) {
                case 2:
                    if (x == 9) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                    }
                    break;
                case 3:
                    if (x >= 8) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                        shipNewPlacement.add(pos3);
                    }
                    break;
                case 4:
                    if (x >= 7) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                        shipNewPlacement.add(pos3);
                        shipNewPlacement.add(pos4);
                    }
                    break;
                case 5:
                    if (x >= 6) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else if (checkShipPlacement(pos1) == true || checkShipPlacement(pos2) == true || checkShipPlacement(pos3) == true || checkShipPlacement(pos4) == true || checkShipPlacement(pos5)) {
                        getShipPosition(s, vertical, shipPlacementVisual);
                    } else {
                        shipNewPlacement.add(pos1);
                        shipNewPlacement.add(pos2);
                        shipNewPlacement.add(pos3);
                        shipNewPlacement.add(pos4);
                        shipNewPlacement.add(pos5);
                    }
                    break;

            }

        }

        printPlacement(pos1, s, vertical, shipPlacementVisual);
        return pos1;
    }

    public boolean checkShipPlacement(Position pos) {

        for (Position shipNewPlacementStr : shipNewPlacement) {
            return pos == shipNewPlacementStr;
        }

        return false;
    }

    public void printPlacement(Position pos, Ship s, Boolean vertical, String[][] shipPlacementVisual) {
        int x = pos.x;
        int y = pos.y;
        for (int i = 0; i < s.size(); i++) {

            if (vertical) {
                shipPlacementVisual[x][y + i] = "" + s.size();
            } else {
                shipPlacementVisual[x + i][y] = "" + s.size();
            }
        }
    }

    public void print2DArray(String[][] shipPlacementVisual) {
        for (String[] a : shipPlacementVisual) {
            for (String b : a) {
                System.out.print(b + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("-----------------------------------------------------------------------------");
    }
}
