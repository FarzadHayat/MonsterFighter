package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import items.HealUp;
import items.IncreaseCritRate;
import items.IncreaseDamage;
import items.LevelUp;
import main.*;
import monsters.AverageJoe;
import monsters.Chunky;
import monsters.Lanky;
import monsters.Raka;
import monsters.Shanny;
import monsters.Zap;

class GameEnvironmentTest {

	private GameEnvironment game;
	private Player player;
	private Score score;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		player = game.getPlayer();
		score = game.getScoreSystem();
	}

	
	/**
	 * Getters and setters
	 * 
	 */
	
	@Test
	void testSetNumDays() throws InvalidValueException {
		// Blue sky
		game.setNumDays(5);
		assertEquals(5, game.getNumDays());
		
		game.setNumDays(15);
		assertEquals(15, game.getNumDays());
		
		// Invalid num days error
		int[] testNumDays = {4, 0, 16, -5, 100};
		for (int numDay : testNumDays) {			
			try {			
				game.setNumDays(numDay);
			}
			catch (InvalidValueException e){
				assertEquals("Invalid number of days! Try again:", e.getMessage());
			}
		}
	}

	
	@Test
	void testSetDifficulty() {
		// Blue sky
		game.setDifficulty(Difficulty.EASY);
		assertEquals(Difficulty.EASY, game.getDifficulty());
		
		game.setDifficulty(Difficulty.NORMAL);
		assertEquals(Difficulty.NORMAL, game.getDifficulty());
		
		game.setDifficulty(Difficulty.HARD);
		assertEquals(Difficulty.HARD, game.getDifficulty());
	}

	
	/**
	 * Functional
	 * 
	 */
	
	@Test
	void setupGame() {
		assertEquals(100, player.getBalance());
		assertEquals(1000, score.getTotalScore());
	}
	
	
	@Test
	void testSleep1() throws InventoryFullException, InvalidValueException {
		// Blue sky
		game.setNumDays(10);
		String result = "\n";
		result += "The shop has been randomised.\n";
		result +="The battles have been randomised.\n";
		result +="Your monsters have healed.";
    	
		ArrayList<Monster> shopMonstersBefore = game.getShop().getMonsters();
		ArrayList<Item> shopItemsBefore = game.getShop().getItems();
		ArrayList<Battle> battlesBefore = game.getBattles();
		
		Monster testMonster = new Chunky(game);
		player.getMonsters().add(testMonster);
		testMonster.setHealth(10);
		int healthBefore = testMonster.getHealth();
    	
		assertTrue(game.sleep().contains(result));
		
		assertFalse(game.getShop().getMonsters().equals(shopMonstersBefore));
		assertFalse(game.getShop().getItems().equals(shopItemsBefore));
		assertFalse(game.getBattles().equals(battlesBefore));
		
		assertEquals(healthBefore + testMonster.getHealAmount(), testMonster.getHealth());
		
		assertEquals(2, game.getDay());
		assertEquals(0, score.getDayBattlesWon());
		assertEquals(0, score.getDayBattlesLost());
		assertEquals(0, score.getDayScore());
	}
	
	
	@Test
	void testSleep2() {
		// Game over
		String result = "\n";

		assertEquals(result, game.sleep());
    	assertTrue(game.getIsFinished());
	}

	
	@Test
	void testCheckStatus() throws InvalidValueException {
		// Blue sky
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

}
