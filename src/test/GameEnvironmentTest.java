package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class GameEnvironmentTest {

	private GameEnvironment game;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Getters and setters
	 */

	@Test
	void testSetBalance() {
		fail("Not yet implemented");
	}

	
	@Test
	void testSetPlayerName() throws InvalidValueException {
		game.setPlayerName("max");
		assertEquals("max", game.getPlayerName());
		
		game.setPlayerName("ABC   Abc   abc");
		assertEquals("ABC   Abc   abc", game.getPlayerName());
		
		game.setPlayerName("  whitespace               ");
		assertEquals("whitespace", game.getPlayerName());
		
		try {			
			game.setPlayerName("aj");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setPlayerName("");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setPlayerName("Abc   Abc   AbcZ");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setPlayerName("123");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setPlayerName("Mikey5x");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setPlayerName("!WoW");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setPlayerName("oops_");
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
	}

	
	@Test
	void testSetNumDays() throws InvalidValueException {
		game.setNumDays(5);
		assertEquals(5, game.getNumDays());
		
		game.setNumDays(15);
		assertEquals(15, game.getNumDays());
		
		try {			
			game.setNumDays(4);
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setNumDays(0);
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
		
		try {			
			game.setNumDays(16);
		}
		catch (InvalidValueException e){
			assertEquals(e.getMessage(), "Invalid player name! Try again:");
		}
	}

	
	@Test
	void testSetDifficulty() {
		fail("Not yet implemented");
	}

	
	@Test
	void testSetNumBattles() {
		fail("Not yet implemented");
	}

	
	/**
	 * Functional
	 * 
	 */
	
	@Test
	void testSleep() {
		fail("Not yet implemented");
	}

	
	@Test
	void testViewShop() {
		fail("Not yet implemented");
	}
	

	@Test
	void testViewBattles() {
		fail("Not yet implemented");
	}
	

	@Test
	void testViewTeam() {
		fail("Not yet implemented");
	}
	

	@Test
	void testViewStats() {
		fail("Not yet implemented");
	}
	

	@Test
	void testViewInventory() {
		fail("Not yet implemented");
	}
	

	@Test
	void testSetupGame() {
		fail("Not yet implemented");
	}
	

	@Test
	void testSelectPlayerName() {
		fail("Not yet implemented");
	}
	

	@Test
	void testSelectNumDays() {
		fail("Not yet implemented");
	}
	

	@Test
	void testSelectDifficulty() {
		fail("Not yet implemented");
	}
	

	@Test
	void testSelectBattle() {
		fail("Not yet implemented");
	}
	

	@Test
	void testSelectStartingMonster() {
		fail("Not yet implemented");
	}
	

	@Test
	void testAddBalance() {
		fail("Not yet implemented");
	}
	

	@Test
	void testMinusBalance() {
		fail("Not yet implemented");
	}
	

	@Test
	void testBalanceSufficient() {
		fail("Not yet implemented");
	}
	

	@Test
	void testRandomiseBattles() {
		fail("Not yet implemented");
	}
	

	@Test
	void testRandomiseShop() {
		fail("Not yet implemented");
	}
	

	@Test
	void testRun() {
		fail("Not yet implemented");
	}

}
