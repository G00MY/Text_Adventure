package textadventure.game;
import java.util.Scanner;

public class Game {
	private Parser parser;
    private Room currentRoom;
    private Player player;
    private CLS cls_var;
    public Game(){
        parser = new Parser();
        player = new Player();
    }

    
    public void printInformation(){
    	 Scanner scanner = new Scanner(System.in);
         String [] intro = {"press enter", "Welcome to Five Nights in Tomato Town", "If at any point you don't know what to do type help", "You are about to start the game", "You won the Fortnite Victory Royal. But at what cost."};
         System.out.println(intro[0]);
         for (int i = 0; i < intro.length; i++) {
       	  System.out.print(intro[i+1]);
       	  scanner.nextLine();
         }
 
        System.out.println(currentRoom.getShortDescription());
        System.out.println(player.getInventoryString());
        System.out.println(currentRoom.getInventoryString());
        System.out.println(currentRoom.getExitString());
        
    }

    public static void main(String[] arg){
        Game game = new Game();
        game.setupGame();
        game.play();
    }

   
    public void setupGame(){
        Room DiningHall =new Room("Dining Hall", "You are in the DiningHall", "An empty Dining Hall. there is food everywhere");
        Room Kitchen = new Room ("Kitchen", "You are in the Kitchen", "You have entered the kitchen west of the dining room. The room is dark with axes everywhere. Hiding behind the counter is another bean looking robot. He has a plaque next to him with the name Gregory. Gregory is holding an empty bag."); 
        Room Theater = new Room ("Theater", "You are in the Theater", "you have entered the northernmost point of the building. There is a theater with a stage on it. All the lights are off but the curtains are open. The stage is a black abyss but you can see a silhouette of a robotic bean with legs on stage.  ");
        Room PlayPlace = new Room ("PlayPlace", "You are in the PlayPlace", "You are have entered the PlayPlace. There is a maze and a robot bear man standing next to it");
        Room SecretAttic = new Room("SecretAttic", "You are in the SecretAttic", "you are in the attic above the dining hall. You see a different looking bean looking robot. There is a spaceship next to him.");        
        
        
        Room Maze = new Room ("Maze", "You are at the enterance of the maze", "There is a bunch of tubes and they all lead to different areas");
       
        
        Item itemExample = new Item("Wood", "Just some common wood. It could be used for building");
        Item itemExample2 = new Item("VBucks", " Small Shiny Coins. It is very valuable");
        Item itemExample3 = new Item("Meat", " yummy");
        Item itemExample4 = new Item("ChugJug", "Hit that");
        Item itemExample5 = new Item("Pizza", "Looks kinda gross");
        
        
        

        currentRoom = DiningHall;
        DiningHall.setExit ("Kitchen", Kitchen);
        DiningHall.setExit ("Theater", Theater);
        DiningHall.setExit ("PlayPlace", PlayPlace);
        DiningHall.setExit ( "SecretAttic", SecretAttic);
        Kitchen.setExit ("Dining Hall", DiningHall);
        Theater.setExit("Dining Hall", DiningHall);
        PlayPlace.setExit("Dining Hall", DiningHall);
        SecretAttic.setExit("Dinin Hall", DiningHall);
        Maze.setExit("PlayPlace", PlayPlace);
        
        PlayPlace.setExit("Maze", Maze);

        Kitchen.setItem("Wood", itemExample);
        Kitchen.setItem("Meat", itemExample3);
        //PlayPlace.setItem("VBucks", itemExample2);
        DiningHall.setItem("ChugJug", itemExample4);
        DiningHall.setItem("Pizza", itemExample5);
        
        
        try{
                cls_var.main();
            }catch(Exception e) {
                System.out.println(e);
            }
        printInformation();
        
       
        play();

    }

    public void play() {
        while(true) {
            Command command = parser.getCommand();
            try{
                cls_var.main();
            }catch(Exception e) {
                System.out.println(e);
            }
            processCommand(command);
            printInformation();
            }
    }

    public void processCommand(Command command) {
        String commandWord = command.getCommandWord().toLowerCase();

        switch(commandWord) {
            case "speak":
                System.out.println ("you wanted me to speak this word, " + command.getSecondWord());
                break;
            case "go":
                goRoom(command);
                break;
            case "grab":
                grab(command);
                break;
            case "drop":
                drop(command);
                break;
            case "look":
                look(command);
                break;
           
                
        }
    }

    
    public void look(Command command){
        String printString = "Looking at ";
        String thingToLook = null;
        if(!command.hasSecondWord()){
            System.out.println("look at what?");
            return;
        }
        if(!command.hasLine()){
            thingToLook = command.getSecondWord();        
        }
        else if (command.hasLine()){
            thingToLook = command.getSecondWord()+command.getLine();
        }
    
        if(thingToLook.equals(currentRoom.getName())){
           printString += "the room: " + currentRoom.getName() + "\n" + currentRoom.getLongDescription();
        }
        else if (currentRoom.getItem(thingToLook) != null){
            printString += "the item: " + currentRoom.getItem(thingToLook).getName() + "\n" + currentRoom.getItem(thingToLook).getDescription(); 
        }
        else if (currentRoom.getItem(thingToLook) != null){
            printString += "the item: " + player.getItem(thingToLook).getName()+ "\n" + player.getItem(thingToLook).getDescription();; 
        }
        else {
            printString += "\n A ghost covered both your eyes and you cannot look at that";
        }
        System.out.println(printString);
    }
    
    public void grab(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Grab What?");
            return;
        }
        String item = command.getSecondWord();
        Item itemToGrab = currentRoom.removeItem(item);
        if(itemToGrab == null) {
            System.out.println("you can't grab that");
        }
        else {
            player.setItem(item, itemToGrab);
        }
    }
    public void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Drop What?");
        }
        String item = command.getSecondWord();
        Item itemToDrop = player.removeItem(item);
        if(itemToDrop == null) {
            System.out.println("you probably should not drop that");
        }
        else {
            currentRoom.setItem(item, itemToDrop);
        }
    }

    public void goRoom(Command command){
        String direction = "";
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        if(!command.hasLine()){
            direction = command.getSecondWord();
        }
        else if (command.hasLine()){
            direction = command.getSecondWord()+command.getLine();
        }
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) {
            System.out.println("you can't go there");
        }
        else {
            currentRoom = nextRoom;   
            
        }
         currentRoom = nextRoom;
    }
    
}

   
		
		

