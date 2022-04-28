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

	public void launchStatsScreen() {
		StatsScreen statsWindow = new StatsScreen(this);
	}
	
	public void closeStatsScreen(StatsScreen statsWindow) {
		statsWindow.closeWindow();
	}
	
	public static void main(String[] args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		gui.setGame(new GameEnvironment());
		gui.launchStatsScreen();
	}

}
