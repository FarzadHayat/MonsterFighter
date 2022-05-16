package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;
import monsters.Raka;

class RakaTest {

	private GameEnvironment game;
	private Raka raka;
	private Monster target;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		raka = new Raka(game);
		target = new AverageJoe(game);
	}

	@Test
	public void testHeal1() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to max health  
		target.takeDamage(raka.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth(), target.getHealth());
	}
	
	@Test 
	public void testHeal2() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to less than max health 
		target.takeDamage(target.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth()-4, target.getHealth());
	}
	
	@Test
	public void testHeal3() throws InvalidTargetException, InvalidValueException {
		//Raka tries to heal fainted ally 
		try {
			target.takeDamage(target.getHealth());
			raka.healAllies(target);
		}
		catch(InvalidTargetException e) {
			assertEquals(0, target.getHealth());
			assertEquals(e.getMessage(), "Invalid target!");
		}
	}
	
	@Test
	void testLevelUp1() throws StatMaxedOutException {
		//Raka level up once 
		raka.levelUp();
		assertEquals(88, raka.getMaxHealth());
		assertEquals(15, raka.getDamage());
		assertEquals(80, raka.getCost());
		assertEquals(24, raka.getHealAmount());
		assertEquals(0.1, raka.getCritRate());
	}
	
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		//Raka level up twice 
		raka.levelUp();
		raka.levelUp();
		assertEquals(96, raka.getMaxHealth());
		assertEquals(20, raka.getDamage());
		assertEquals(90, raka.getCost());
		assertEquals(32, raka.getHealAmount());
		assertEquals(0.1, raka.getCritRate());
	}
	
	@Test
	void testLevelUp3() throws StatMaxedOutException {
		//Raka level up at max level
		for(int i = 0; i < 3; i++) {
			raka.levelUp();
		}
		try {
			raka.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}

}
