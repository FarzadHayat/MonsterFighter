package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;
import monsters.Zap;

class ZapTest {

	private GameEnvironment game;
	private Zap monster;
	
	@BeforeEach
	public void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		monster = new Zap(game);
	}

	@Test
	public void testLevelUp1() throws StatMaxedOutException {
		//Zap level up once 
		monster.levelUp();
		assertEquals(88, monster.getMaxHealth());
		assertEquals(30, monster.getDamage());
		assertEquals(60, monster.getCost());
		assertEquals(24, monster.getHealAmount());
		assertEquals(0.7, monster.getCritRate());
	}
	
	@Test
	public void testLevelUp2() throws StatMaxedOutException {
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
	public void testLevelUp3() throws StatMaxedOutException {
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
	
	@Test
	public void testClone() {
		Monster cloneInst = monster.clone();
		assertTrue(cloneInst != monster);
		assertTrue(Zap.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == monster.getLevel());
	}

}
