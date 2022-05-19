package main;

import exceptions.InvalidValueException;
import items.HealUp;
import items.IncreaseCritRate;
import items.IncreaseDamage;
import items.LevelUp;
import monsters.*;

/**
 * The main game manager.
 * Holds the generic information about the game.
 * @author Farzad and Daniel
 */
public class GameEnvironment {

	/**
	 * Fields
	 */
    private int numDays;
    private int day = 1;
    private Difficulty difficulty = Difficulty.EASY;
    
    private MonsterInventory allMonsters;
    private ItemInventory allItems;
    
    private Player player;
    private Shop shop;
    private BattleInventory battles;
    
    private RandomEvent randomEvent;
    private Score scoreSystem;
    
    private boolean isFinished = false;
    
    private int easyScore = 1000;
    private int normalScore = 2000;
    private int hardScore = 3000;
    
    
    /**
     * Constructors
     */
    
    /**
     * Create a new GameEnvironment object.
     * 1. Sets default balance.
     * 2. Sets default score.
     */
    public GameEnvironment() {
    	player = new Player(this);
    	shop = new Shop(this);
    	scoreSystem = new Score(this);
    	try {
			player.setBalance(100);
		} catch (InvalidValueException e) {
			e.printStackTrace();
		}
    	scoreSystem.setTotalScore(easyScore);
    }
    
    
    /**
     * Getters and setters
     */

	/**
     * Get the value of numDays
     * @return the value of numDays
     */
    public int getNumDays () {
    	return numDays;
    }
    
    
    /**
     * Set the value of numDays
     * @param numDays the new value of numDays
     * @throws InvalidValueException 
     */
    public void setNumDays (int numDays) throws InvalidValueException {
    	if (5 <= numDays && numDays <= 15) {
    		this.numDays = numDays;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid number of days! Try again:");
    	}
    }

    
    /**
     * Get the value of day
	 * @return the value of day
	 */
	public int getDay() {
		return day;
	}


	/**
	 * Set the value of day
	 * @param day the new value of day
	 */
	public void setDay(int day) {
		this.day = day;
	}


	/**
     * Get the value of difficulty
     * @return the value of difficulty
     */
    public Difficulty getDifficulty () {
        return difficulty;
    }
    
    
    /**
     * Set the value of difficulty
     * @param difficulty the new value of difficulty
     */
    public void setDifficulty (Difficulty difficulty) {
    	this.difficulty = difficulty;
    }


	/**
	 * Get the value of allMonsters
	 * @return the value of allMonsters
	 */
	public MonsterInventory getAllMonsters() {
		return allMonsters;
	}


	/**
	 * Set the value of allMonsters
	 * @param allMonsters the new value of allMonsters
	 */
	public void setAllMonsters(MonsterInventory allMonsters) {
		this.allMonsters = allMonsters;
	}


	/**
	 * Get the value of allItems
	 * @return the value of allItems
	 */
	public ItemInventory getAllItems() {
		return allItems;
	}
	
	
	/**
	 * Set the value of allItems
	 * @param allItems the new value of allItems
	 */
	public void setAllItems(ItemInventory allItems) {
		this.allItems = allItems;
	}

    
	/**
	 * Get the value of player
	 * @return the value of player
	 */
    public Player getPlayer() {
		return player;
	}


    /**
	 * Set the value of player
	 * @param player the new value of player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	/**
	 * Get the value of shop
	 * @return the value of shop
	 */
	public Shop getShop() {
		return shop;
	}


	/**
	 * Set the value of shop
	 * @param shop the new value of shop
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}


	/**
	 * Get the value of battles
	 * @return the value of battles
	 */
	public BattleInventory getBattles() {
		return battles;
	}


	/**
	 * Set the value of battles
	 * @param battles the new value of battles
	 */
	public void setBattles(BattleInventory battles) {
		this.battles = battles;
	}
	
	
	/**
	 * Get the value of randomEvent
	 * @return the value of randomEvent
	 */
	public RandomEvent getRandomEvent() {
		return randomEvent;
	}


	/**
	 * Set the value of randomEvent
	 * @param randomEvent the new value of randomEvent
	 */
	public void setRandomEvent(RandomEvent randomEvent) {
		this.randomEvent = randomEvent;
	}

	
	/**
	 * get the value of isFinished
	 * @return the value of isFinished
	 */
	public boolean getIsFinished() {
		return isFinished;
	}


	/**
	 * Set the value of isFinished
	 * @param isFinished the new value of isFinished
	 */
	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	
	/**
	 * Get the value of scoreSystem
	 * @return the value of scoreSystem
	 */
	public Score getScoreSystem() {
	    return scoreSystem;
	}
	
	
	/**
	 * Set the value of scoreSystem
	 * @param scoreSystem the new value of Score class
	 */
	public void setScoreSystem(Score scoreSystem) {
	    this.scoreSystem = scoreSystem;
	}

	
	/**
	 * Functional
	 */

    /**
     * Setup the necessary game information.
     * 1. Set starting balance and starting score based on the level of difficulty selected by the player.
     * 2. Setup allMonsters and allItems.
     * 3. Setup the shop and randomize it.
     * 4. Setup battles and randomize it.
     */
    public void setupGame() {
    	try {
    		switch(difficulty) {
	    	case EASY:
	    		player.setBalance(100);
	    		scoreSystem.setTotalScore(easyScore);
	    		break;
	    	case NORMAL:
	    		player.setBalance(80);
	    		scoreSystem.setTotalScore(normalScore);
	    		break;
	    	case HARD:
	    		player.setBalance(60);
	    		scoreSystem.setTotalScore(hardScore);
	    		break;
    		}
    	} catch (InvalidValueException e) {
    		e.printStackTrace();
    	}
    	
    	allMonsters = new MonsterInventory(6, this);
		allMonsters.add(new AverageJoe(this));
		allMonsters.add(new Chunky(this));
		allMonsters.add(new Lanky(this));
		allMonsters.add(new Shanny(this));
		allMonsters.add(new Raka(this));
		allMonsters.add(new Zap(this));
		
		allItems = new ItemInventory(4, this);
		allItems.add(new HealUp(this));
		allItems.add(new IncreaseDamage(this));
		allItems.add(new IncreaseCritRate(this));
		allItems.add(new LevelUp(this));
		
		shop.randomise();
		battles = new BattleInventory(5, this);
		battles.randomise();
		randomEvent = new RandomEvent(this);
    }
    
	
	/**
	 * Sleep through the night.
	 * 1. Check the status of the game.
	 * 2. Randomize the shop
	 * 3. Randomize battles
	 * 4. Heal all player monsters.
	 * 5. Reset the number of today's battles.
	 * @return the commentary of events that happened over night.
     */
    public String sleep() {
    	String result = "\n";
    	checkStatus();
    	if (!getIsFinished()) {
    		setDay(getDay() + 1);
    		getAllMonsters().levelUpOnDay();
    		getShop().randomise();
    		battles.randomise();
			result += randomEvent.runAllRandom();
    		player.getMonsters().healAll();
    		scoreSystem.resetDayBattles();
    		scoreSystem.setDayScore(0);
    		result += "The shop has been randomised.\n";
    		result +="The battles have been randomised.\n";
    		result +="Your monsters have healed.";
    	}
    	return result;
    }
    
    
    /**
     * Set the value of isFinished to true if the number of days has reached numDays.
     */
    public void checkStatus() {
    	setIsFinished(day >= numDays);
    }

}