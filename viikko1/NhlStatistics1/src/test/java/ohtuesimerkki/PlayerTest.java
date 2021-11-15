/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mlkul
 */
public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("Alex Lyon", "PHI", 1, 1); 
    }
    
    @Test
    public void konstruktoriLuoPelaajalleNimen() {
        assertEquals("Alex Lyon", player.getName());
    }
    
   

   
}
