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
			assertEquals(e.getMessage(), "Battle not available: Player has no monsters! Try again...");
		}
		
		Monster testMonster = new Chunky(game);
		testMonster.setHealth(0);
		testMonster.setIsFainted(true);
		player.getMonsters().add(testMonster);
		try {
			battle.setup();
		}
		catch (NotFoundException e) {
			assertEquals(e.getMessage(), "Battle not available: Player monsters are all fainted! Try again...");
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
    	
    	assertEquals(battle.checkStatus(), result);
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
    	
    	assertEquals(battle.checkStatus(), result);
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
    	
    	assertEquals(battle.win(), result);
    	assertEquals(battle.getWinner(), Turn.PLAYER);
    	assertEquals(score.getDayBattlesWon(), 1);
    	assertEquals(score.getDayScore(), scoreBefore + scoreReward);
    	assertEquals(player.getBalance(), balanceBefore + balanceReward);
	}

	
	@Test
	void testLose() {
		String result = "\nYou lost!";
		game.getScoreSystem().addBattlesLost();
		assertEquals(battle.lose(), result);

		assertEquals(battle.getWinner(), Turn.ENEMY);
		
	}
	
	
	@Test
	public void testToString() {
		String result = String.format("Battle %s\n %s\n", battle.getName(), battle.getMonsters());
		assertEquals(battle.toString(), result);
	}
	
	
	@Test
	public void testView() {
		String result = battle.toString();
    	result += "\n1: Fight";
    	result += "\n2: Go back";
    	assertEquals(battle.view(), result);
	}

}
