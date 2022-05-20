package main;

import java.util.*;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;
import monsters.Raka;

/**
 * This class launches the CMD game and manages the player input and written output.
 * @author Farzad and Daniel
 */
public class CommandLine {

	/**
	 * Fields
	 */
    private Scanner scanner = new Scanner(System.in);
    private int selection;
    private boolean forceStop = false;

    private GameEnvironment game = GameEnvironment.getInstance();
    private Player player;
    private Shop shop;
        
    /**
     * Constructors
     */
    
    /**
     * Creates a new CommandLine object.
     * Start the command line game.
     */
    public CommandLine() {
    	start();
    	if(forceStop != true) {
        	run();
    	}
    }

    
    /**
     * Getters and setters
     */
    
	/**
	 * Get the value of scanner
	 * @return the value of scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}
	
	
	/**
	 * set the value of scanner
	 * @param scanner the new value of scanner
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	

	/**
	 * Functional
	 */
	
    /**
     * Ask the player if they want to play or not.
     * If yes, then run the command line game setup.
     * If no, then quit the game.
     */
    public void start() {
    	System.out.println("Would you like to play the game?\n1. Yes\n2. No");
    	outer:
	    	while (true) {    		
	    		try {
	    			int choice = scanner.nextInt();
	    			scanner.nextLine();
	    			switch (choice) {
	    			case 1:
	    				setupCmdGame();
	    				break outer;
	    			case 2:
	    				System.out.println("Bye!");
	    				forceStop = true;
	    				break outer;
	    			default:
	    				throw new IllegalArgumentException("Command not found! Try again:");
	    			}
	    		}
	    		catch (IllegalArgumentException e) {
	    			System.out.println(e.getMessage());
	    		}
	    		catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
	    	}
    }
	
	
	/**
	 * Setup the command line game.
	 * 1. Set the player name.
	 * 2. Set the number of days.
	 * 3. Set the difficulty.
	 * 4. Run game setup to populate necessary game information.
	 * 5. Select a starting monster.
	 */
	public void setupCmdGame() {
    	player = game.getPlayer();
    	shop = game.getShop();
		selectPlayerName();
		selectNumDays();
		selectDifficulty();
		game.populateGame();
		selectStartingMonster();
	}
	
	
	/**
	 * Ask the player to pick a player name.
	 * The name must be between 3 to 15 characters long and contain no numbers or special characters.
	 */
	public void selectPlayerName() {
		System.out.println("Select a player name (3 - 15 characters"
						+ " containing no numbers or special characters):");
		while (true) {
			String name = scanner.nextLine();
			try {
				player.setName(name);
				break;
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(String.format("Nice to meet you %s!", player.getName()));
    }
    
    
	/**
	 * Ask the player to pick a number of days to play for.
	 * The number of days must be between 5 to 15 days.
	 */
    public void selectNumDays() {
		System.out.println("Select a number of days (between 5 - 15):");
		outer:
			while (true) {
				try {
					int numDays = scanner.nextInt();
					scanner.nextLine();
					game.setNumDays(numDays);
					break outer;
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
				catch (InvalidValueException e) {
					System.out.println(e.getMessage());
				}
			}
		System.out.println(String.format("You chose: %s days.", game.getNumDays()));
    }
    
    
    /**
     * Ask the player to pick a difficulty.
     * The options are Easy, Normal, or Hard.
     */
    public void selectDifficulty() {
		System.out.println("Select a difficulty level\n1. Easy\n2. Normal\n3. Hard");
		outer:
			while(true) {
	        	try {
	        		int choice = scanner.nextInt();
	        		scanner.nextLine();
	        		switch (choice) {
	        		case 1:
	        			game.setDifficulty(Difficulty.EASY);
	        			break outer;
	        		case 2:
	        			game.setDifficulty(Difficulty.NORMAL);
	        			break outer;
	        		case 3:
	        			game.setDifficulty(Difficulty.HARD);
	        			break outer;
	        		default:
	        			throw new IllegalArgumentException("Command not found! Try again:");
	        		}
	        	}
	        	catch (IllegalArgumentException e) {
	    			System.out.println(e.getMessage());
	    		}
	        	catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
	    	}
		System.out.println(String.format("You chose: %s.", game.getDifficulty()));
    }


    /**
     * Print a list of all monsters and ask the player to pick a starting monster.
     * The choices are Average Joe, Chunky, Lanky, Shanny, Raka, or Zap.
     */
	public void selectStartingMonster() {
		System.out.println("\nSelect a starting monster:\n");
		System.out.println(game.getAllMonsters().view());
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (0 < selection && selection <= game.getAllMonsters().size()) {
					Monster monster = game.getAllMonsters().get(selection - 1).clone();
					if(monster instanceof Raka) {
		    			((Raka) monster).setTeam(player.getMonsters());
		    		}
					System.out.println("You chose: " + monster.getName());
					player.getMonsters().add(monster);
					break;
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
	}
	
	
	/**
	 * Rename of the given monster
	 * The monster name must be between 3 to 15 characters long and contain no numbers or special characters.
	 * @param monster the monster to be renamed
	 */
	public void selectMonsterName(Monster monster) {
		System.out.println("Select a unique monster name (3 - 15 characters"
				+ " containing no numbers or special characters):");
		while (true) {
			String name = scanner.nextLine();
			try {
				String nameBefore = monster.getName();
				monster.setName(name);
				System.out.println(String.format("%s has been renamed to %s.", nameBefore, monster.getName()));
				goBack();
				break;
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}
    
    
	/**
	 * Print the shop contents and allow the user to either view a monster, view an item, or go back to the home page.
	 */
    public void viewShop() {
    	System.out.println(shop);
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				
				int monstersSize = shop.getMonsters().size();
				int itemsSize = shop.getItems().size();
				int combinedSize = monstersSize + itemsSize; 
				
				if (combinedSize == selection - 1) {
					break;
				}
				if (0 < selection && selection <= monstersSize) {
					int index = selection - 1;
					Monster monster = shop.getMonsters().get(index);
					viewShopMonster(monster);
		    		System.out.println(shop);
				}
				else {						
					if (monstersSize < selection && selection <= combinedSize) {
						int index = selection - monstersSize - 1;
						Item item = shop.getItems().get(index);
						viewShopItem(item);
						System.out.println(shop);
					}
					else {
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
    }
    
    
    /**
     * Print the full details of the given monster and allow the user to either buy or go back to the shop page.
     * @param monster the given monster
     */
    public void viewShopMonster(Monster monster) {
    	System.out.println(monster.view());
    	outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
					case 1:
						System.out.println(monster.buy());
						goBack();
						break outer;
					case 2:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (InvalidValueException | InsufficientFundsException | InventoryFullException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
    }
    
    
    /**
	 * Print the full details of the given item and allow the user to either buy or go back to the shop page.
	 * @param item the given item
	 */
	public void viewShopItem(Item item) {
		System.out.println(item.view());
		outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
					case 1:
						System.out.println(item.buy());
						goBack();
						break outer;
					case 2:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (InvalidValueException | InsufficientFundsException | InventoryFullException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
	}


	/**
     * Print a list of the player's monsters and allow them to either view a monster or go back to the home page.
     */
    public void viewTeam() {
    	System.out.println(player.viewMonsters());
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (player.getMonsters().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= player.getMonsters().size()) {
					Monster monster = player.getMonsters().get(selection - 1);
					viewPlayerMonster(monster);
					System.out.println(player.viewMonsters());
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
    }


    /**
	 * Print the full details of the given monster and allow the user to either rename, sell, or go back to my team page.
	 * @param monster the given monster
	 */
	public void viewPlayerMonster(Monster monster) {
		System.out.println(monster.view());
		outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
					case 1:
						selectMonsterName(monster);
						System.out.println(monster.view());
						break;
					case 2:
						System.out.println(monster.sell());
						goBack();
						break outer;
					case 3:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (InvalidValueException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
	}


	/**
     * Print a list of the player's items and allow them to either view an item or go back to the home page.
     */
    public void viewInventory() {
    	System.out.println(player.viewItems());
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (player.getItems().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= player.getItems().size()) {
					Item item = player.getItems().get(selection - 1);
					viewPlayerItem(item);
					System.out.println(player.viewItems());
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
    }
    
    
    /**
	 * Print the full details of the given item and allow the user to either use, sell, or go back to my inventory page.
	 * @param item the given item
	 */
	public void viewPlayerItem(Item item) {
		System.out.println(item.view());
		outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
					case 1:
						useItem(item);
						break outer;
					case 2:
						System.out.println(item.sell());
						goBack();
						break outer;
					case 3:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (InvalidValueException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
	}


	/**
	 * Print the player's team and allow the user to pick a monster to either apply the item to or go back to the item page.
	 * @param item the given item
	 */
	public void useItem(Item item) {
		System.out.println("Choose a monster to use it on:");
		System.out.println(player.viewMonsters());
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (player.getMonsters().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= player.getMonsters().size()) {
					Monster monster = player.getMonsters().get(selection - 1);
					item.use(monster);
					System.out.println(String.format("You used %s on %s:", item.getName(), monster.getName()));
					System.out.println(monster);
					goBack();
					break;
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (StatMaxedOutException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				System.out.println("Choose a monster to use it on:");
				System.out.println(player.viewMonsters());
			}
			catch (InputMismatchException e) {
				System.out.println("Command not found! Try again:");
				scanner.nextLine();
			}
		}
	}


	/**
	 * Print a list of all the battles and allow the user to either view a battle or go back to the home page.
	 */
	public void viewBattles() {
		System.out.println(game.getBattles().view());
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getBattles().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getBattles().size()) {
					Battle battle = game.getBattles().get(selection - 1); 
					viewBattle(battle);
			    	System.out.println(game.getBattles().view());
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.println("Command not found! Try again:");
				scanner.nextLine();
			}
		}
	}


	/**
	 * Print the full details of the given battle and allow the user to either fight the battle or go back the battles page.
	 * @param battle the given battle
	 */
	public void viewBattle(Battle battle) {
		System.out.println(battle.view());
		outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
					case 1:
						playBattle(battle);
						break outer;
					case 2:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
	}


	/**
	 * Play the given battle using command line to control the pace of the battle.
	 * The player must press Enter to play the next turn in the battle and continue until the battle is over.
	 * Checks the player team before the game to make sure they have at least one non fainted monster.
	 * Checks the status of the battle after each turn.
	 * @param battle the given battle currently being played
	 */
	public void playBattle(Battle battle) {
		try {
			battle.setup();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			goBack();
			return;
		}
		System.out.println("Press Enter to play next turn...");
		while (battle.getWinner() == null) {
			scanner.nextLine();
			System.out.println(battle.playTurn());
			System.out.println(battle.checkStatus());
		}
		game.getBattles().remove(battle);
		goBack();
	}


	/**
     * Print the game statistics and allow the player to go back to the home page.
     */
    public void viewStats() {
    	printStats();
    	goBack();
    }
    
    
    /**
     * Print the following:
     * 1. Balance
     * 2. Player name
     * 3. The current day
     * 4. Difficulty
     * 5. Today's score and total score
     * 6. Today's battles won and total battles won
     */
    public void printStats() {
    	System.out.println("\n===== PLAYER STATS =====\n");
    	System.out.println("Balance: " + player.getBalance());
    	System.out.println("Player name: " + player.getName());
    	System.out.println(String.format("Day: %s out of %s", game.getDay(), game.getNumDays()));
    	System.out.println("Difficulty: " + game.getDifficulty());
    	System.out.println("Today score: " + game.getScoreSystem().getDayScore());
    	System.out.println("Total score: " + game.getScoreSystem().getTotalScore());
    	System.out.println(String.format("Today battles: %s won out of %s", game.getScoreSystem().getDayBattlesWon(), 
    			game.getScoreSystem().getDayBattlesWon() + game.getScoreSystem().getDayBattlesLost()));
    	System.out.println(String.format("Total battles: %s won out of %s", game.getScoreSystem().getTotalBattlesWon(), 
    			game.getScoreSystem().getTotalBattlesWon() + game.getScoreSystem().getTotalBattlesLost()));
    	if (game.getIsFinished()) {
    		System.out.println(String.format("\nFinal score: %s (+%s bonus)", 
					game.getScoreSystem().finalScore(), 
					game.getScoreSystem().scoreBonus()));
    		System.out.println("\n<<<<< Game over! >>>>>");
    	}
    }
    
    
    /**
	 * Print the events that occurred over night and allow the user to go back to the home page.
	 */
	public void viewSleep() {
		System.out.println(game.sleep());
		if (!game.getIsFinished()) {			
			goBack();
		}
	}


	/**
     * Allow the player to go back to the previous page by entering a certain key.
     * This method can be added to an other method in order to add a go back buffer.
     */
    public void goBack() {
    	System.out.println("\n1. Go back");
    	outer:
	    	while (true) {
	    		try {
	    			int selection = scanner.nextInt();
	    			scanner.nextLine();
	    			switch (selection) {
	    			case 1:
	    				break outer;
	    			default:
	    				throw new IllegalArgumentException("Command not found! Try again:");
	    			}
	    		}
	    		catch (IllegalArgumentException e) {
	    			System.out.println(e.getMessage());
	    		}
	    		catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
	    	}
    }
    
	
	/**
	 * Print a list of the home page menu.
	 */
	public void viewHome() {
		System.out.println("\nHome:");
		System.out.println("1: Shop");
		System.out.println("2: Team");
		System.out.println("3: Inventory");
		System.out.println("4: Battles");
		System.out.println("5: Stats");
		System.out.println("6: Sleep");
	}
	
    
	/**
	 * Print the home page and run the main command line loop.
	 * The loop continues until the game has finished.
	 * The final stats will be printed once the loop has stopped.
	 */
    public void run() {
    	viewHome();
    	while (!game.getIsFinished()) {
    		try {
    			selection = scanner.nextInt();
    			scanner.nextLine();
    			switch (selection) {
    				case 1:
    					viewShop();
    					break;
    				case 2:
    					viewTeam();
    					break;
    				case 3:
    					viewInventory();
    					break;
    				case 4:
    					viewBattles();
    					break;
    				case 5:
    					viewStats();
    					break;
    				case 6:
    					viewSleep();
    					break;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
				}
    			if (!game.getIsFinished()) {
    				viewHome();
    			}
    		}
    		catch (IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    		} catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
    	}
    	printStats();
    }
    
    
    /**
     * Returns a proper case version of the given phrase with the first letter of each word in upper case and all redundant whitespace removed.
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
    
    
    /**
     * the main call to start the command line game
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	new CommandLine();
    }
    
}