package gui;

import exceptions.InventoryFullException;
import items.HealUp;
import items.IncreaseCritRate;
import items.IncreaseDamage;
import items.LevelUp;
import main.*;
import monsters.*;

public class GraphicalUserInterface {

	private GameEnvironment game;
	
	public GameEnvironment getGame() {
		return game;
	}

	public void setGame(GameEnvironment game) {
		this.game = game;
	}
	
	public void launchHomeScreen() {
		new HomeScreen(this);
	}
	
	public void closeHomeScreen(HomeScreen homeWindow) {
		homeWindow.closeWindow();
	}

	public void launchStatsScreen() {
		new StatsScreen(this);
	}
	
	public void closeStatsScreen(StatsScreen statsWindow) {
		statsWindow.closeWindow();
	}

	public void launchShopScreen() {
		new ShopScreen(this);
	}
	
	public void closeShopScreen(ShopScreen shopWindow) {
		shopWindow.closeWindow();
	}

	public void launchBattlesScreen() {
		new BattlesScreen(this);
	}
	
	public void closeBattlesScreen(BattlesScreen battlesWindow) {
		battlesWindow.closeWindow();
	}

	public void launchBattleScreen(Battle battle) {
		new BattleScreen(this, battle);
	}
	
	public void closeBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
	}
	
	public void launchMonsterScreen(Monster monster) {
		new MonsterScreen(this, monster);
	}
	
	public void closeMonsterScreen(MonsterScreen monsterWindow) {
		monsterWindow.closeWindow();
	}
	
	public void launchItemScreen(Item item) {
		new ItemScreen(this, item);
	}
	
	public void closeItemScreen(ItemScreen itemWindow) {
		itemWindow.closeWindow();
	}
	
	public void launchSetupScreen() {
		new SetupScreen(this);
	}
	
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
	}
	
	public void launchStartScreen() {
		new StartScreen(this);
	}
	
	public void closeStartScreen(StartScreen startWindow) {
		startWindow.closeWindow();
	}
	
	public void launchStartingMonsterScreen() {
		new StartingMonsterScreen(this);
	}
	
	public void closeStartingMonsterScreen(StartingMonsterScreen startMonsterWindow) {
		startMonsterWindow.closeWindow();
	}
	
	public void launchFightScreen(Battle battle) {
		new FightScreen(this, battle);
	}
	
	public void closeFightScreen(FightScreen fightWindow) {
		fightWindow.closeWindow();
	}
	
	public static void main(String[] args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		
		// testing inventories
		try {
			gui.getGame().setAllMonsters(new MonsterInventory(6, gui.getGame()));
			gui.getGame().getAllMonsters().add(new Chunky(gui.getGame()));
			gui.getGame().getAllMonsters().add(new Lanky(gui.getGame()));
			gui.getGame().getAllMonsters().add(new Shanny(gui.getGame()));
			gui.getGame().getAllMonsters().add(new AverageJoe(gui.getGame()));
			gui.getGame().getAllMonsters().add(new Zap(gui.getGame()));
			gui.getGame().getAllMonsters().add(new Raka(gui.getGame()));
			
			gui.getGame().setAllItems(new ItemInventory(4, gui.getGame()));
			gui.getGame().getAllItems().add(new LevelUp(gui.getGame()));
			gui.getGame().getAllItems().add(new HealUp(gui.getGame()));
			gui.getGame().getAllItems().add(new IncreaseCritRate(gui.getGame()));
			gui.getGame().getAllItems().add(new IncreaseDamage(gui.getGame()));
			
			gui.getGame().getPlayer().getMonsters().randomise();
			
			gui.getGame().getPlayer().getItems().randomise();
			
			gui.getGame().setBattles(new BattleInventory(5, gui.getGame()));
			gui.getGame().getBattles().randomise();
		} catch (InventoryFullException e1) {
			e1.printStackTrace();
		}
		
		gui.launchStartScreen();
	}

	public void launchSleepAlert() {
		// sleep and show overnight commentary
		String events = game.sleep();
		if (game.getIsFinished()) {
			AlertBox.infoBox("GAME OVER!", "Game over");
			launchStatsScreen();
		}
		else {
			AlertBox.infoBox(events.replaceFirst("(?:\n)+", ""), "Good morning!");
		}
	}

}
