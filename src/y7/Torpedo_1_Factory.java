/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package y7;

import battleship.examples.*;
import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;

/**
 *
 * @author Tobias Grundtvig
 */
public class Torpedo_1_Factory implements PlayerFactory<BattleshipsPlayer>
{
    private static int nextID = 1;
    private final int id;

    public Torpedo_1_Factory()
    {
        id = nextID++;
    }
    
    
    @Override
    public BattleshipsPlayer getNewInstance()
    {
        return new Torpedo_1();                  // vores player navn her
    }

    @Override
    public String getID()
    {
        return "RND:"+id;
    }

    @Override
    public String getName()
    {
        return "Torpedo_1 " + id;               //return vores player navn
    }
    
}
