package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import main.*;
import monsters.Chunky;

class BattleTest {
	
	private GameEnvironment game;
	private Battle battle;
	private Player player;
	private Score score;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		battle = new Battle(game);
		player = game.getPlayer();
		score = game.getScoreSystem();
	}

	
	@Test
	public void testSetup() throws InventoryFullException {
		try {
			battle.setup();
		}
		catch (NotFoundException e) {
			assertEquals("Battle not available: Player has no monsters! Try again...", e.getMessage());
		}
		
		Monster testMonster = new Chunky(game);
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
	
	
	@Test
	void testCheckStatus1() {
		player.getMonsters().randomise();
		for (Monster monster : player.getMonsters().getList()) {
			monster.setHealth(0);
			monster.setIsFainted(true);
		}
		
		String result = "\nYou lost!";
    	
    	assertEquals(result, battle.checkStatus());
	}
	
	
	@Test
	void testCheckStatus2() {
		player.getMonsters().randomise();
		for (Monster monster : battle.getMonsters().getList()) {
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

	
	@Test
	void testLose() {
		String result = "\nYou lost!";
		game.getScoreSystem().addBattlesLost();
		assertEquals(result, battle.lose());

		assertEquals(Turn.ENEMY, battle.getWinner());
		
	}
	
	
	@Test
	public void testToString() {
		String result = String.format("Battle %s\n %s\n", battle.getName(), battle.getMonsters());
		assertEquals(result, battle.toString());
	}
	
	
	@Test
	public void testView() {
		String result = battle.toString();
    	result += "\n1: Fight";
    	result += "\n2: Go back";
    	assertEquals(result, battle.view());
	}

}
