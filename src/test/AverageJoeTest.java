package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class AverageJoeTest {

	private GameEnvironment game;
	private AverageJoe monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		monster = new AverageJoe(game);
	}

	@Test
	void testLevelUp1() throws StatMaxedOutException {
		//Average Joe level up once 
		monster.levelUp();
		assertEquals(110, monster.getMaxHealth());
		assertEquals(30, monster.getDamage());
		assertEquals(70, monster.getCost());
		assertEquals(30, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		//Average Joe level up twice 
		monster.levelUp();
		monster.levelUp();
		assertEquals(120, monster.getMaxHealth());
		assertEquals(40, monster.getDamage());
		assertEquals(80, monster.getCost());
		assertEquals(40, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	@Test
	void testLevelUp3() throws StatMaxedOutException {
		//Average Joe level up at max level
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
