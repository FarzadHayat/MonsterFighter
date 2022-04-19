package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameEnvironment;
import main.Shanny;
import main.StatMaxedOutException;
import main.Zap;

class ZapTest {

	private GameEnvironment game;
	private Zap monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		monster = new Zap(game);
	}

	@Test
	void testLevelUp1() throws StatMaxedOutException {
		//Zap level up once 
		monster.levelUp();
		assertEquals(88, monster.getMaxHealth());
		assertEquals(30, monster.getDamage());
		assertEquals(60, monster.getCost());
		assertEquals(24, monster.getHealAmount());
		assertEquals(0.7, monster.getCritRate());
	}
	
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		//Zap level up twice 
		monster.levelUp();
		monster.levelUp();
		assertEquals(96, monster.getMaxHealth());
		assertEquals(40, monster.getDamage());
		assertEquals(70, monster.getCost());
		assertEquals(32, monster.getHealAmount());
		assertEquals(0.9, monster.getCritRate());
	}
	
	@Test
	void testLevelUp3() throws StatMaxedOutException {
		//Zap level up at max level
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
