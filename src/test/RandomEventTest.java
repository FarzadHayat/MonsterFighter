package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Chunky;
import main.GameEnvironment;
import main.InventoryFullException;
import main.Monster;
import main.PurchasableNotFoundException;
import main.RandomEvent;
import main.StatMaxedOutException;
import main.Zap;

class RandomEventTest {

	private GameEnvironment game;
	private RandomEvent randEvent;
	private Monster monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		randEvent = new RandomEvent(game);
		monster = new Chunky(game);
	}

	@Test
	public void testRandomMonsterLevelUp1() throws StatMaxedOutException {
		randEvent.randomMonsterLevelUp(monster);
		assertTrue(monster.getLevel() == 1 || monster.getLevel() == 2);
	}
	
	@Test 
	public void testRandomMonsterLevelUp2() throws StatMaxedOutException {
		game.getScoreSystem().setDayBattlesWon(9);
		randEvent.randomMonsterLevelUp(monster);
		assertEquals(2, monster.getLevel());
		assertEquals(0.1, randEvent.getLevelUpChance());
	}
	
	@Test
	public void testRandomMonsterLevelUp3() throws StatMaxedOutException {
		game.getScoreSystem().setDayBattlesWon(9);
		monster.setLevel(4);
		try {
			randEvent.randomMonsterLevelUp(monster);
		}
		catch(StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}
	
	@Test
	public void testRandomMonsterLeave1() throws InventoryFullException, PurchasableNotFoundException {
		game.getMyMonsters().add(monster);
		randEvent.randomMonsterLeave(monster);
		assertTrue(game.getMyMonsters().contains(monster) || !game.getMyMonsters().contains(monster));
	}
	
	@Test
	public void testRandomMonsterLeave2() throws InventoryFullException {
		game.getMyMonsters().add(monster);
		monster.setIsFainted(true);
		assertTrue(game.getMyMonsters().contains(monster) || !game.getMyMonsters().contains(monster));
		assertEquals(0.05, randEvent.getLeaveChance());
	}
	
	@Test
	public void testRandomMonsterJoin1() throws InventoryFullException {
		game.getMyMonsters().add(monster);
		randEvent.randomMonsterJoin();
		assertTrue(game.getMyMonsters().size() == 1 || game.getMyMonsters().size() == 2);
	}
	
	@Test
	public void testRandomMonsterJoin2() throws InventoryFullException {
		for(int i = 0; i < game.getMyMonsters().getMaxSize(); i++) {
			game.getMyMonsters().add(monster);
		}
		try {
			randEvent.randomMonsterJoin();
			assertEquals(game.getMyMonsters().getMaxSize(), game.getMyMonsters().size());
		}
		catch(InventoryFullException e) {
			assertEquals(e.getMessage(), "Inventory full!");
		}
	}
	
	@Test
	public void testRunAllRandom1() throws InventoryFullException, StatMaxedOutException, PurchasableNotFoundException {
		game.getMyMonsters().add(monster);
		randEvent.runAllRandom();
		assertTrue(monster.getLevel() == 1 || monster.getLevel() == 2);
		assertTrue(game.getMyMonsters().size() != 0);
		assertTrue(game.getMyMonsters().size() == 1 || game.getMyMonsters().size() == 2 || game.getMyMonsters().size() == 3 || game.getMyMonsters().size() == 4);
	}
	
	@Test
	public void testRunAllRandom2() throws InventoryFullException, StatMaxedOutException, PurchasableNotFoundException {
		Monster zap = new Zap(game);
		game.getMyMonsters().add(zap);
		game.getMyMonsters().add(monster);
		randEvent.runAllRandom();
		assertTrue(monster.getLevel() == 1 || monster.getLevel() == 2);
		assertTrue(zap.getLevel() == 1 || zap.getLevel() == 2);
		assertTrue(game.getMyMonsters().size() != 0);
		assertTrue(game.getMyMonsters().size() == 1 || game.getMyMonsters().size() == 2 || game.getMyMonsters().size() == 3 || game.getMyMonsters().size() == 4);
	}

}
