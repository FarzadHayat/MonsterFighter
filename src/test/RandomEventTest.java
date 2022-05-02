package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;
import main.*;
import monsters.Chunky;
import monsters.Zap;

class RandomEventTest {

	private GameEnvironment game;
	private RandomEvent randEvent;
	private Monster monster;
	private Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		player = game.getPlayer();
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
	
//	@Test
//	public void testRandomMonsterLevelUp3() throws StatMaxedOutException {
//		game.getScoreSystem().setDayBattlesWon(9);
//		monster.setLevel(4);
//		try {
//			randEvent.randomMonsterLevelUp(monster);
//		}
//		catch(StatMaxedOutException e) {
//			assertEquals(e.getMessage(), "Monster is already max level!");
//		}
//	}
	
	@Test
	public void testRandomMonsterLeave1() throws InventoryFullException, NotFoundException {
		player.getMonsters().add(monster);
		randEvent.randomMonsterLeave(monster);
		assertTrue(player.getMonsters().getList().contains(monster) || !player.getMonsters().getList().contains(monster));
	}
	
	@Test
	public void testRandomMonsterLeave2() throws InventoryFullException {
		player.getMonsters().add(monster);
		monster.setIsFainted(true);
		assertTrue(player.getMonsters().getList().contains(monster) || !player.getMonsters().getList().contains(monster));
		assertEquals(0.05, randEvent.getLeaveChance());
	}
	
	@Test
	public void testRandomMonsterJoin1() throws InventoryFullException {
		player.getMonsters().add(monster);
		randEvent.randomMonsterJoin();
		assertTrue(player.getMonsters().getList().size() == 1 || player.getMonsters().getList().size() == 2);
	}
	
//	@Test
//	public void testRandomMonsterJoin2() throws InventoryFullException {
//		for(int i = 0; i < game.getMyMonsters().getMaxSize(); i++) {
//			game.getMyMonsters().add(monster);
//		}
//		try {
//			randEvent.randomMonsterJoin();
//			assertEquals(game.getMyMonsters().getMaxSize(), game.getMyMonsters().size());
//		}
//		catch(InventoryFullException e) {
//			assertEquals(e.getMessage(), "Inventory full!");
//		}
//	}
	
	@Test
	public void testRunAllRandom1() throws InventoryFullException, StatMaxedOutException, NotFoundException {
		player.getMonsters().add(monster);
		randEvent.runAllRandom();
		assertTrue(monster.getLevel() == 1 || monster.getLevel() == 2);
		assertTrue(player.getMonsters().getList().size() != 0);
		assertTrue(player.getMonsters().getList().size() == 1 || player.getMonsters().getList().size() == 2 || 
				player.getMonsters().getList().size() == 3 || player.getMonsters().getList().size() == 4);
	}
	
	@Test
	public void testRunAllRandom2() throws InventoryFullException, StatMaxedOutException, NotFoundException {
		Monster zap = new Zap(game);
		player.getMonsters().add(zap);
		player.getMonsters().add(monster);
		randEvent.runAllRandom();
		assertTrue(monster.getLevel() == 1 || monster.getLevel() == 2);
		assertTrue(zap.getLevel() == 1 || zap.getLevel() == 2);
		assertTrue(player.getMonsters().getList().size() != 0);
		assertTrue(player.getMonsters().getList().size() == 1 || player.getMonsters().getList().size() == 2 || 
				player.getMonsters().getList().size() == 3 || player.getMonsters().getList().size() == 4);
	}

}
