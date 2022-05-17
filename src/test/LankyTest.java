package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;
import monsters.Lanky;

class LankyTest {

	private GameEnvironment game;
	private Lanky monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		monster = new Lanky(game);
	}

	@Test
	void testLevelUp1() throws StatMaxedOutException {
		//Lanky level up once 
		monster.levelUp();
		assertEquals(66, monster.getMaxHealth());
		assertEquals(55, monster.getDamage());
		assertEquals(80, monster.getCost());
		assertEquals(18, monster.getHealAmount());
		assertEquals(0.1, monster.getCritRate());
	}
	
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		//Lanky level up twice 
		monster.levelUp();
		monster.levelUp();
		assertEquals(72, monster.getMaxHealth());
		assertEquals(70, monster.getDamage());
		assertEquals(90, monster.getCost());
		assertEquals(24, monster.getHealAmount());
		assertEquals(0.1, monster.getCritRate());
	}
	
	@Test
	void testLevelUp3() throws StatMaxedOutException {
		//Lanky level up at max level
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
	
	@Test
	public void testClone() {
		Monster cloneInst = monster.clone();
		assertTrue(cloneInst != monster);
		assertTrue(Lanky.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == monster.getLevel());
	}

}
