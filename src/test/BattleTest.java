package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class BattleTest {
	
	private GameEnvironment game;
	private Battle battle;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		battle = new Battle(game);
	}

	
	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testPlayerAttack() {
		fail("Not a testable function");
	}

	
	@Test
	void testEnemyAttack() {
		fail("Not a testable function");
	}

	
	@Test
	void testPlay() {
		fail("Not a testable function");
	}

	
	@Test
	void testCheckStatus() {
		fail("Not yet implemented");
	}


	
	@Test
	void testWin() {
		fail("Not yet implemented");
	}

	
	@Test
	void testLose() {
		fail("Not yet implemented");
	}

}
