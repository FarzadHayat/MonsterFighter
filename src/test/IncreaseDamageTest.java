package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;
import items.IncreaseDamage;
import main.*;
import monsters.Chunky;

class IncreaseDamageTest {
	
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
		int damageBefore = monster.getDamage();
		Item item = new IncreaseDamage(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems().getList());
		assertEquals(damageBefore + IncreaseDamage.getDamageIncrease(), monster.getDamage());
	}
	
	
	@Test
	void testUse2() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Item not owned
		Monster monster = new Chunky(game);
		Item item = new IncreaseDamage(game);
		player.getMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (NotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
