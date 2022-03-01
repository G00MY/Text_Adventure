package textadventure.game;
import java.util.HashMap;
import java.util.Set;
public class Room {
	private String name;
    private String shortDescription;
    private String longDescription;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> inventory;
    
    public Room(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        exits = new HashMap<>();
        inventory = new HashMap<>();
    }
    
    public String getName(){
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor); 
    }

    public void setItem(String name, Item item){
        inventory.put(name, item);

    }
        
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public Item removeItem(String Key){
        return inventory.remove(Key);
    }

    public Item getItem(String Key){
        return inventory.get(Key);
    }

    public String getExitString() {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for (String exits: keys) {
            returnString += "\"" + exits + "\"";
        }
        return returnString;
    }

    public String getInventoryString(){
        String returnString = "Room Inventory: ";
        Set<String> keys = inventory.keySet();
        for (String item: keys) {
            returnString += "\"" + item + "\" ";
        }
        return returnString;
    }

}
