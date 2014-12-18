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
    private ArrayList<Position> posArrayHit = new ArrayList<>(); //bliver slettet løbende
    private ArrayList<Position> posStartArray = new ArrayList<>(); //gemmer alle positive for hele runden
    private ArrayList<Position> newCords = new ArrayList<>();       //array med nye cordinater til skud
    private ArrayList<Position> ramtArray = new ArrayList<>();      // til at printe sucsesr ud
    private ArrayList<Position> nextHit = new ArrayList<>();        // hvis et skib er ramt 2 forsæt

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
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Position pos = new Position(i, j);
                posStartArray.add(pos);
//                System.out.println("pos i posStartArray: i,j"+i+" "+j );
            }

        }
    }

    @Override           // leg her
    public void placeShips(Fleet fleet, Board board) {
        final String WATER = null;
        final String SHIP = "X";

        sizeX = board.sizeX();
        sizeY = board.sizeY();
        String[][] shipPlacement = new String[sizeX][sizeY];
        boolean isShipHere = false;
        int state = rnd.nextInt(4) + 0;

        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {

            Ship s = fleet.getShip(i);
            boolean vertical;
            Position pos;

            do {
                vertical = rnd.nextBoolean();
                if (vertical) {
                    System.out.println("Generere skib " + s.size() + " vertikalt");
                    int x = rnd.nextInt(sizeX);
                    int y = rnd.nextInt(sizeY - (s.size() - 1));
                    for (i = 0; i < s.size(); i++) {
                        for (String[] a : shipPlacement) {
                            for (String b : a) {
                                if (b == SHIP) {
                                    System.out.println("Skib det samme sted " + x + "," + (y + i));
                                    isShipHere = true;
                                    
                                }

                            }

                        }
                        System.out.println("Sætter skib " + s.size() + " ind i 2d array");
                        shipPlacement[x][y + i] = "X";
                    }

                    pos = new Position(x, y);
                } else {
                    System.out.println("Generere skib " + s.size() + " horisontalt");
                    int x = rnd.nextInt(sizeX - (s.size() - 1));
                    int y = rnd.nextInt(sizeY);
                    for (i = 0; i < s.size(); i++) {
                        for (String[] a : shipPlacement) {
                            for (String b : a) {
                                if (b == SHIP) {
                                    System.out.println("Skib det samme sted " + (x + i) + "," + y);
                                    isShipHere = true;
                                    
                                }

                            }

                        }
                        System.out.println("Sætter skib " + s.size() + " ind i 2d array");
                        shipPlacement[x + i][y] = "X";
                    }

                    pos = new Position(x, y);
                }
            } while (!isShipHere);

            board.placeShip(pos, s, vertical);
        }

//        Position pos16 = new Position(8, 3);
//        Ship s16 = fleet.getShip(0);
//        board.placeShip(pos16, s16, false);
//        for (int i = 0; i < s16.size(); i++) {
//            shipPlacement[8][3 + i] = "XX";
//        }
        
        Position pos16 = new Position(8, 3);
        Ship s16 = fleet.getShip(0);
        board.placeShip(pos16, s16, false);
        for (int i = 0; i < s16.size(); i++) {
            shipPlacement[8][3 + i] = "-";
        }

        Position pos17 = new Position(2, 8);
        Ship s17 = fleet.getShip(1);
        board.placeShip(pos17, s17, true);
        for (int i = 0; i < s17.size(); i++) {
            shipPlacement[2 + i][8] = "-";
        }

        Position pos18 = new Position(0, 4);
        Ship s18 = fleet.getShip(2);
        board.placeShip(pos18, s18, false);
        for (int i = 0; i < s18.size(); i++) {
            shipPlacement[0][4 + i] = "-";
        }

        Position pos19 = new Position(4, 4);
        Ship s19 = fleet.getShip(3);
        board.placeShip(pos19, s19, false);
        for (int i = 0; i < s19.size(); i++) {
            shipPlacement[4][4 + i] = "-";
        }

        Position pos20 = new Position(3, 1);
        Ship s20 = fleet.getShip(4);
        board.placeShip(pos20, s20, true);
        for (int i = 0; i < s20.size(); i++) {
            shipPlacement[3 + i][1] = "-";
        }
        
//        Position pos1 = new Position(4, 0);
//        Ship s1 = fleet.getShip(0);
//        board.placeShip(pos1, s1, false);
//        for (int i = 0; i < s1.size(); i++) {
//            shipPlacement[4][0 + i] = "XX";
//        }
//
//        Position pos2 = new Position(3, 0);
//        Ship s2 = fleet.getShip(1);
//        board.placeShip(pos2, s2, false);
//        for (int i = 0; i < s2.size(); i++) {
//            shipPlacement[3][0 + i] = "XXX";
//        }
//
//        Position pos3 = new Position(2, 0);
//        Ship s3 = fleet.getShip(2);
//        board.placeShip(pos3, s3, false);
//        for (int i = 0; i < s3.size(); i++) {
//            shipPlacement[2][0 + i] = "XXX";
//        }
//
//        Position pos4 = new Position(1, 0);
//        Ship s4 = fleet.getShip(3);
//        board.placeShip(pos4, s4, false);
//        for (int i = 0; i < s4.size(); i++) {
//            shipPlacement[1][0 + i] = "XXXX";
//        }
//
//        Position pos5 = new Position(0, 0);
//        Ship s5 = fleet.getShip(4);
//        board.placeShip(pos5, s5, false);
//        for (int i = 0; i < s5.size(); i++) {
//            shipPlacement[0][0 + i] = "XXXXX";
//        }
        // System.out.println("Starter print af 2d array");
        for (String[] a : shipPlacement) {
            for (String b : a) {
                System.out.print(b + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("-----------------------------------------------------------------------------");

        /**
         * Disse harcodede positioner kan man ikke regne med, da de er baseret
         * på et koordinat system
         */
//        switch (state) {
//            case 0:
//                Position pos1 = new Position(4, 0);
//                Ship s1 = fleet.getShip(0);
//                board.placeShip(pos1, s1, false);
//
//                Position pos2 = new Position(3, 0);
//                Ship s2 = fleet.getShip(1);
//                board.placeShip(pos2, s2, false);
//
//                Position pos3 = new Position(2, 0);
//                Ship s3 = fleet.getShip(2);
//                board.placeShip(pos3, s3, false);
//
//                Position pos4 = new Position(1, 0);
//                Ship s4 = fleet.getShip(3);
//                board.placeShip(pos4, s4, false);
//
//                Position pos5 = new Position(0, 0);
//                Ship s5 = fleet.getShip(4);
//                board.placeShip(pos5, s5, false);
//                break;
//            case 1:
//                Position pos6 = new Position(1, 3);
//                Ship s6 = fleet.getShip(0);
//                board.placeShip(pos6, s6, true);
//
//                Position pos7 = new Position(0, 0);
//                Ship s7 = fleet.getShip(1);
//                board.placeShip(pos7, s7, false);
//
//                Position pos8 = new Position(2, 0);
//                Ship s8 = fleet.getShip(2);
//                board.placeShip(pos8, s8, false);
//
//                Position pos9 = new Position(6, 6);
//                Ship s9 = fleet.getShip(3);
//                board.placeShip(pos9, s9, true);
//
//                Position pos10 = new Position(9, 0);
//                Ship s10 = fleet.getShip(4);
//                board.placeShip(pos10, s10, false);
//                break;
//            case 2:
//                Position pos11 = new Position(1, 3);
//                Ship s11 = fleet.getShip(0);
//                board.placeShip(pos11, s11, false);
//
//                Position pos12 = new Position(7, 3);
//                Ship s12 = fleet.getShip(1);
//                board.placeShip(pos12, s12, true);
//
//                Position pos13 = new Position(6, 9);
//                Ship s13 = fleet.getShip(2);
//                board.placeShip(pos13, s13, false);
//
//                Position pos14 = new Position(4, 4);
//                Ship s14 = fleet.getShip(3);
//                board.placeShip(pos14, s14, false);
//
//                Position pos15 = new Position(3, 5);
//                Ship s15 = fleet.getShip(4);
//                board.placeShip(pos15, s15, true);
//                break;
//            case 3:
//                Position pos16 = new Position(9, 3);
//                Ship s16 = fleet.getShip(0);
//                board.placeShip(pos16, s16, false);
//
//                Position pos17 = new Position(2, 8);
//                Ship s17 = fleet.getShip(1);
//                board.placeShip(pos17, s17, true);
//
//                Position pos18 = new Position(0, 4);
//                Ship s18 = fleet.getShip(2);
//                board.placeShip(pos18, s18, false);
//
//                Position pos19 = new Position(4, 4);
//                Ship s19 = fleet.getShip(3);
//                board.placeShip(pos19, s19, false);
//
//                Position pos20 = new Position(3, 5);
//                Ship s20 = fleet.getShip(4);
//                board.placeShip(pos20, s20, true);
//                break;
//        }
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
//        System.out.println("tur nr: " + posArray.size() + "| x-y: " + posArray.get(posArray.size() - 1).x + " " + posArray.get(posArray.size() - 1).y);

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
//            System.out.println("ramt!");
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
//       printRamtArray();
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

    private Position getFiringPosition() {
        Position pos;
        int x;  //random fra posStartArray

        if (posCount > 0) {
//            System.out.println("hmm2");
            posCount = 0;
            createNewCords();
        }
        
        if (nextHit.size()>1)
            
        
        if (newCords.size() > 0) {
//            System.out.println("newCords size(): " + newCords.size());
//            printNewCord();
            pos = newCords.get(0);
//            System.out.println("newcords før check: x-y: "+ newCords.get(0).x +" "+newCords.get(0).y);
            for (int i = newCords.size() - 1; i > -1; i--) {
//                System.out.println("newCords");
                checknewCordInposArray(pos);
//                System.out.println("newcords i lykken: x-y: " + newCords.get(0).x + " " + newCords.get(0).y);

            }
//            System.out.println("newcords efter check: x-y: "+ newCords.get(0).x +" "+newCords.get(0).y);
            pos = newCords.get(0);
//            System.out.println("newcords efter ny pej 0: x-y: " + newCords.get(0).x + " " + newCords.get(0).y);
            for (int i = posStartArray.size() - 1; i > -1; i--) {
                if (pos.x == posStartArray.get(i).x && pos.y == posStartArray.get(i).y) {
                    posStartArray.remove(i);
                    break;
                }
            }
            newCords.remove(0);
//                System.out.println(" postion i newCords x: " + newCords.get(0).x + " og y: " + newCords.get(0).y);
            return pos;

        }

        x = rnd.nextInt(posStartArray.size());
        pos = posStartArray.get(x);
        posStartArray.remove(x);

        return pos;

    }

    public void checknewCordInposArray(Position pos) {
//        System.out.println(" checknewC...");
        for (int i = posArray.size() - 1; i > -1; i--) {
            if (pos.x == posArray.get(i).x && pos.y == posArray.get(i).y) {
                newCords.remove(0);
//                printNewCord();
                break;
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
//        System.out.println("hkkk2");

        if (posArrayHit.size() > 0) {
//            System.out.println("posArrayHit større end 0");
            y = posArrayHit.get(0).y;
            x = posArrayHit.get(0).x;
            posArrayHit.clear();
//            System.out.println("x :" + x + " y: " + y);
            pos1 = new Position(x, y - 1);
            pos2 = new Position(x + 1, y);
            pos3 = new Position(x, y + 1);
            pos4 = new Position(x - 1, y);

//            System.out.println("newcords pos1: " + pos1.x + " " + pos1.y);
//            System.out.println("newcords pos2: " + pos2.x + " " + pos2.y);
//            System.out.println("newcords pos3: " + pos3.x + " " + pos3.y);
//            System.out.println("newcords pos4: " + pos4.x + " " + pos4.y);
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
}
