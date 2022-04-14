package main;

/**
 * Class Battle
 * The player will have at most four monsters.
Once the player has picked a battle,
 * his monsters will fight against the enemy monsters by attacking in a random
 * order. The attacks will take turn one by the player and one by the computer
 * until one team has fainted all monsters.
 */
public class Battle {

    private int turn;    public Battle () { };
    
    //
    // Methods
    //


    /**
     * Set the value of turn
     * @param newVar the new value of turn
     */
    public void setTurn (int newVar) {
        turn = newVar;
    }

    /**
     * Get the value of turn
     * @return the value of turn
     */
    public int getTurn () {
        return turn;
    }

    //
    // Other methods
    //

    /**
     * choose a random player monster to attack a random enemy monster
increment turn
     * over to the enemy
     */
    public void playerAttack()
    {
    }


    /**
     * choose a random enemy monster to attack a random player monster
increment turn
     * over to the player
     */
    public void enemyAttack()
    {
    }


    /**
     * while player not lost and enemy not lost:
	if player's turn:
		player
     * attack
	else:
		enemy attack
	check if won, lost, or neither
     */
    public void play()
    {
    }


    /**
     */
    public void win()
    {
    }


    /**
     */
    public void lose()
    {
    }


}
