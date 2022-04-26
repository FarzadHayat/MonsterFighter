package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class LevelUpTest {
	
	private GameEnvironment game;
	private Player player;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		player = game.getPlayer();
	}

	
	@Test
	void testUse1() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		int levelBefore = monster.getLevel();
		Item item = new LevelUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems().getList());
		assertEquals(levelBefore + 1, monster.getLevel());
	}
	
	
	@Test
	void testUse2() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Monster is already max level
		Monster monster = new Chunky(game);
		monster.setLevel(monster.getMaxLevel());
		Item item = new LevelUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Item not owned
		Monster monster = new Chunky(game);
		Item item = new LevelUp(game);
		player.getMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (NotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
