package gui;

import main.*;

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
	
	public static void main(String[] args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		
		// testing inventories
		try {
			gui.getGame().getPlayer().getMonsters().add(new Chunky(gui.getGame()));
			gui.getGame().getPlayer().getMonsters().add(new Lanky(gui.getGame()));
			gui.getGame().getPlayer().getMonsters().add(new Shanny(gui.getGame()));
			gui.getGame().getPlayer().getMonsters().add(new AverageJoe(gui.getGame()));
			
			gui.getGame().getPlayer().getItems().add(new LevelUp(gui.getGame()));
			gui.getGame().getPlayer().getItems().add(new HealUp(gui.getGame()));
			gui.getGame().getPlayer().getItems().add(new IncreaseCritRate(gui.getGame()));
			gui.getGame().getPlayer().getItems().add(new IncreaseDamage(gui.getGame()));
		} catch (InventoryFullException e1) {
			e1.printStackTrace();
		}
		
		gui.launchHomeScreen();
	}

	public void launchSleepAlert() {
		// sleep and show overnight commentary
	}

}
