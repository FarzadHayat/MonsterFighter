package main;

import java.util.*;

public class RandomEvent {
    
    private double levelUpChance = 0.1;
    private double leaveChance = 0.05;
    private double joinChance = 0.1;
    private GameEnvironment game;
    private double leaveIncrement = 2.0;
    private double levelUpIncrement = 0.1;
    Random rn = new Random();
    
    public RandomEvent(GameEnvironment game) {
    	this.game = game;
    }
    
    public double getLevelUpChance() {
    	return levelUpChance;
    }
    
    public void setLevelUpChance(double levelUpChance) {
    	this.levelUpChance = levelUpChance;
    }

    public double getLeaveChance() {
    	return leaveChance;
    }
    
    public void setLeaveChance(double leaveChance) {
    	this.leaveChance = leaveChance;
    }
    
    public double getJoinChance() {
    	return joinChance;
    }
    
    public void setJoinChance(double joinChance) {
    	this.joinChance = joinChance;
    }
    
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
    
    public String randomMonsterLeave(Monster monster) throws StorableNotFoundException {
    	String event = "";
		double resetValue = leaveChance;
		//If monster fainted during any battle in the day, leaveChance is doubled 
		if(monster.getIsFainted()) {
		    leaveChance *= leaveIncrement;
		}
		double randomValue = rn.nextDouble(1);
		if(randomValue <= leaveChance) {
		    game.getMyMonsters().remove(monster);
		    event = String.format("Sadly, %s left the team over night...\n", monster.getName());
		}
		setLeaveChance(resetValue);
		return event;
    }

    public String randomMonsterJoin() {
    	String event = "";
		Monster randomMonster = game.getAllMonsters().random();
		double randomValue = rn.nextDouble(1);
		
		if(randomValue <= joinChance) {
			try {				
				game.getMyMonsters().add(randomMonster);
				event = String.format("A new monster has joined the team over night. Welcome %s!\n", randomMonster.getName());
			}
			catch (InventoryFullException e) {}
		}
		return event;
    }
    
    public String runAllRandom() {
    	String events = "";
		for(Monster monster: game.getMyMonsters().getList()) {
			events += randomMonsterLevelUp(monster);
		}
		for(Monster monster: game.getMyMonsters().getList()) {
		    if(game.getMyMonsters().size() <= 1) {
		    	break;
		    }
		    try {		    	
		    	events += randomMonsterLeave(monster);
		    }
		    catch (StorableNotFoundException e) {
		    	e.printStackTrace();
		    }
		}
		for(int i = 0; i < game.getMyMonsters().getMaxSize() - game.getMyMonsters().size(); i++) {
			events += randomMonsterJoin();
		}
		if (events.equals("")) {
			events = "Nothing interesting happened over night...\n";
		}
		return events;
    }
}
