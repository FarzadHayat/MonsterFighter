import java.util.ArrayList;

public class Inventory {

    private ArrayList<Monster> myMonsters;
    private ArrayList<Item> myItems;
    
    public Inventory () {
    	myMonsters = new ArrayList<Monster>();
    	myItems = new ArrayList<Item>();
    };


    /**
     * @param        item
     * @param        monster
     */
    public void useItem(Item item, Monster monster)
    {
    }


    /**
     * @param        monster
     */
    public void addMonster(Monster monster)
    {
    }


    /**
     * @param        monster
     */
    public void removeMonster(Monster monster)
    {
    }


    /**
     * @param        item
     */
    public void addItem(Item item)
    {
    }


    /**
     * @param        item
     */
    public void removeItem(Item item)
    {
    }


}
