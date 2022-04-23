package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class GameEnvironmentTest {

	private GameEnvironment game;

	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	/**
	 * Getters and setters
	 * 
	 */

	@Test
	void testSetBalance() throws InvalidValueException {
		game.setBalance(0);
		assertEquals(0, game.getBalance());
		
		game.setBalance(100);
		assertEquals(100, game.getBalance());
		
		game.setBalance(5.5);
		assertEquals(5.5, game.getBalance());
		
		try {
			game.setBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Balance cannot be a negative value!");
		}
		
		try {
			game.setBalance(-0.5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Balance cannot be a negative value!");
		}
	}

	
	@Test
	void testSetPlayerName() throws InvalidValueException {
		game.setPlayerName("max");
		assertEquals("max", game.getPlayerName());
		
		game.setPlayerName("ABC   Abc   abc");
		assertEquals("ABC   Abc   abc", game.getPlayerName());
		
		game.setPlayerName("  whitespace               ");
		assertEquals("whitespace", game.getPlayerName());
		
		String[] testNames = {"aj", "", "Abc   Abc   AbcZ", "123", "Mikey5x", "!WoW", "oops_"};
		for (String name : testNames) {			
			try {			
				game.setPlayerName(name);
			}
			catch (InvalidValueException e){
				assertEquals(e.getMessage(), "Invalid player name! Try again:");
			}
		}
	}

	
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
		game.setBalance(10);
		game.checkStatus();
		assertTrue(game.getIsFinished());
		
		game.getMyMonsters().add(new Chunky(game));
		game.checkStatus();
		assertFalse(game.getIsFinished());
	}
	
	
	@Test
	void testcheckStatus3() throws InvalidValueException, InventoryFullException {
		game.setNumDays(10);
		game.setBalance(10);
		game.getMyItems().add(new LevelUp(game));
		game.checkStatus();
		assertTrue(game.getIsFinished());
	}
	

	@Test
	void testAddBalance() throws InvalidValueException {
		game.setBalance(0);
		game.addBalance(10);
		assertEquals(game.getBalance(), 10);
		game.addBalance(50);
		assertEquals(game.getBalance(), 60);
		game.addBalance(0);
		assertEquals(game.getBalance(), 60);
		
		try {
			game.addBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Cannot be a negative value!");
		}
	}
	

	@Test
	void testMinusBalance() throws InsufficientFundsException, InvalidValueException {
		game.minusBalance(10);
		assertEquals(game.getBalance(), 90);
		game.minusBalance(90);
		assertEquals(game.getBalance(), 0);
		
		try {
			game.setBalance(10);
			game.minusBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Cannot be a negative value!");
		}
		
		try {
			game.minusBalance(20);
		}
		catch (InsufficientFundsException e) {
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
		
		game.setBalance(0);
		try {
			game.minusBalance(10);
		}
		catch (InsufficientFundsException e) {
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	

	@Test
	void testBalanceSufficient() throws InvalidValueException {
		assertTrue(game.balanceSufficient(0));
		assertTrue(game.balanceSufficient(50));
		assertTrue(game.balanceSufficient(100));
		
		assertFalse(game.balanceSufficient(100.1));
		game.setBalance(0);
		assertTrue(game.balanceSufficient(0));
		assertFalse(game.balanceSufficient(1));
		
		try {
			game.balanceSufficient(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Cannot be a negative value!");
		}
	}

}
