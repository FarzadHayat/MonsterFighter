package main;

public class Score {
   
	/**
	 * Fields
	 */
	
    private int dayBattlesWon;
    private int dayBattlesLost;
    private int score;
    private int totalBattlesWon;
    private int totalBattlesLost;
    private Player player;
   
    private int battlesWeight = 100;
    private int balanceWeight = 10;
   
    /**
     * Constructors
     */
    
    /**
     * Creates new Score object 
     * @param game given GameEnvironment object
     */
    public Score(GameEnvironment game) {
    	player = game.getPlayer();
    }
    
    /**
     * Getters and Setters
     */
    
    /**
     * Get the value of dayBattlesWon
     * @return the value of dayBattlesWon
     */
    public int getDayBattlesWon() {
    	return dayBattlesWon;
    }
   
    /**
     * Set the value of dayBattlesWon
     * @param battlesWon the new value of dayBattlesWon
     */
    public void setDayBattlesWon(int battlesWon) {
    	this.dayBattlesWon = battlesWon;
    }
   
    /**
     * Get the value of dayBattlesLost
     * @return the value of dayBattlesLost
     */
    public int getDayBattlesLost() {
    	return dayBattlesLost;
    }
   
    /**
     * Set the value of dayBattlesLost
     * @param battlesLost the new value of dayBattlesLost
     */
    public void setDayBattlesLost(int battlesLost) {
    	this.dayBattlesLost = battlesLost;
    }
   
    /**
     * Get the value of score
     * @return the value of score
     */
    public int getScore() {
    	return score;
    }
   
    /**
     * Set the value of score
     * @param score the new value of score
     */
    public void setScore(int score) {
    	this.score = score;
    }
   
    /**
     * Get the value of totalBattlesWon
     * @return the value of totalBattlesWon
     */
    public int getTotalBattlesWon() {
    	return totalBattlesWon;
    }
   
    /**
     * Set the value of totalBattlesWon
     * @param battlesWon the new value of totalBattlesWon
     */
    public void setTotalBattlesWon(int battlesWon) {
    	this.totalBattlesWon = battlesWon;
    }
   
    /**
     * Get the value of totalBattlesLost
     * @return the value of totalBattlesLost
     */
    public int getTotalBattlesLost() {
    	return totalBattlesLost;
    }
   
    /**
     * Set the value of totalBattlesLost
     * @param battlesLost the new value of totalBattlesLost
     */
    public void setTotalBattlesLost(int battlesLost) {
    	this.totalBattlesLost = battlesLost;
    }
    
    /**
     * Get the value of battlesWeight
     * @return the value of battlesWeight
     */
    public int getBattlesWeight() {
    	return battlesWeight;
    }
    
    /**
     * Set the value of battlesWeight
     * @param weight the new value of battlesWeight
     */
    public void setBattlesWeight(int weight) {
    	this.battlesWeight = weight;
    }
    
    /**
     * Get the value of balanceWeight
     * @return the value of balanceWeight
     */
    public int getBalanceWeight() {
    	return balanceWeight;
    }
    
    /**
     * Set the value of balanceWeight
     * @param weight the new value of balanceWeight
     */
    public void setBalanceWeight(int weight) {
    	this.balanceWeight = weight;
    }
    
    /**
     * Functional
     */
    
    /**
     * Add one to dayBattlesWon and totalBattlesWon
     */
    public void addBattlesWon() {
		this.dayBattlesWon++;
		this.totalBattlesWon++;
    }
   
    /**
     * Add one to dayBattlesLost and totalBattlesLost
     */
    public void addBattlesLost() {
		this.dayBattlesLost++;
		this.totalBattlesLost++;
    }
   
    /**
     * Set dayBattlesWon and dayBattlesLost to zero
     */
    public void resetDayBattles() {
		this.dayBattlesLost = 0;
		this.dayBattlesWon = 0;
    }
   
    /**
     * Add to the value of score based on the value of totalBattlesWon and the remaining balance
     * @return score the value of score
     */
    public int getFinalScore() {
		score += totalBattlesWon * battlesWeight + player.getBalance() * balanceWeight;
		return score;
    }
    
    /**
     * Add to the value of score based on the given value
     * @param amount the value to add to the score 
     * @throws InvalidValueException if given value is less than 0
     */
    public void addScore(int amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		setScore(getScore() + amount);
    	}
    }
}