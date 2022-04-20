package main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import main.GameEnvironment.Difficulty;

public class CommandLine {

    private Scanner scanner = new Scanner(System.in);
    private GameEnvironment game;
    
    private enum Command {
    	VIEW,
    	SELECT,
    	SHOP,
    	SLEEP,
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
    
    public enum Trade {
    	BUY,
    	SELL
    }
	
    
    public CommandLine(GameEnvironment game) {
    	this.game = game;
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
		while (game.getPlayerName() == null) {
			String name = input.nextLine();
			try {
				game.setPlayerName(name);
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("Nice to meet you %s!", game.getPlayerName()));
    }
    
    
	/**
	 * Let the player pick a number of days
	 * 
	 */
    public void selectNumDays() {
    	Scanner input = getScanner();
		System.out.println("Select a number of days (between 5 - 15):");
		while (game.getNumDays() == 0) {
			try {
				int numDays = Integer.parseInt(input.nextLine().strip());
				game.setNumDays(numDays);
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a number! Try again:");
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("You chose: %s days.", game.getNumDays()));
    }
    
    
    /**
     * Let the player pick a difficulty
     * 
     */
    public void selectDifficulty() {
    	Scanner input = getScanner();
		System.out.println("Select a difficulty level (easy, normal, hard):");
		while (game.getDifficulty() == null) {
			try {
				String inputStr = input.nextLine().toUpperCase().strip();
				Difficulty difficulty = Difficulty.valueOf(inputStr);
				game.setDifficulty(difficulty);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid difficulty! Try again:");
			}
		}
		System.out.println(String.format("You chose: %s.", game.getDifficulty()));
    }


    /**
     * Let the player pick a starting monster
     * @throws InventoryFullException 
     * 
     */
	public void selectStartingMonster() throws InventoryFullException {
		Scanner input = getScanner();
		System.out.println("Select a starting monster (average joe, chunky, lanky, shanny, raka, zap):");
		while (game.getMyMonsters().size() == 0) {
			String inputStr = input.nextLine();
			String monsterName = properCase(inputStr);
			try {
				if (game.getAllMonsters().contains(monsterName)) {				
					Class<? extends Monster> clazz = game.getAllMonsters().find(monsterName).getClass();
					Monster monster = clazz.getConstructor(GameEnvironment.class).newInstance(game);
					game.getMyMonsters().add(monster);
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
		System.out.println(String.format("You chose:\n%s", game.getMyMonsters()));
    }
    
	


    /**
     * View the shop page
     */
    public void viewShop() {
    	System.out.println("===== MONSTERS =====");
    	System.out.println(game.getShop().getMonsters());
    	System.out.println("===== ITEMS =====");
    	System.out.println(game.getShop().getItems());
    }


    /**
     * View the possible battles
     */
    public void viewBattles() {
    	System.out.println("===== BATTLES =====");
    	for (int i = 0; i < game.getBattles().size(); i++)
    	{
    		Battle battle = game.getBattles().get(i);
    		System.out.println(String.format("===== Battle %s =====", i + 1));
    		System.out.println(battle);
    	}
    }


    /**
     * View my monsters
     */
    public void viewMonsters() {
    	System.out.println("===== MY MONSTERS =====");
    	System.out.println(game.getMyMonsters());
    }


    /**
	 * View the game statistics
	 */
	public void viewStats() {
		System.out.println("===== PLAYER STATS =====");
		System.out.println("Balance: " + game.getBalance());
		System.out.println("Player name: " + game.getPlayerName());
		System.out.println(String.format("Day %s out of %s", game.getDay(), game.getNumDays()));
		System.out.println("Difficulty: " + game.getDifficulty());
		// print current score and other stats about past battles
	}


	/**
	 * View my items
	 */
	public void viewItems() {
		System.out.println("===== MY ITEMS =====");
		System.out.println(game.getMyItems());
	}


	/**
	 * Select a battle to fight.
	 * @throws InvalidValueException 
	 * @throws InvalidTargetException 
	 * @throws NumberFormatException
	 * @throws IndexOutOfBoundsException
	 */
	public void selectBattle(String battleString) throws InvalidValueException, InvalidTargetException, NumberFormatException, IndexOutOfBoundsException {
		if (game.getMyMonsters().size() == 0) {
			throw new IllegalArgumentException();
		}
		try {
			int index = Integer.parseInt(battleString);
			if (1 <= index && index <= game.getBattles().size()) {				
				Battle battle = game.getBattles().get(index - 1);
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
     * @param view
     * @throws IllegalArgumentException
     */
    public void viewSwitch(View view) throws IllegalArgumentException {
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
    }
    
    
    /**
     * @param select
     * @param rest
     * @throws InventoryFullException
     * @throws NumberFormatException
     * @throws IndexOutOfBoundsException
     * @throws InvalidValueException
     * @throws InvalidTargetException
     */
    public void selectSwitch(Select select, String rest) throws InventoryFullException, NumberFormatException, IndexOutOfBoundsException, InvalidValueException, InvalidTargetException {
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
    }
    
    
    /**
     * @param shop
     * @param rest
     */
    public void tradeSwitch(Trade trade, String rest) {
    	try {
    		switch (trade) {
	    		case BUY:
	    			game.getShop().buy(rest);
	    			break;
	    		case SELL:
	    			game.getShop().sell(rest);
	    			break;
    		}
    	}
		catch (InventoryFullException | InsufficientFundsException | PurchasableNotFoundException e) {
			System.out.println(e.getMessage() + " Try again:");
		}
    }
    
    
    public void run() throws InventoryFullException {
    	Scanner input = getScanner();
    	outer:
    	while (!game.isFinished()) {    		
    		String[] inputArray = input.nextLine().toUpperCase().strip().split("\\s+");
    		ArrayList<String> commands = new ArrayList<String>(Arrays.asList(inputArray));
    		String rest;
    		try {    		
    			Command command = Command.valueOf(commands.get(0));
    			switch (command) {
	    			case VIEW:
	    				View view = View.valueOf(commands.get(1));
	    				viewSwitch(view);
	    				break;
	    			case SELECT:
	    				Select select = Select.valueOf(commands.get(1));
	    				rest = properCase(String.join(" ", commands.subList(2, commands.size())));
	    				selectSwitch(select, rest);
	    				break;
	    			case SHOP:
	    				Trade trade = Trade.valueOf(commands.get(1));
	    				rest = properCase(String.join(" ", commands.subList(2, commands.size())));
	    				tradeSwitch(trade, rest);
	    				break;
	    			case SLEEP:
	    				game.sleep();
	    				break;
	    			case QUIT:
	    				break outer;
    			}
    		}
    		catch (IllegalArgumentException | IndexOutOfBoundsException e) {
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
    public String properCase(String phrase) {
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
	
}
