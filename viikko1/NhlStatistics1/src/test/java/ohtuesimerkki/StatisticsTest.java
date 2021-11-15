/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
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
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void playerCanBeFoundByName() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void teamHasRightNumberOfPlayers() {
        assertEquals(3, stats.team("EDM").size());
    }
  
    @Test
    public void teamPlayersAreCorrect() {
        assertEquals("Yzerman", stats.team("DET").get(0).getName());
    }
    
    @Test
    public void topScorersGivesTheBestScorer() {
        assertEquals("Gretzky", stats.topScorers(1));
    }
}
