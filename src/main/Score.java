package main;

public class Score {
   
    private int dayBattlesWon;
    private int dayBattlesLost;
    private int score;
    private int totalBattlesWon;
    private int totalBattlesLost;
    private GameEnvironment game;
   
    private int battlesWeight = 100;
    private int balanceWeight = 10;
   
    public Score(GameEnvironment game) {
    	this.game = game;
    }
   
    /**
     * @return number of battles won in a day
     */
    public int getDayBattlesWon() {
    	return dayBattlesWon;
    }
   
    /**
     * @param battlesWon number of battles won in a day
     */
    public void setDayBattlesWon(int battlesWon) {
    	this.dayBattlesWon = battlesWon;
    }
   
    /**
     * @return number of battles lost in a day
     */
    public int getDayBattlesLost() {
    	return dayBattlesLost;
    }
   
    /**
     * @param battlesLost number of battles lost in a day
     */
    public void setDayBattlesLost(int battlesLost) {
    	this.dayBattlesLost = battlesLost;
    }
   
    /**
     * @return player's score
     */
    public int getScore() {
    	return score;
    }
   
    /**
     * @param score the player's score
     */
    public void setScore(int score) {
    	this.score = score;
    }
   
    /**
     * @return total battles won in the game
     */
    public int getTotalBattlesWon() {
    	return totalBattlesWon;
    }
   
    /**
     * @param battlesWon number of battles won
     */
    public void setTotalBattlesWon(int battlesWon) {
    	this.totalBattlesWon = battlesWon;
    }
   
    /**
     * @return total battles lost in the game
     */
    public int getTotalBattlesLost() {
    	return totalBattlesLost;
    }
   
    /**
     * @param battlesLost number of battles lost
     */
    public void setTotalBattlesLost(int battlesLost) {
    	this.totalBattlesLost = battlesLost;
    }
   
    /**
     * Add one to the battles won in a day and total battles won
     */
    public void addBattlesWon() {
		this.dayBattlesWon++;
		this.totalBattlesWon++;
    }
   
    /**
     * Add one to the battles lost in a day and total battles lost
     */
    public void addBattlesLost() {
		this.dayBattlesLost++;
		this.totalBattlesLost++;
    }
   
    /**
     * Reset battles won/lost in a day to zero
     */
    public void resetDayBattles() {
		this.dayBattlesLost = 0;
		this.dayBattlesWon = 0;
    }
   
    /**
     * Calculate the player's final score based on the number of battles won and balance remaining
     * @return player's final calculated score
     */
    public int getFinalScore() {
		score += totalBattlesWon*battlesWeight + game.getBalance()*balanceWeight;
		return score;
    }
}