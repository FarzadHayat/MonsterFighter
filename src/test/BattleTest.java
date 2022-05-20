package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import main.*;
import monsters.Chunky;

/**
 * Unit test for Battle class
 * @author Farzad and Daniel
 */

class BattleTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Battle battle;
	private Player player;
	private Score score;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game.populateGame();
		battle = new Battle();
		player = game.getPlayer();
		score = game.getScoreSystem();
	}

	/**
	 * Setup battle instance
	 * @result exception is thrown if player has no monsters or if monsters are all fainted
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testSetup() throws InventoryFullException {
		try {
			battle.setup();
		}
		catch (NotFoundException e) {
			assertEquals("Battle not available: Player has no monsters! Try again...", e.getMessage());
		}
		
		Monster testMonster = new Chunky();
		testMonster.setHealth(0);
		testMonster.setIsFainted(true);
		player.getMonsters().add(testMonster);
		try {
			battle.setup();
		}
		catch (NotFoundException e) {
			assertEquals("Battle not available: Player monsters are all fainted! Try again...", e.getMessage());
		}
	}
	
	/**
	 * Check status of battle when player's monsters are all fainted
	 * @result correct string is returned without any errors
	 */
	@Test
	void testCheckStatus1() {
		player.getMonsters().randomise();
		for (Monster monster : player.getMonsters()) {
			monster.setHealth(0);
			monster.setIsFainted(true);
		}
		
		String result = "\nYou lost!";
    	
    	assertEquals(result, battle.checkStatus());
	}
	
	/**
	 * Check status of battle when enemy's monsters are all fainted
	 * @result correct string is returned without any errors
	 */
	@Test
	void testCheckStatus2() {
		player.getMonsters().randomise();
		for (Monster monster : battle.getMonsters()) {
			monster.setHealth(0);
			monster.setIsFainted(true);
		}
		
		int balanceReward = battle.getBalanceMultiplier() * battle.getSize();
		int scoreReward = battle.getScoreMultiplier() * battle.getSize();
		
    	String result = "\nYou won!";
    	result += String.format("\nYou have gained %s gold!", balanceReward);
    	result += String.format("\nYou have gained %s score!", scoreReward);
    	
    	assertEquals(result, battle.checkStatus());
	}

	/**
	 * Player wins the battle
	 * @result player wins battle, score and balance is increased without any errors
	 */
	@Test
	void testWin() {
		int balanceBefore = game.getPlayer().getBalance();
		int scoreBefore = game.getScoreSystem().getDayScore();

		int balanceReward = battle.getBalanceMultiplier() * battle.getSize();
		int scoreReward = battle.getScoreMultiplier() * battle.getSize();
		
    	String result = "\nYou won!";
    	result += String.format("\nYou have gained %s gold!", balanceReward);
    	result += String.format("\nYou have gained %s score!", scoreReward);
    	
    	assertEquals(result, battle.win());
    	assertEquals(Turn.PLAYER, battle.getWinner());
    	assertEquals(1, score.getDayBattlesWon());
    	assertEquals(scoreBefore + scoreReward, score.getDayScore());
    	assertEquals(balanceBefore + balanceReward, player.getBalance());
	}

	/**
	 * Player loses the battle
	 * @result player loses battle, correct string is returned without any errors
	 */
	@Test
	void testLose() {
		String result = "\nYou lost!";
		game.getScoreSystem().addBattlesLost();
		assertEquals(result, battle.lose());

		assertEquals(Turn.ENEMY, battle.getWinner());
		
	}
	
	/**
	 * Checks string representation of battle
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testToString() {
		String result = String.format("Battle %s\n %s\n", battle.getName(), battle.getMonsters());
		assertEquals(result, battle.toString());
	}
	
	/**
	 * Checks string representation of view option
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView() {
		String result = battle.toString();
    	result += "\n1: Fight";
    	result += "\n2: Go back";
    	assertEquals(result, battle.view());
	}

}
