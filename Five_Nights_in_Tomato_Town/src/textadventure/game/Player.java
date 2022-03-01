package textadventure.game;
import java.util.HashMap;
import java.util.Set;
public class Player {
	private HashMap<String, Item> inventory;
    public Player(){
        inventory = new HashMap<>();
    }
    public String getInventoryString(){
        String returnString = "Player Inventory: ";
        Set<String> keys = inventory.keySet();
        for (String item: keys) {
            returnString += " " + item;
        }
        return returnString;
    }

    public Item removeItem(String Key){
        return inventory.remove(Key);
    }

    public Item getItem(String Key){
        return inventory.get(Key);
    }
    public void setItem(String name, Item item){
        inventory.put(name, item);

    }
}
