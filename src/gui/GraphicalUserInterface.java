package gui;

import main.*;

/**
 * This class launches the GUI game and manages the screens.
 * @author Farzad and Daniel
 */
public class GraphicalUserInterface {

	private GameEnvironment game;
	
	
	/**
	 * Get the value of game
	 * @return the value of game
	 */
	public GameEnvironment getGame() {
		return game;
	}

	
	/**
	 * set the value of game
	 * @param game the new value of game
	 */
	public void setGame(GameEnvironment game) {
		this.game = game;
	}
	
	
	/**
	 * Launch a new HomeScreen.
	 */
	public void launchHomeScreen() {
		new HomeScreen(this);
	}
	
	
	/**
	 * Close the given HomeScreen.
	 * @param homeWindow the given homeWindow
	 */
	public void closeHomeScreen(HomeScreen homeWindow) {
		homeWindow.closeWindow();
	}

	
	/**
	 * Launch a new StatsScreen.
	 */
	public void launchStatsScreen() {
		new StatsScreen(this);
	}
	
	
	/**
	 * Close the given StatsScreen.
	 * @param statsWindow the given statsWindow
	 */
	public void closeStatsScreen(StatsScreen statsWindow) {
		statsWindow.closeWindow();
	}

	
	/**
	 * Launch a new ShopScreen.
	 */
	public void launchShopScreen() {
		new ShopScreen(this);
	}
	
	
	/**
	 * Close the given ShopScreen.
	 * @param shopWindow the given shopWindow
	 */
	public void closeShopScreen(ShopScreen shopWindow) {
		shopWindow.closeWindow();
	}

	
	/**
	 * Launch a new BattlesScreen.
	 */
	public void launchBattlesScreen() {
		new BattlesScreen(this);
	}
	
	
	/**
	 * Close the given BattlesScreen.
	 * @param battlesWindow the given battlesWindow
	 */
	public void closeBattlesScreen(BattlesScreen battlesWindow) {
		battlesWindow.closeWindow();
	}

	
	/**
	 * Launch a new BattleScreen.
	 */
	public void launchBattleScreen(Battle battle) {
		new BattleScreen(this, battle);
	}
	
	
	/**
	 * Close the given BattleScreen.
	 * @param battleWindow the given battleWindow
	 */
	public void closeBattleScreen(BattleScreen battleWindow) {
		battleWindow.closeWindow();
	}
	
	
	/**
	 * Launch a new MonsterScreen.
	 * @param monster the given monster
	 */
	public void launchMonsterScreen(Monster monster) {
		new MonsterScreen(this, monster);
	}
	
	
	/**
	 * Close the given MonsterScreen.
	 * @param monsterWindow the given monsterWindow
	 */
	public void closeMonsterScreen(MonsterScreen monsterWindow) {
		monsterWindow.closeWindow();
	}
	
	
	/**
	 * Launch a new ItemScreen.
	 */
	public void launchItemScreen(Item item) {
		new ItemScreen(this, item);
	}
	
	
	/**
	 * Close the given ItemScreen.
	 * @param itemWindow the given itemWindow
	 */
	public void closeItemScreen(ItemScreen itemWindow) {
		itemWindow.closeWindow();
	}
	
	
	/**
	 * Launch a new SetupScreen.
	 */
	public void launchSetupScreen() {
		new SetupScreen(this);
	}
	
	
	/**
	 * Close the given SetupScreen.
	 * @param setupWindow the given setupWindow
	 */
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
	}
	
	
	/**
	 * Launch a new StartScreen.
	 */
	public void launchStartScreen() {
		new StartScreen(this);
	}
	
	
	/**
	 * Close the given StartScreen.
	 * @param startWindow the given startWindow
	 */
	public void closeStartScreen(StartScreen startWindow) {
		startWindow.closeWindow();
	}
	
	
	/**
	 * Launch a new StartingMonsterScreen.
	 */
	public void launchStartingMonsterScreen() {
		new StartingMonsterScreen(this);
	}
	
	
	/**
	 * Close the given StartingMonsterScreen.
	 * @param startMonsterWindow the given startMonsterWindow
	 */
	public void closeStartingMonsterScreen(StartingMonsterScreen startMonsterWindow) {
		startMonsterWindow.closeWindow();
	}
	
	
	/**
	 * Launch a new FightScreen.
	 * @param battle the given battle
	 */
	public void launchFightScreen(Battle battle) {
		new FightScreen(this, battle);
	}
	
	
	/**
	 * Close the given FightScreen.
	 * @param fightWindow the given fightWindow
	 */
	public void closeFightScreen(FightScreen fightWindow) {
		fightWindow.closeWindow();
	}
	
	
	/**
	 * Run the GUI game.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		gui.launchStartScreen();
	}

	
	/**
	 * Goes to sleep.
	 * If the game is over, then shows game over alert and then the stats screen.
	 * If the game is not over, then shows the overnight events alert.
	 */
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
