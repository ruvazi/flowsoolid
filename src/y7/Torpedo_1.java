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

    private ArrayList<Position> posArray = new ArrayList<Position>();
    private ArrayList<Position> posArrayHit = new ArrayList<Position>(); //bliver slettet løbende
    private ArrayList<Position> posArrayHitSave = new ArrayList<Position>(); //gemmer alle positive for hele runden
    
    int posCount= 0;
    
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
    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {

        Position pos = getFiringPosition();
        posArray.add(pos);

        while (true) {
            for (Position posObject : posArray) {
                if (posObject == pos) {
                    pos = getFiringPosition();
                    System.out.println(pos);
                    break;
                }
            }
            System.out.println(pos);
            return pos;
        }

    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        
        if(hit){
        
        posCount++;
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
        
//        for (int i = 0; i < 1; i++) {
        if (posCount>0) {
            pos = posArray.get(posArray.size());
            posArrayHit.add(pos);
            posArrayHitSave.add(pos);
            posCount--;
        }   
        
        int x = rnd.nextInt(sizeX);
        int y = rnd.nextInt(sizeY);

        pos = new Position(x, y);
            
        

        

        return pos;
    }
}
