package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;
import monsters.Zap;

/**
 * Unit test for Zap class
 * @author Farzad and Daniel
 */

class ZapTest {

	/**
	 * Fields
	 */
	private GameEnvironment game;
	private Zap monster;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	public void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		monster = new Zap(game);
	}

	/**
	 * Levels up monster once
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
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
	
	/**
	 * Levels up monster twice
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
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
	
	/**
	 * Levels up monster four times
	 * @result monster is unable to level up further and exception is thrown
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
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
	
	/**
	 * Clones monster instance
	 * @result new monster instance is created and does not equal the original instance
	 */
	@Test
	public void testClone() {
		Monster cloneInst = monster.clone();
		assertTrue(cloneInst != monster);
		assertTrue(Zap.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == monster.getLevel());
	}

}
