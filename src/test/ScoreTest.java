package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidValueException;
import main.GameEnvironment;
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
    public void testAddBattlesWon1() {
    	//Won 1 battle
		scoreSystem.addBattlesWon();
		assertEquals(1, scoreSystem.getDayBattlesWon());
		assertEquals(1, scoreSystem.getTotalBattlesWon());
    }
    
    @Test
    public void testAddBattlesWon2() {
    	//Won 2 battles
		scoreSystem.addBattlesWon();
		scoreSystem.addBattlesWon();
		assertEquals(2, scoreSystem.getDayBattlesWon());
		assertEquals(2, scoreSystem.getTotalBattlesWon());
    }
   
    @Test
    public void testAddBattlesLost1() {
    	//Lost 1 battle
		scoreSystem.addBattlesLost();
		assertEquals(1, scoreSystem.getDayBattlesLost());
		assertEquals(1, scoreSystem.getTotalBattlesLost());
    }
    
    @Test
    public void testAddBattlesLost2() {
    	//Lost 2 battles
		scoreSystem.addBattlesLost();
		scoreSystem.addBattlesLost();
		assertEquals(2, scoreSystem.getDayBattlesLost());
		assertEquals(2, scoreSystem.getTotalBattlesLost());
    }
   
    @Test
    public void testResetDayBattles() {
    	//Reset daily battles but keep total battles 
		scoreSystem.addBattlesWon();
		scoreSystem.addBattlesLost();
		scoreSystem.resetDayBattles();
		assertEquals(1, scoreSystem.getTotalBattlesLost());
		assertEquals(1, scoreSystem.getTotalBattlesWon());
		assertEquals(0, scoreSystem.getDayBattlesLost());
		assertEquals(0, scoreSystem.getDayBattlesWon());
    }
    
    @Test
    public void testScoreBonus() {
    	//Calculate bonus from 10 battles won and balance of 100
    	scoreSystem.setTotalBattlesWon(10);
    	assertEquals(2000, scoreSystem.scoreBonus());
    }
   
    @Test
    public void testGetFinalScore() throws InvalidValueException {
    	//Calculate final score plus bonuses
    	//10 battles won and player balance of 100
		scoreSystem.setTotalBattlesWon(10);
		assertEquals(3000, scoreSystem.finalScore());
    }
    
    @Test
    public void testAddScore1() throws InvalidValueException {
    	scoreSystem.addScore(1000);
    	assertEquals(1000, scoreSystem.getDayScore());
    	assertEquals(2000, scoreSystem.getTotalScore());
    }
    
    @Test
    public void testAddScore2() {
    	try {
			scoreSystem.addScore(-1);
		} catch (InvalidValueException e) {
			assertEquals("Cannot be a negative value!", e.getMessage());
		}
    	assertEquals(0, scoreSystem.getDayScore());
    	assertEquals(1000, scoreSystem.getTotalScore());
    }

}