package main;

public class GameEnvironment {

	/**
	 * Fields
	 */
    private double balance;
    private String playerName;
    private int numDays;
    private int day = 1;
    private Difficulty difficulty = Difficulty.EASY;
    
    private Inventory<Monster> myMonsters;
    private Inventory<Item> myItems;
    
    private Inventory<Monster> allMonsters;
    private Inventory<Item> allItems;
    
    private Shop shop;
    private Inventory<Battle> battles;
    
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
    	scoreSystem = new Score(this);
    	try {
			setBalance(100);
		} catch (InvalidValueException e) {
			e.printStackTrace();
		}
    	scoreSystem.setScore(easyScore);
    }
    
    
    /**
     * Getters and setters
     */
    
    /**
     * Get the value of balance
     * @return the value of balance
     */
    public double getBalance () {
        return balance;
    }
    
    
    /**
     * Set the value of balance
     * @param balance the new value of balance
     * @throws InvalidValueException 
     */
    public void setBalance (double balance) throws InvalidValueException {
    	if (0 > balance) {
    		throw new InvalidValueException("Balance cannot be a negative value!");
    	}
    	else {
    		this.balance = balance;    		
    	}
    }

    
    /**
     * Get the value of playerName
     * @return the value of playerName
     */
    public String getPlayerName () {
        return playerName;
    }
    
    
    /**
     * Set the value of playerName
     * @param playerName the new value of playerName
     * @throws InvalidValueException 
     */
    public void setPlayerName (String playerName) throws InvalidValueException {
    	playerName = playerName.strip();
    	String regex = "(([a-zA-Z])*(\\s)*)*([a-zA-Z])+";
    	if (3 <= playerName.length() && playerName.length() <= 15 && playerName.matches(regex)) {
    		this.playerName = playerName;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid player name! Try again:");
    	}
    }
    
    
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
	 * @return the day
	 */
	public int getDay() {
		return day;
	}


	/**
	 * @param day the day to set
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
     * Get the value of myMonsters
     * @return the value of myMonsters
     */
    public Inventory<Monster> getMyMonsters () {
        return myMonsters;
    }
    
    
    /**
     * Set the value of myMonsters
     * @param myMonsters the new value of myMonsters
     */
    public void setMyMonsters (Inventory<Monster> myMonsters) {
    	this.myMonsters = myMonsters;
    }

    
    /**
     * Get the value of myItems
     * @return the value of myItems
     */
    public Inventory<Item> getMyItems () {
        return myItems;
    }
    
    
    /**
     * Set the value of myItems
     * @param myItems the new value of myItems
     */
    public void setMyItems (Inventory<Item> myItems) {
    	this.myItems = myItems;
    }


	/**
	 * Get the value of allMonsters
	 * @return the value of allMonsters
	 */
	public Inventory<Monster> getAllMonsters() {
		return allMonsters;
	}


	/**
	 * Set the value of allMonsters
	 * @param allMonsters the new value of allMonsters
	 */
	public void setAllMonsters(Inventory<Monster> allMonsters) {
		this.allMonsters = allMonsters;
	}


	/**
	 * Get the value of allItems
	 * @return the value of allItems
	 */
	public Inventory<Item> getAllItems() {
		return allItems;
	}
	
	
	/**
	 * Set the value of allItems
	 * @param allItems the new value of allItems
	 */
	public void setAllItems(Inventory<Item> allItems) {
		this.allItems = allItems;
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
	public Inventory<Battle> getBattles() {
		return battles;
	}


	/**
	 * Set the value of battles
	 * @param battles the new value of battles
	 */
	public void setBattles(Inventory<Battle> battles) {
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
	public void setFinished(boolean isFinished) {
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
     * 3. Setup the shop.
     * 4. Randomize battles.
     */
    public void setupGame() {
    	
    	try {
    		switch(difficulty) {
	    	case EASY:
	    		setBalance(100);
	    		scoreSystem.setScore(easyScore);
	    		break;
	    	case NORMAL:
	    		setBalance(80);
	    		scoreSystem.setScore(normalScore);
	    		break;
	    	case HARD:
	    		setBalance(60);
	    		scoreSystem.setScore(hardScore);
	    		break;
    		}
    	} catch (InvalidValueException e) {
    		e.printStackTrace();
    	}
    	
    	try {    		
    		allMonsters = new Inventory<Monster>(6, this);
    		allMonsters.add(new AverageJoe(this));
    		allMonsters.add(new Chunky(this));
    		allMonsters.add(new Lanky(this));
    		allMonsters.add(new Shanny(this));
    		allMonsters.add(new Raka(this));
    		allMonsters.add(new Zap(this));
    		
    		allItems = new Inventory<Item>(4, this);
    		allItems.add(new HealUp(this));
    		allItems.add(new IncreaseDamage(this));
    		allItems.add(new IncreaseCritRate(this));
    		allItems.add(new LevelUp(this));
    		
    		myMonsters = new Inventory<Monster>(4, this);
    		myItems = new Inventory<Item>(4, this);
    		shop = new Shop(this);
    		battles = new Inventory<Battle>(5, this);
    		Inventory.randomiseBattles(battles, this);
    		randomEvent = new RandomEvent(this);
    	}
    	catch (InventoryFullException e) {
    		e.printStackTrace();
    	}
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
    	String result = "";
    	checkStatus();
    	if (!getIsFinished()) {
    		setDay(getDay() + 1);
    		getShop().randomise();
    		Inventory.randomiseBattles(battles, this);
			result += randomEvent.runAllRandom();
    		Inventory.healAll(myMonsters);
    		scoreSystem.resetDayBattles();
    		result += "The shop has been randomised.\n";
    		result +="The battles have been randomised.\n";
    		result +="Your monsters have healed.\n";
    	}
    	return result;
    }
    
    
    /**
     * Set the value of isFinished to true if either the number of days has reached numDays or there is a stale mate.
     * A stale mate occurs when the player has no monsters and can't afford to buy anything from the shop.
     */
    public void checkStatus() {
    	boolean stalemate = true;
    	if (myMonsters.size() == 0) {
    		for (Monster monster : getShop().getMonsters().getList()) {
    			if (balance >= monster.getCost()) {
    				stalemate = false;
    			}
    		}
    	}
    	else {
    		stalemate = false;
    	}
    	setFinished(day >= numDays || stalemate);
    }
		

    /**
     * Add the given amount to balance.
     * @param amount to be added to balance
     * @throws InvalidValueException if amount is a negative value
     */
    public void addBalance(double amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		setBalance(getBalance() + amount);
    	}
    }
    
    
    /**
     * Subtract the given amount from balance.
     * @param amount to be subtracted from balance.
     * @throws InsufficientFundsException if balance is less than the amount to be subtracted
     * @throws InvalidValueException if amount is a negative value
     */
    public void minusBalance(double amount) throws InsufficientFundsException, InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
		if (balanceSufficient(amount)) {
			setBalance(getBalance() - amount);
    	}
    	else {
    		throw new InsufficientFundsException("Insufficient funds!");
    	}
    }
    
    
    /**
     * Returns whether balance is sufficient to afford the given amount.
     * @param amount the given amount
     * @return whether balance is bigger or equal to amount
     * @throws InvalidValueException if amount is negative value
     */
    public boolean balanceSufficient(double amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		return getBalance() >= amount;
    	}
    }

}