package monsters;

import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.StatMaxedOutException;
import main.*;

/**
 * A monster subclass.
 * An ancient being from decades ago, Raka swaps between a healer
 * and an attacker. Its offensive ability can be overlooked but
 * not its healing ability. With this monster in the team, monsters
 * will definitely get a substantial boost!
 * @author Farzad and Daniel
 */
public class Raka extends Monster {
	
	/**
	 * Fields
	 */
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Raka";
	private static String description = "An ancient being from decades ago, Raka swaps between a healer and an attacker. Its offensive ability can be overlooked but not its healing ability. With this monster in the team, monsters will definitely get a substantial boost!";
	private static int defaultMaxHealth = 80;
	private static int defaultDamage = 10;
	private static int defaultCost = 70;
	private static int defaultHealAmount = (int) (0.2*defaultMaxHealth);
	private static double defaultCritRate = 0.1;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.1*getMaxHealth());
	private int levelUpDamage = 5;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.1*getMaxHealth());
	
	private MonsterInventory team;
	private Monster randomMonster; 
	private Random random = new Random();
	private int choice; // controls whether raka attacks or heals
	
	/**
	 * Constructors
	 */
	
	/**
	 * Creates Raka object by calling superclass constructor and passing in the default values
	 * @param game given GameEnvironment object
	 */
	public Raka(GameEnvironment game) {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate, game);
    };
    
    
    /**
     * Getters and Setters 
     */
    
    /**
     * Get the value of team
	 * @return the value of team 
	 */
	public MonsterInventory getTeam() {
		return team;
	}

	
	/**
	 * Set the value of team
	 * @param team the new value of team
	 */
	public void setTeam(MonsterInventory team) {
		this.team = team;
	}


	/**
     * Get the value of randomMonster
     * @return the value of randomMonster
     */
	public Monster getRandomMonster() {
		return randomMonster;
	}


	/**
     * Set the value of randomMonster
     * @param randomMonster the new value of randomMonster
     */
	public void setRandomMonster(Monster randomMonster) {
		this.randomMonster = randomMonster;
	}


	/**
     * Get the value of random
     * @return the value of random
     */
	public Random getRandom() {
		return random;
	}


	/**
     * Set the value of random
     * @param random the new value of random
     */
	public void setRandom(Random random) {
		this.random = random;
	}
	
	
	/**
     * Get the value of choice
     * @return the value of choice
     */
	public int getChoice() {
		return choice;
	}
	
	
	/**
     * Set the value of choice
     * @param choice the new value of choice
     */
	public void setChoice(int choice) {
		this.choice = choice;
	}


    /**
     * Functional
     */
    
    /**
     * Get the value of healingAmount 
     * @return the value of healingAmount
     */
    public int getHealingAmount() {
    	return getHealAmount();
    }
    
    
    /**
     * Heals given Monster object
     * @param other given Monster object
     * @throws InvalidValueException if heal amount is negative or zero
     * @throws InvalidTargetException if the target is fainted
     */
    public void healAllies(Monster other) throws InvalidTargetException, InvalidValueException {
    	if(!other.getIsFainted()) {
    		other.heal(getHealAmount());
    	}
    	else {
    		throw new InvalidTargetException("Invalid target!");
    	}
    	
    }

    
    /**
     * Level up monster statistics relevant to the monster 
     * @throws StatMaxedOutException if monster is already at maximum level
     */
    public void levelUp() throws StatMaxedOutException
    {
    	super.levelUp();
    	setMaxHealth(getMaxHealth()+levelUpHealth);
    	setDamage(getDamage()+levelUpDamage);
    	setCost(getCost()+levelUpCost);
    	setHealAmount(getHealAmount()+levelUpHealAmount);
    	setHealth(getHealth()+levelUpHealth);
    }
    
    
    /**
     * Randomly selects whether to heal an ally or attack an enemy monster.
     * @param other the given enemy monster
     */
    public int attack(Monster other) throws InvalidTargetException, InvalidValueException {
		choice = random.nextInt(10);
		if(choice <= 2) {
    		randomMonster = team.random();
    		while(randomMonster.getIsFainted()) {
    			randomMonster = team.random();
    		}
    		this.healAllies(randomMonster);
    		return getHealAmount();
		}
		else {
			return super.attack(other);
		}
    }
    
    
    /**
     * @return new Raka instance
     */
    public Monster clone() {
    	Raka cloneInst = new Raka(game);
    	int correctLevel = game.getAllMonsters().random().getLevel();
    	for(int i = 0; i < correctLevel - 1; i++) {
    		try {
				cloneInst.levelUp();
			} catch (StatMaxedOutException e) {
				// Error within code
				e.printStackTrace();
			}
    	}
    	return cloneInst;
    }
    
}
