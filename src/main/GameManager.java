package main;

public class GameManager {
	
	private static String playerName;
	private static int numDays;
	private static Difficulty difficulty = Difficulty.EASY;
	
	/**
     * Set the value of difficulty
     * @param difficulty the new value of difficulty
     */
    public static void setDifficulty (Difficulty difficulty) {
    	GameManager.difficulty = difficulty;
    }
    
    /**
     * @return difficulty of the game
     */
    public static Difficulty getDifficulty() {
    	return difficulty;
    }
    
    /**
     * @return player name
     */
    public static String getPlayerName() {
    	return playerName;
    }
    
    /**
     * @return number of days in the game
     */
    public static int getNumDays() {
    	return numDays;
    }
	
	/**
     * Set the value of playerName
     * @param playerName the new value of playerName
     * @throws InvalidValueException 
     */
    public static void setPlayerName (String playerName) throws InvalidValueException {
    	playerName = playerName.strip();
    	String regex = "(([a-zA-Z])*(\\s)*)*([a-zA-Z])+";
    	if (3 <= playerName.length() && playerName.length() <= 15 && playerName.matches(regex)) {
    		GameManager.playerName = playerName;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid player name! Try again:");
    	}
    }
    
    /**
     * Set the value of numDays
     * @param numDays the new value of numDays
     * @throws InvalidValueException 
     */
    public static void setNumDays (int numDays) throws InvalidValueException {
    	if (5 <= numDays && numDays <= 15) {
    		GameManager.numDays = numDays;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid number of days! Try again:");
    	}
    }
    
}
