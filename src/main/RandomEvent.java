package main;

import java.util.*;

public class RandomEvent {
    
	/**
	 * Fields
	 */
	
    private double levelUpChance = 0.1;
    private double leaveChance = 0.05;
    private double joinChance = 0.1;
    private double leaveIncrement = 2.0;
    private double levelUpIncrement = 0.1;
    
    private GameEnvironment game;
    private Player player;
    
    private Random rn = new Random();
    
    
    /**
     * Constructors 
     */
    
    /**
     * Creates RandomEvent object 
     * Sets game to the given GameEnvironment object
     * @param game given GameEnvironment object
     */
    public RandomEvent(GameEnvironment game) {
    	this.game = game;
    	player = game.getPlayer();
    }
    
    
    /**
     * Getters and Setters
     */
    
    /**
     * Get the value of levelUpChance
     * @return the value of levelUpChance
     */
    public double getLevelUpChance() {
    	return levelUpChance;
    }
    
    
    /**
     * Set the value of levelUpChance
     * @param levelUpChance the new value of levelUpChance
     */
    public void setLevelUpChance(double levelUpChance) {
    	this.levelUpChance = levelUpChance;
    }
    
    
    /**
     * Get the value of leaveChance
     * @return the value of leaveChance
     */
    public double getLeaveChance() {
    	return leaveChance;
    }
    
    
    /**
     * Set the value of leaveChance
     * @param leaveChance the new value of leaveChance
     */
    public void setLeaveChance(double leaveChance) {
    	this.leaveChance = leaveChance;
    }
    
    
    /**
     * Get the value of joinChance
     * @return the value of joinChance
     */
    public double getJoinChance() {
    	return joinChance;
    }
    
    
    /**
     * Set the value of joinChance
     * @param joinChance the new value of joinChance
     */
    public void setJoinChance(double joinChance) {
    	this.joinChance = joinChance;
    }
    
    
    /**
     * Functional
     */
    
    /**
     * Level up given Monster object based on the levelUpChance
     * @param monster given Monster object 
     * @return event the string representing a successful level up
     */
    public String randomMonsterLevelUp(Monster monster) {
    	String event = "";
		double resetValue = levelUpChance;
		//Possible mechanism: levelUpChance increases with the number of battles won in a day
		levelUpChance += levelUpIncrement*game.getScoreSystem().getDayBattlesWon();
		double randomValue = rn.nextDouble(1);
		if(randomValue <= levelUpChance) {
			try {
				monster.levelUp();
				event = String.format("%s has leveled up over night!\n", monster.getName());
			}
			catch (StatMaxedOutException e) {}
		}
		setLevelUpChance(resetValue);
		return event;
    }
    
    
    /**
     * Monster leaves the player's team based on the leaveChance
     * @param monster given Monster object 
     * @return event the string representing when a monster leaves the team
     * @throws NotFoundException if monster not found in player's inventory
     */
    public String randomMonsterLeave(Monster monster) throws NotFoundException {
    	String event = "";
		double resetValue = leaveChance;
		//If monster fainted during any battle in the day, leaveChance is doubled 
		if(monster.getIsFainted()) {
		    leaveChance *= leaveIncrement;
		}
		double randomValue = rn.nextDouble(1);
		if(randomValue <= leaveChance) {
		    player.getMonsters().remove(monster);
		    event = String.format("Sadly, %s left the team over night...\n", monster.getName());
		}
		setLeaveChance(resetValue);
		return event;
    }
    

    /**
     * Random monster joins the player's team based on the joinChance
     * @return event the string representing when a monster joins the team 
     */
    public String randomMonsterJoin() {
    	String event = "";
		Monster randomMonster = game.getAllMonsters().random();
		double randomValue = rn.nextDouble(1);
		
		if(randomValue <= joinChance) {
			try {				
				player.getMonsters().add(randomMonster);
				event = String.format("A new monster has joined the team over night. Welcome %s!\n", randomMonster.getName());
			}
			catch (InventoryFullException e) {}
		}
		return event;
    }
    
    
    /**
     * Runs randomMonsterLevelUp based on the number of monsters in the player's team 
     * Runs randomMonsterLeave based on the number of monsters in the player's team but stops when there is only 1 monster in the team
     * Runs randomMonsterJoin based on the number of free slots in the player's team 
     * @return event the string representing the events occurred  
     */
    public String runAllRandom() {
    	String events = "";
		for(Monster monster: player.getMonsters().getList()) {
			events += randomMonsterLevelUp(monster);
		}
		ArrayList<Monster> playerMonsterList = new ArrayList<Monster>(player.getMonsters().getList());
		for(Monster monster: playerMonsterList) {
		    if(player.getMonsters().getList().size() <= 1) {
		    	break;
		    }
		    try {
		    	events += randomMonsterLeave(monster);
		    }
		    catch (NotFoundException e) {
		    	e.printStackTrace();
		    }
		}
		for(int i = 0; i < player.getMonsters().getMaxSize() - player.getMonsters().getList().size(); i++) {
			events += randomMonsterJoin();
		}
		if (events.equals("")) {
			events = "Nothing interesting happened over night...\n";
		}
		return events;
    }
    
}
