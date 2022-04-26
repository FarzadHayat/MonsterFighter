package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameEnvironment;
import main.InvalidValueException;
import main.Score;

class ScoreTest {

    private GameEnvironment game;
    private Score scoreSystem;
   
    @BeforeEach
    void setUp() throws Exception {
    	game = new GameEnvironment();
    	scoreSystem = game.getScoreSystem();
    }

    @Test
    public void testAddBattlesWon() {
		scoreSystem.addBattlesWon();
		assertEquals(1, scoreSystem.getDayBattlesWon());
		assertEquals(1, scoreSystem.getTotalBattlesWon());
    }
   
    @Test
    public void testAddBattlesLost() {
		scoreSystem.addBattlesLost();
		assertEquals(1, scoreSystem.getDayBattlesLost());
		assertEquals(1, scoreSystem.getTotalBattlesLost());
    }
   
    @Test
    public void testResetDayBattles() {
		scoreSystem.addBattlesWon();
		scoreSystem.addBattlesLost();
		scoreSystem.resetDayBattles();
		assertEquals(1, scoreSystem.getTotalBattlesLost());
		assertEquals(1, scoreSystem.getTotalBattlesWon());
		assertEquals(0, scoreSystem.getDayBattlesLost());
		assertEquals(0, scoreSystem.getDayBattlesWon());
    }
   
    @Test
    public void testGetFinalScore() throws InvalidValueException {
		scoreSystem.setTotalBattlesWon(10);
		assertEquals(3000, scoreSystem.finalScore());
    }

}