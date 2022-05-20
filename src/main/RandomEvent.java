package main;

import java.util.*;

import exceptions.StatMaxedOutException;
import monsters.Raka;

/**
 * Handles all random events that happen over night.
 * @author Farzad and Daniel
 */
public class RandomEvent {
    
	/**
	 * Fields
	 */
    private double levelUpChance = 0.1;
    private double leaveChance = 0.05;
    private double joinChance = 0.1;
    private double leaveIncrement = 2.0;
    private double levelUpIncrement = 0.1;
    
    private GameEnvironment game = GameEnvironment.getInstance();
    private Player player;
    
    private Random rn = new Random();
    
    
    /**
     * Constructors 
     */
    
    /**
     * Creates RandomEvent object 
     */
    public RandomEvent() {
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
     * Get the value of rn
     * @return the value of rn 
     */
    public Random getRandom() {
    	return rn;
    }
    
    
    /**
     * Set the value of rn
     * @param rn the new value of rn
     */
    public void setRandom(Random rn) {
    	this.rn = rn;
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
     */
    public String randomMonsterLeave(Monster monster) {
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
		Monster randomMonster = game.getAllMonsters().random().clone();
		if(randomMonster instanceof Raka) {
			((Raka) randomMonster).setTeam(player.getMonsters());
		}
		double randomValue = rn.nextDouble(1);
		
		if(randomValue <= joinChance) {
			if (!player.getMonsters().isFull()) {
				player.getMonsters().add(randomMonster);
				event = String.format("A new monster has joined the team over night. Welcome %s!\n", randomMonster.getName());
			}
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
		for(Monster monster: player.getMonsters()) {
			events += randomMonsterLevelUp(monster);
		}
		ArrayList<Monster> playerMonsterList = new ArrayList<Monster>(player.getMonsters());
		for(Monster monster: playerMonsterList) {
		    if(player.getMonsters().size() <= 1) {
		    	break;
		    }
		    events += randomMonsterLeave(monster);
		}
		for(int i = 0; i < player.getMonsters().getMaxSize() - player.getMonsters().size(); i++) {
			events += randomMonsterJoin();
		}
		if (events.equals("")) {
			events = "Nothing interesting happened over night...\n";
		}
		return events;
    }
    
}
