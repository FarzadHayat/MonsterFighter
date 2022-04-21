package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class ShannyTest {

	private GameEnvironment game;
	private Shanny monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		monster = new Shanny(game);
	}

	@Test
	void testLevelUp1() throws StatMaxedOutException {
		//Shanny level up once 
		monster.levelUp();
		assertEquals(110, monster.getMaxHealth());
		assertEquals(23, monster.getDamage());
		assertEquals(70, monster.getCost());
		assertEquals(70, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		//Shanny level up twice 
		monster.levelUp();
		monster.levelUp();
		assertEquals(120, monster.getMaxHealth());
		assertEquals(31, monster.getDamage());
		assertEquals(80, monster.getCost());
		assertEquals(90, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	@Test
	void testLevelUp3() throws StatMaxedOutException {
		//Shanny level up at max level
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
