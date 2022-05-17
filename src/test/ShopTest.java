package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class ShopTest {

	private GameEnvironment game;
	private Shop shop;
	private Player player;
	private MonsterInventory monsters;
	private ItemInventory items;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		shop = game.getShop();
		player = game.getPlayer();
		monsters = shop.getMonsters();
		items = shop.getItems();
	}

	
	@Test
	void testRandomise() {
		ArrayList<Monster> monstersList = monsters.getList();
		ArrayList<Item> itemsList = items.getList();
		shop.randomise();
		assertTrue(monstersList != monsters.getList());
		assertTrue(itemsList != items.getList());
	}

	
	@Test
	void testToString() {
		String result = String.format("\nBalance: %s\n", player.getBalance());
    	int start = 1;
    	result += "\n===== MONSTERS =====\n\n";
    	for (Monster monster : monsters.getList()) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	result += "\n===== ITEMS =====\n\n";
    	for (Item item : items.getList()) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	result += String.format("\n%s: Go back", start);
    	assertEquals(result, shop.toString());
	}

}
