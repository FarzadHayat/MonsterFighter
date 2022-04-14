package main;

public class GameEnvironment {

    private int balance;
    private String name;
    private int numDays;
    private String difficulty;    public GameEnvironment () { };
    
    //
    // Methods
    //


    /**
     * Set the value of balance
     * @param newVar the new value of balance
     */
    public void setBalance (int newVar) {
        balance = newVar;
    }

    /**
     * Get the value of balance
     * @return the value of balance
     */
    public int getBalance () {
        return balance;
    }

    /**
     * Set the value of name
     * @param newVar the new value of name
     */
    public void setName (String newVar) {
        name = newVar;
    }

    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName () {
        return name;
    }

    /**
     * Set the value of numDays
     * @param newVar the new value of numDays
     */
    public void setNumDays (int newVar) {
        numDays = newVar;
    }

    /**
     * Get the value of numDays
     * @return the value of numDays
     */
    public int getNumDays () {
        return numDays;
    }

    /**
     * Set the value of difficulty
     * @param newVar the new value of difficulty
     */
    public void setDifficulty (String newVar) {
        difficulty = newVar;
    }

    /**
     * Get the value of difficulty
     * @return the value of difficulty
     */
    public String getDifficulty () {
        return difficulty;
    }

    //
    // Other methods
    //

    /**
     */
    public void sleep()
    {
    }


    /**
     * View the shop page.
     */
    public void visitShop()
    {
    }


    /**
     * View the possible battles panel.
     */
    public void viewBattles()
    {
    }


    /**
     * Select a battle to fight.
     */
    public void selectBattle()
    {
    }


    /**
     * View the player inventory panel.
     */
    public void viewInventory()
    {
    }


    /**
     * @param        item
     * @param        monster
     */
    public void useItem(Item item, Monster monster)
    {
    }


    /**
     * View the team properties panel.
     */
    public void viewTeam()
    {
    }


    /**
     * View the game statistics panel.
     */
    public void viewStats()
    {
    }


    /**
     * 1. Set player name and request a different name if necessary.
2. Set number of
     * days and request a different input if necessary.
3. Set difficulty.
4. Select
     * starting monster and set monster name if not using the default.
     */
    public void setupGame()
    {
    }


    /**
     * @param        name
     */
    public void setName(String name)
    {
    }


    /**
     * @param        numDays
     */
    public void setNumDays(int numDays)
    {
    }


    /**
     * @param        difficulty
     */
    public void setDifficulty(String difficulty)
    {
    }


    /**
     * @param        monster
     */
    public void selectStartingMonster(Monster monster)
    {
    }


}
