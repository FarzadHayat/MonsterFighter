package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AverageJoe;
import main.Chunky;
import main.GameEnvironment;
import main.StatMaxedOutException;

class ChunkyTest {

	private GameEnvironment game;
	private Chunky monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		monster = new Chunky(game);
	}

	@Test
	void testLevelUp1() throws StatMaxedOutException {
		//Chunky level up once 
		monster.levelUp();
		assertEquals(240, monster.getMaxHealth());
		assertEquals(15, monster.getDamage());
		assertEquals(90, monster.getCost());
		assertEquals(60, monster.getHealAmount());
		assertEquals(0.1, monster.getCritRate());
	}
	
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		//Chunky level up twice 
		monster.levelUp();
		monster.levelUp();
		assertEquals(280, monster.getMaxHealth());
		assertEquals(20, monster.getDamage());
		assertEquals(100, monster.getCost());
		assertEquals(80, monster.getHealAmount());
		assertEquals(0.1, monster.getCritRate());
	}
	
	@Test
	void testLevelUp3() throws StatMaxedOutException {
		//Chunky level up at max level
		for(int i = 0; i < 3; i++) {
			monster.levelUp();
		}
		try {
			monster.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}

}
