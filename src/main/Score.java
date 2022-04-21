package main;

public class Score {
    
    private int dayBattlesWon;
    private int dayBattlesLost;
    private int score;
    private int totalBattlesWon;
    private int totalBattlesLost;
    private GameEnvironment game;
    
    public Score(GameEnvironment game) {
	this.game = game;
    }
    
    public int getDayBattlesWon() {
	return dayBattlesWon;
    }
    
    public void setDayBattlesWon(int battlesWon) {
	this.dayBattlesWon = battlesWon;
    }
    
    public int getDayBattlesLost() {
	return dayBattlesLost;
    }
    
    public void setDayBattlesLost(int battlesLost) {
	this.dayBattlesLost = battlesLost;
    }
    
    public int getScore() {
	return score;
    }
    
    public void setScore(int score) {
	this.score = score;
    }
    
    public int getTotalBattlesWon() {
	return totalBattlesWon;
    }
    
    public void setTotalBattlesWon(int battlesWon) {
	this.totalBattlesWon = battlesWon;
    }
    
    public int getTotalBattlesLost() {
	return totalBattlesLost;
    }
    
    public void setTotalBattlesLost(int battlesLost) {
	this.totalBattlesLost = battlesLost;
    }
    
    public void addBattlesWon() {
	this.dayBattlesWon++;
	this.totalBattlesWon++;
    }
    
    public void addBattlesLost() {
	this.dayBattlesLost++;
	this.totalBattlesLost++;
    }
    
    public void resetDayBattles() {
	this.dayBattlesLost = 0;
	this.dayBattlesWon = 0;
    }
}
