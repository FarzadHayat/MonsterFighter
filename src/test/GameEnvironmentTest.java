package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class GameEnvironmentTest {

	private GameEnvironment game;
	private Player player;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		player = game.getPlayer();
	}

	/**
	 * Getters and setters
	 * 
	 */

	
	@Test
	void testSetNumDays() throws InvalidValueException {
		game.setNumDays(5);
		assertEquals(5, game.getNumDays());
		
		game.setNumDays(15);
		assertEquals(15, game.getNumDays());
		
		int[] testNumDays = {4, 0, 16, -5, 100};
		for (int numDay : testNumDays) {			
			try {			
				game.setNumDays(numDay);
			}
			catch (InvalidValueException e){
				assertEquals(e.getMessage(), "Invalid number of days! Try again:");
			}
		}
	}

	
	@Test
	void testSetDifficulty() {
		game.setDifficulty(Difficulty.EASY);
		assertEquals(game.getDifficulty(), Difficulty.EASY);
		
		game.setDifficulty(Difficulty.NORMAL);
		assertEquals(game.getDifficulty(), Difficulty.NORMAL);
		
		game.setDifficulty(Difficulty.HARD);
		assertEquals(game.getDifficulty(), Difficulty.HARD);
	}

	
	/**
	 * Functional
	 * 
	 */
	
	@Test
	void testSleep() {
//		fail("Not yet implemented");
	}

	
	@Test
	void testCheckStatus1() throws InvalidValueException {
		game.setNumDays(10);
		game.checkStatus();
		assertFalse(game.getIsFinished());
		
		game.setDay(10);
		game.checkStatus();
		assertTrue(game.getIsFinished());
		
		game.setDay(9);
		game.checkStatus();
		assertFalse(game.getIsFinished());
		
		game.setDay(11);
		game.checkStatus();
		assertTrue(game.getIsFinished());
	}
	
	
	@Test
	void testcheckStatus2() throws InvalidValueException, InventoryFullException {
		game.setNumDays(10);
		player.setBalance(10);
		game.checkStatus();
		assertTrue(game.getIsFinished());
		
		player.getMonsters().add(new Chunky(game));
		game.checkStatus();
		assertFalse(game.getIsFinished());
	}
	
	
	@Test
	void testcheckStatus3() throws InvalidValueException, InventoryFullException {
		game.setNumDays(10);
		player.setBalance(10);
		player.getItems().add(new LevelUp(game));
		game.checkStatus();
		assertTrue(game.getIsFinished());
	}

}
