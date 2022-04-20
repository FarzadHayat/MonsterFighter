package main;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GameEnvironment {

	/**
	 * Fields
	 * 
	 */
    private double balance;
    private String playerName;
    private int numDays;
    private int day;
    private Difficulty difficulty;
    
    private enum Difficulty {
    	EASY,
    	NORMAL,
    	HARD
    }
    
    private int numBattles = 5;
    private ArrayList<Battle> battleList;
    
    private MonsterInventory myMonsters;
    private ItemInventory myItems;
    
    private MonsterInventory allMonsters;
    private ItemInventory allItems;
    
    private MonsterInventory shopMonsters;
    private ItemInventory shopItems;
    
    private Scanner scanner = new Scanner(System.in);
    
    private enum Command {
    	VIEW,
    	SELECT,
    	QUIT
    }
    
    private enum View {
    	SHOP,
    	MONSTERS,
    	ITEMS,
    	BATTLES,
    	STATS
    }
    
    private enum Select {
    	NAME,
    	DAYS,
    	DIFFICULTY,
    	BATTLE,
    	MONSTER
    }
    
    private enum Shop {
    	BUY,
    	SELL,
    	LEAVE
    }
    
    
    /**
     * Constructors
     * @throws InventoryFullException 
     * @throws InvalidValueException 
     */
    public GameEnvironment () throws InventoryFullException, InvalidValueException {
    	setDay(1);
    	setBalance(0);
    	setMyMonsters(new MonsterInventory(this));
    	setMyItems(new ItemInventory(this));

    	setAllMonsters(new MonsterInventory(this));
    	ArrayList<Monster> allMonstersList = new ArrayList<Monster>();
    	allMonstersList.add(new AverageJoe(this));
    	allMonstersList.add(new Chunky(this));
    	allMonstersList.add(new Lanky(this));
    	allMonstersList.add(new Shanny(this));
    	allMonstersList.add(new Raka(this));
    	allMonstersList.add(new Zap(this));
    	allMonsters.setMonsterList(allMonstersList);
    	
    	setAllItems(new ItemInventory(this));
    	ArrayList<Item> allItemsList = new ArrayList<Item>();
    	allItemsList.add(new IncreaseHealth(this));
    	allItemsList.add(new IncreaseDamage(this));
    	allItemsList.add(new IncreaseCritRate(this));
    	allItemsList.add(new LevelUp(this));
    	allItems.setItemList(allItemsList);
    	
    	setBattleList(new ArrayList<Battle>(numBattles));
    	randomiseBattles();
    	
    	setShopMonsters(new MonsterInventory(this));
    	setShopItems(new ItemInventory(this));
    	randomiseShop();
    };

    
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
     */
    public void setBalance (double balance) {
    	this.balance = balance;
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
    	String regex = "(([a-z]|[A-Z])*(\s)*)*([a-z]|[A-Z])+";
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
	 * @throws InvalidValueException 
	 */
	public void setDay(int day) throws InvalidValueException {
    	if (1 <= day && day <= 15) {
    		this.day = day;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid day! Try again:");
    	}
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
	 * @return the numBattles
	 */
	public int getNumBattles() {
		return numBattles;
	}


	/**
	 * @param numBattles the numBattles to set
	 */
	public void setNumBattles(int numBattles) {
		this.numBattles = numBattles;
	}


	/**
	 * @return the battleList
	 */
	public ArrayList<Battle> getBattleList() {
		return battleList;
	}


	/**
	 * @param battleList the battleList to set
	 */
	public void setBattleList(ArrayList<Battle> battleList) {
		this.battleList = battleList;
	}


	/**
     * Get the value of myMonsters
     * @return the value of myMonsters
     */
    public MonsterInventory getMyMonsters () {
        return myMonsters;
    }
    
    
    /**
     * Set the value of myMonsters
     * @param myMonsters the new value of myMonsters
     */
    public void setMyMonsters (MonsterInventory myMonsters) {
    	this.myMonsters = myMonsters;
    }

    
    /**
     * Get the value of myItems
     * @return the value of myItems
     */
    public ItemInventory getMyItems () {
        return myItems;
    }
    
    
    /**
     * Set the value of myItems
     * @param myItems the new value of myItems
     */
    public void setMyItems (ItemInventory myItems) {
    	this.myItems = myItems;
    }


	/**
	 * @return the allMonsters
	 */
	public MonsterInventory getAllMonsters() {
		return allMonsters;
	}


	/**
	 * @param allMonsters the allMonsters to set
	 */
	public void setAllMonsters(MonsterInventory allMonsters) {
		this.allMonsters = allMonsters;
	}


	/**
	 * @return the allItems
	 */
	public ItemInventory getAllItems() {
		return allItems;
	}
	
	
	/**
	 * @param allItems the allItems o set
	 */
	public void setAllItems(ItemInventory allItems) {
		this.allItems = allItems;
	}


	/**
	 * @return the shopMonsters
	 */
	public MonsterInventory getShopMonsters() {
		return shopMonsters;
	}


	/**
	 * @param shopMonsters the shopMonsters to set
	 */
	public void setShopMonsters(MonsterInventory shopMonsters) {
		this.shopMonsters = shopMonsters;
	}
	
	
	/**
	 * @return the shopItems
	 */
	public ItemInventory getShopItems() {
		return shopItems;
	}


	/**
	 * @param shopItems the shopItems to set
	 */
	public void setShopItems(ItemInventory shopItems) {
		this.shopItems = shopItems;
	}
	
    
	/**
	 * @return the scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}
	
	
	/**
	 * @param scanner the scanner to set
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	
    /**
     * Functional
     * 
     */

	/**
	 * Sleep
	 * 
     */
    public void sleep() {
    	
    }


    /**
     * View the shop page
     */
    public void viewShop() {
    	System.out.println("You have entered the shop.");
    	System.out.println("===== MONSTERS =====");
    	System.out.println(shopMonsters);
    	System.out.println("===== ITEMS =====");
    	System.out.println(shopItems);
    	
    	Scanner input = getScanner();
    	outer:
    	while (true) {
    		String[] inputArray = input.nextLine().toUpperCase().strip().split("\\s+");
    		ArrayList<String> commands = new ArrayList<String>(Arrays.asList(inputArray));
    		String rest = format(String.join(" ", commands.subList(1, commands.size())));
    		try {    		
    			Shop shop = Shop.valueOf(commands.get(0));
    			switch (shop) {
    				case BUY:
    					if (shopMonsters.contains(rest)) {
    						Monster monster = shopMonsters.find(rest);
    						monster.buy();
    						shopMonsters.remove(monster);
    						System.out.println("You bought: " + monster.getName());
    					}
    					else {
    						if (shopItems.contains(rest)) {
    							Item item = shopItems.find(rest);
    							item.buy();
    							shopItems.remove(item);
    							System.out.println("You bought: " + item.getName());
    						}
    						else {
    							throw new PurchasableNotFoundException("Purchasable not found in shop!");
    						}
    					}
    					break;
    				case SELL:
    					if (myMonsters.contains(rest)) {
    						Monster monster = myMonsters.find(rest); 
    						monster.sell();
    						System.out.println("You sold: " + monster.getName());
    					}
    					else {
    						if (myItems.contains(rest)) {
    							Item item = myItems.find(rest); 
    							item.sell();
    							System.out.println("You sold: " + item.getName());
    						}
    						else {
    							throw new PurchasableNotFoundException("Purchasable not found in team or inventory!");
    						}
    					}
    					break;
    				case LEAVE:
    					System.out.println("You have left the shop.");
    					break outer;
    			}
    		}
    		catch (IllegalArgumentException e) {
    			System.out.println("Command not found! Try again:");
    		}
    		catch (InventoryFullException | InsufficientFundsException | PurchasableNotFoundException e) {
    			System.out.println(e.getMessage() + " Try again:");
    		}
		}
    }


    /**
     * View the possible battles
     */
    public void viewBattles() {
    	System.out.println("===== BATTLES =====");
    	for (int i = 0; i < battleList.size(); i++)
    	{
    		Battle battle = battleList.get(i);
    		System.out.println(String.format("===== Battle %s =====", i + 1));
    		System.out.println(battle);
    	}
    }


    /**
     * View my monsters
     */
    public void viewMonsters() {
    	System.out.println("===== MY MONSTERS =====");
    	System.out.println(myMonsters);
    }


    /**
	 * View the game statistics
	 */
	public void viewStats() {
		System.out.println("===== PLAYER STATS =====");
		System.out.println("Balance: " + getBalance());
		System.out.println("Player name: " + getPlayerName());
		System.out.println(String.format("Day %s out of %s", getDay(), getNumDays()));
		System.out.println("Difficulty: " + getDifficulty());
		// print current score and other stats about past battles
	}


	/**
	 * View my items
	 */
	public void viewItems() {
		System.out.println("===== MY ITEMS =====");
		System.out.println(myItems);
	}


	/**
	 * 1. Set player name and request a different name if necessary.
	 * 2. Set number of days and request a different input if necessary.
	 * 3. Set difficulty.
	 * 4. Select starting monster and set monster name if not using the default.
	 * @throws InventoryFullException 
	 */
	public void setupGame() throws InventoryFullException {
		selectPlayerName();
		selectNumDays();
		selectDifficulty();
		selectStartingMonster();
	}


	/**
	 * Let the player pick a name.
	 * 
	 */
	public void selectPlayerName() {
    	Scanner input = getScanner();
		System.out.println("Select a player name (between 3 - 15 characters"
						+ " containing no numbers or special characters):");
		while (getPlayerName() == null) {
			String name = input.nextLine();
			try {
				setPlayerName(name);
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("Nice to meet you %s!", getPlayerName()));
    }
    
    
	/**
	 * Let the player pick a number of days
	 * 
	 */
    public void selectNumDays() {
    	Scanner input = getScanner();
		System.out.println("Select a number of days (between 5 - 15):");
		while (getNumDays() == 0) {
			try {
				int numDays = Integer.parseInt(input.nextLine().strip());
				setNumDays(numDays);
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a number! Try again:");
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("You chose: %s days.", getNumDays()));
    }
    
    
    /**
     * Let the player pick a difficulty
     * 
     */
    public void selectDifficulty() {
    	Scanner input = getScanner();
		System.out.println("Select a difficulty level (easy, normal, hard):");
		while (difficulty == null) {
			try {
				String inputStr = input.nextLine().toUpperCase().strip();
				Difficulty difficulty = Difficulty.valueOf(inputStr);
				setDifficulty(difficulty);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid difficulty! Try again:");
			}
		}
		System.out.println(String.format("You chose: %s.", getDifficulty()));
    }


    /**
     * Let the player pick a starting monster
     * @throws InventoryFullException 
     * 
     */
	public void selectStartingMonster() throws InventoryFullException {
		Scanner input = getScanner();
		System.out.println("Select a starting monster (average joe, chunky, lanky, shanny, raka, zap):");
		while (getMyMonsters().getMonsterList().size() == 0) {
			String inputStr = input.nextLine();
			String monsterName = format(inputStr);
			try {
				if (getAllMonsters().contains(monsterName)) {				
					Class<? extends Monster> clazz = getAllMonsters().find(monsterName).getClass();
					Monster monster = clazz.getConstructor(GameEnvironment.class).newInstance(this);
					getMyMonsters().add(monster);
				}
				else {
					throw new PurchasableNotFoundException("Invalid starting monster!");
				}
			}
			catch (PurchasableNotFoundException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				System.out.println(e.getMessage() + " Try again:");
			}				
		}
		System.out.println(String.format("You chose:\n%s", getMyMonsters()));
    }
	
	
	/**
	 * Select a battle to fight.
	 * @throws InvalidValueException 
	 * @throws InvalidTargetException 
	 * @throws NumberFormatException
	 * @throws IndexOutOfBoundsException
	 */
	public void selectBattle(String battleString) throws InvalidValueException, InvalidTargetException, NumberFormatException, IndexOutOfBoundsException {
		if (myMonsters.getMonsterList().size() == 0) {
			throw new IllegalArgumentException();
		}
		try {
			int index = Integer.parseInt(battleString);
			if (1 <= index && index <= battleList.size()) {				
				Battle battle = battleList.get(index - 1);
				battle.play();			
			}
			else {
				throw new IndexOutOfBoundsException();
			}
		}
		catch (NumberFormatException | IndexOutOfBoundsException e) {
			throw e;
		}
	}
		

    /**
     * Add to the player balance
     * @param amount
     */
    public void addBalance(double amount) {
		balance += amount;
    }
    
    
    /**
     * Remove from the player balance
     * @param amount
     * @throws InsufficientFundsException
     */
    public void minusBalance(double amount) throws InsufficientFundsException {
		if (balanceSufficient(amount)) {
			balance -= amount;
    	}
    	else {
    		throw new InsufficientFundsException("Insufficient funds!");
    	}
    }
    
    
    /**
     * Returns whether balance is sufficient for the given cost
     * @param amount the cost
     * @return whether balance is sufficient
     */
    public boolean balanceSufficient(double amount) {
    	return balance >= amount;
    }
    
    
    /**
     * Randomise the battles in battleList.
     * @throws InventoryFullException 
     */
    public void randomiseBattles() throws InventoryFullException {
    	for (int i = 0; i < numBattles; i++) {
    		MonsterInventory monsterInventory = new MonsterInventory(this);
    		monsterInventory.randomiseInventory();
    		Battle battle = new Battle(this, monsterInventory);
    		battleList.add(battle);
    	}
    }


    /**
     * Randomise the purchasables in the shop.
     * @throws InventoryFullException 
     */
    public void randomiseShop() throws InventoryFullException {
    	shopMonsters.randomiseInventory();
    	shopItems.randomiseInventory();
    }
    
    
    public void run() throws InventoryFullException {
    	Scanner input = getScanner();
    	outer:
    	while (true) {    		
    		String[] inputArray = input.nextLine().toUpperCase().strip().split("\\s+");
    		ArrayList<String> commands = new ArrayList<String>(Arrays.asList(inputArray));
    		String rest = format(String.join(" ", commands.subList(2, commands.size())));
    		try {    		
    			Command command = Command.valueOf(commands.get(0));
    			switch (command) {
	    			case VIEW:
	    				View view = View.valueOf(commands.get(1));
	    				switch (view) {
	    					case SHOP:
	    						viewShop();
	    						break;
	    					case MONSTERS:
	    						viewMonsters();
	    						break;
	    					case ITEMS:
	    						viewItems();
	    						break;
	    					case BATTLES:
	    						viewBattles();
	    						break;
	    					case STATS:
	    						viewStats();
	    						break;
	    				}
	    				break;
	    			case SELECT:
	    				Select select = Select.valueOf(commands.get(1));
	    				switch (select) {
	    					case NAME:
	    						selectPlayerName();
	    						break;
	    					case DAYS:
	    						selectNumDays();
	    						break;
	    					case DIFFICULTY:
	    						selectDifficulty();
	    						break;
	    					case MONSTER:
	    						selectStartingMonster();
	    						break;
	    					case BATTLE:
	    						selectBattle(rest);
	    						break;
	    				}
	    				break;
	    			case QUIT:
	    				break outer;
    			}
    		}
    		catch (IllegalArgumentException | IndexOutOfBoundsException e) {
    			e.printStackTrace();
    			System.out.println("Command not found! Try again:");
    		}
    		catch (InvalidValueException | InvalidTargetException  e) {
				e.printStackTrace();
			}
    	}
    	input.close();
    }
    
    
    /**
     * Returns a proper case version of the given phrase with all redundant whitespace removed.
     * @param phrase to be formatted
     * @return the formatted phrase
     */
    public String format(String phrase) {
    	String[] words = phrase.toLowerCase().strip().split("\\s+");
    	if (String.join("", words).equals("")) {
    		return "";
    	}
    	for (int i = 0; i < words.length; i++) {
    		String word = words[i];
    		String first = word.substring(0, 1);
    		if (first.matches("[a-zA-Z]")) {
    			first = first.toUpperCase();
    		}
    		String rest = word.substring(1, word.length());   
    		words[i] = first + rest;
    	}
    	String result = String.join(" ", words);
		return result;
    }
    
    
    public static void main(String[] args) throws InventoryFullException, InvalidValueException {
    	GameEnvironment game = new GameEnvironment();
    	game.setBalance(100);
    	game.setupGame();
    	game.run();
    }

}
