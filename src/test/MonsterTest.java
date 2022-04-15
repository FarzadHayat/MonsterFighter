package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameEnvironment;
import main.Monster;

class MonsterTest {

	private GameEnvironment game;
	private Monster monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	@Test
	void test() {
		monster = new Monster("Test", "Testing description", 100, 20, 20, 1, 10, 0.1);

	}

}
