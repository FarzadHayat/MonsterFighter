package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidValueException;
import main.GameEnvironment;
import main.Score;

/**
 * Unit test for Score class
 * @author Farzad and Daniel
 */

class ScoreTest {

	/**
	 * Fields
	 */
    private GameEnvironment game;
    private Score scoreSystem;
   
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
    @BeforeEach
    void setUp() throws Exception {
    	game = new GameEnvironment();
    	scoreSystem = game.getScoreSystem();
    }

    /**
     * Add 1 won battle to score system 
     * @result 1 won battle is added to daily and total won 
     */
    @Test
    public void testAddBattlesWon1() {
    	//Won 1 battle
		scoreSystem.addBattlesWon();
		assertEquals(1, scoreSystem.getDayBattlesWon());
		assertEquals(1, scoreSystem.getTotalBattlesWon());
    }
    
    /**
     * Add 2 won battles to score system 
     * @result 2 won battles is added to daily and total won 
     */
    @Test
    public void testAddBattlesWon2() {
    	//Won 2 battles
		scoreSystem.addBattlesWon();
		scoreSystem.addBattlesWon();
		assertEquals(2, scoreSystem.getDayBattlesWon());
		assertEquals(2, scoreSystem.getTotalBattlesWon());
    }
   
    /**
     * Add 1 lost battle to score system 
     * @result 1 lost battle is added to daily and total lost 
     */
    @Test
    public void testAddBattlesLost1() {
    	//Lost 1 battle
		scoreSystem.addBattlesLost();
		assertEquals(1, scoreSystem.getDayBattlesLost());
		assertEquals(1, scoreSystem.getTotalBattlesLost());
    }
    
    /**
     * Add 2 lost battles to score system 
     * @result 2 lost battles is added to daily and total lost 
     */
    @Test
    public void testAddBattlesLost2() {
    	//Lost 2 battles
		scoreSystem.addBattlesLost();
		scoreSystem.addBattlesLost();
		assertEquals(2, scoreSystem.getDayBattlesLost());
		assertEquals(2, scoreSystem.getTotalBattlesLost());
    }
   
    /**
     * Reset daily won and lost battles 
     * @result daily battles reset without any errors, total still remains 
     */
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
    
    /**
     * Calculate bonus from battles won
     * @result bonus is correctly calculated without any errors 
     */
    @Test
    public void testScoreBonus() {
    	//Calculate bonus from 10 battles won and balance of 100
    	scoreSystem.setTotalBattlesWon(10);
    	assertEquals(2000, scoreSystem.scoreBonus());
    }
   
    /**
     * Calculate final score with bonus 
     * @result correct score with bonus is returned without any errors 
     */
    @Test
    public void testGetFinalScore() {
    	//Calculate final score plus bonuses
    	//10 battles won and player balance of 100
		scoreSystem.setTotalBattlesWon(10);
		assertEquals(3000, scoreSystem.finalScore());
    }
    
    /**
     * Add to player's score 
     * @result amount is added to player's score without any errors 
     * @throws InvalidValueException if given value is invalid 
     */
    @Test
    public void testAddScore1() throws InvalidValueException {
    	scoreSystem.addScore(1000);
    	assertEquals(1000, scoreSystem.getDayScore());
    	assertEquals(2000, scoreSystem.getTotalScore());
    }
    
    /**
     * Add negative value to player's score
     * @result exception is thrown
     * @throws InvalidValueException if given value is invalid 
     */
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