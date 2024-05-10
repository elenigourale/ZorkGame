
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.util.*;

public class ZorkController {


    private static ZorkView view;
    private static ZorkModel model;

    List<String> objects = new ArrayList<>();
    List<String> commands = new ArrayList<>();
// private DA modelPoints;
    //private DAView view;


    public ZorkController(ZorkModel model, ZorkView view) {
        this.model = model;
        this.view = view;


    }



    public  void setObjects(String newObject) {
        objects.add(newObject);
    }

    public void removeItem(String item) {
        objects.remove(item);
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }




    public Player makeHomeArchitecture() {
        Room livingRoom = new Room("living room");
        Room kitchen = new Room("kitchen");
        Room bedroom = new Room("bedroom");
        Room bathroom = new Room("bathroom");
        // Σύνδεση χώρων
        livingRoom.setExit("go to kitchen", kitchen);
        livingRoom.setExit("go to bedroom", bedroom);
        livingRoom.setExit("go to bathroom", bathroom);

        kitchen.setExit("go to living room", livingRoom);
        kitchen.setExit("go to bedroom", bedroom);
        kitchen.setExit("go to bathroom", bathroom);

        bedroom.setExit("go to bathroom", bathroom);
        bedroom.setExit("go to living room", livingRoom);
        bedroom.setExit("go to kitchen", kitchen);

        bathroom.setExit("go to bedroom", bedroom);
        bathroom.setExit("go to living room", livingRoom);
        bathroom.setExit("go to kitchen", kitchen);
        //initialise player too
        Player player = new Player(livingRoom);
        return player;
    }
    public class HouseGraph {

        private HashMap<String, List<String>> adjacencyList;

        public HouseGraph() {
            adjacencyList = new HashMap<>();
            initializeGraph();
        }

        public void initializeGraph() {
            addNode("Bedroom");
            addNode("Bathroom");
            addNode("LivingRoom");
            addNode("Kitchen");

            addEdge("Bedroom", "Bathroom");
            addEdge("Bathroom", "LivingRoom");
        }

        private void addNode(String name) {
            adjacencyList.put(name, new ArrayList<>());
        }

        private void addEdge(String source, String destination) {
            adjacencyList.get(source).add(destination);
        }

        // You can add methods here to traverse the graph (DFS, BFS)
        // and check if the player can reach the LivingRoom
    }
    public List<String> getCommands() {
        return commands;
    }

    public boolean heIsGettingCloser(int thesis) {
        boolean gotcha;
        switch (thesis) {
            case 3:
                gotcha = true;

                break;
            default:
                gotcha = false;
                break;
        }
        return gotcha;
    }


    public void setYourName(String yourName) {
        model.setYourName(yourName);
    }

    public static String getYourName() {
        return model.getYourName();

    }
}

    class Player {
        public static Room currentRoom;

        private int hitPoints;
        public int getHitPoints() {
            return hitPoints;
        }

        public void setHitPoints(int hitPoints) {
            this.hitPoints = hitPoints+this.hitPoints;
        }

        public Player(Room startingRoom) {
            currentRoom = startingRoom;
        }

        public void stabHim(boolean existantKnife) {

            System.out.println("you won");

        }

        public void move(String direction) {
            Room nextRoom = currentRoom.getExit(direction);
            if (nextRoom != null) {
                currentRoom = nextRoom;
                System.out.println("You are now in: " + currentRoom.getDescription());
            } else {
                System.out.println("you cannot go there");
            }
        }


    }

    class Room {

        //public static Room livingRoom;
        private String description;
        private Map<String, Room> exits;
        private List<String> items;


        public static void getUserInputToTXT(File file, String string)
        {
            // Write user action to file
            try {
//System.out.println("Im writingg");
                FileWriter writer = new FileWriter(file, true);  // Append mode
                writer.write(string+"\n");
                writer.close();
            } catch (Exception e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        public Room(String description) {
            this.description = description;
            exits = new HashMap<>();
        }

        public String getDescription() {
            return description;
        }

        public void setExit(String direction, Room room) {
            exits.put(direction, room);
        }


        public Room getExit(String direction) {
            return exits.get(direction);
        }


        public static void getHashtableItems(Hashtable itemsKitchen) {
            for (Object key : itemsKitchen.keySet()) {
                System.out.println(key.hashCode() + "\t" + key + "\t" + itemsKitchen.get(key));

            }
        }



    }


    class LivingRoom {

    public static void lookForBackpack(Player player, Scanner scanner, File file)
    {
        boolean rooms = true;
        String line ;
        while(rooms){
            System.out.println("you 'll need a backpack to put your staff");
            line = scanner.nextLine();
            Room.getUserInputToTXT(file, line);
            switch (line)
            {
                case "look":
                    Room.getHashtableItems(initLivingRoomItems());
                  //  line = scanner.nextLine();
                    break;
                case "grab backpack":
                    ZorkGame.yourItems.put(16,"backpack");
                    initLivingRoomItems().remove(1);
                    System.out.println("you have your backpack");
                    rooms=false;
                    break;

                default:
                    System.out.println("i cannot understand you");
                    break;
            }

        }


    }

    public static  void goToLivingroom(Player player, Scanner scanner, File file)
    {
boolean rooms=true;
String line;
        while (rooms) {

            System.out.print("Where do you want to go next: ");
            line = scanner.nextLine();
            Room.getUserInputToTXT(file, line);
            String direction = line;
            player.move(direction);

            switch (direction)
            {
                case "go to kitchen":
                    rooms=false;
                    break;
                case "go to bathroom":
                    System.out.println("wrong room");
                 //   line = scanner.nextLine();
                    break;
                case "go to bedroom":
                    System.out.println("wrong room");
                  //  line = scanner.nextLine();
                    break;
                default:
                    rooms=true;
                    System.out.println("he is getting closer to you");
                   // line = scanner.nextLine();
                    break;
            }
        }
    }


        public static void getHashtableItems(Hashtable itemsKitchen) {
            for (Object key : itemsKitchen.keySet()) {
                System.out.println(key.hashCode() + "\t" + key + "\t" + itemsKitchen.get(key));

            }
        }
        public static Hashtable endGame(boolean rooms, Hashtable yourItems, Scanner scanner, File file)
        {
            String line;

           while(rooms)
        {

            System.out.println("it s your only chance now");
            String getLine = scanner.nextLine();

            Room.getUserInputToTXT(file, getLine);
            switch (getLine) {
                case "stab him and call 911":
                    System.out.println("you won");
                    rooms = false;
                    break;
                case "stab him":
                    System.out.println("he died and you went in jail");
                    rooms = false;
                    break;
                case "call 911":
                    System.out.println("he listened to you and stabbed you first, game over");
                    rooms = false;
                case "my items":
                    Room.getHashtableItems(yourItems);
                    break;
                default:
                    System.out.println("I'm sorry I cannot understand you");
                    break;
            }
        }
           return yourItems;

    }


    public static void goToLivingRoomFinal( Player player, Hashtable yourItems, Scanner scanner, Hashtable itemsLivingroom, File file)
    {
        boolean rooms=true;

        while (rooms){

            System.out.println("go in the living room");
            String getLine=scanner.nextLine();

            Room.getUserInputToTXT(file, getLine);
            switch (getLine) {
                case "go to living room":
                    player.move(getLine);
                    System.out.println("stab him and call 911");
                    rooms=false;
                    break;
                case "go to kitchen":
                    player.move(getLine);
                    break;
                case "go to bathroom":
                    player.move(getLine);
                    break;
                case "go to bedroom":
                    player.move(getLine);
                    break;
                case "look":
                    System.out.println("he is with his back facing you, do sth quick!!");
                    break;
                case "look for items":
                    Room.getHashtableItems(itemsLivingroom);
                    break;
                case "my items":
                    Room.getHashtableItems(yourItems);
                    break;
                default:
                    System.out.println("I'm sorry I cannot understand you");
                    break;
            }}
    }

        public static boolean checkBackpack(Hashtable<Integer, String> yourBackPack)
        {
            boolean check;
            if (yourBackPack.containsValue("knife") && yourBackPack.containsValue("phone")) {
          check=true;
            } else {
              check=false;
            }
            return check;
        }
          public static Hashtable initLivingRoomItems()
    {
        Hashtable<Integer, String> itemsBedroom= new Hashtable<>(10);
        itemsBedroom.put(1, "backpack");

        return itemsBedroom;
    }

        class Bathroom
        {

            public static Hashtable initBathroomItems()
            {
                Hashtable<Integer, String> itemsBathroom= new Hashtable<>(10);
                itemsBathroom.put(1, "paper");
                itemsBathroom.put(2, "cat");
                itemsBathroom.put(3, "soap");
                return itemsBathroom;
            }


        }


        class Bedroom
        {
            public static Hashtable initBedroomRoomItems()
            {
                Hashtable<Integer, String> itemsBedroom= new Hashtable<>(10);
                itemsBedroom.put(1, "diary");
                itemsBedroom.put(2, "phone");
                itemsBedroom.put(3, "bag");
                return itemsBedroom;
            }




        }



        class Kitchen
        {
            public static Hashtable initKitchenRoomItems()
            {
                Hashtable<Integer, String> itemsKitchen= new Hashtable<>(10);
                itemsKitchen.put(1, "knife");
                itemsKitchen.put(2, "banana");
                itemsKitchen.put(3, "trash");
                return itemsKitchen;
            }

        }
        public static int heIsClose(int yourThesi)
     {
         int check=0;

         switch (yourThesi)
         {
             case 0:
                 case 1:
                 check+=1 ;
             break;
             case 2:
                 check+=3;
                 break;
             default:
                 check+=4;
                 break;

         }
         return check;
     }



    }
class Bathroom
{

    public static Hashtable initBathroomItems()
    {
        Hashtable<Integer, String> itemsBathroom= new Hashtable<>(10);
        itemsBathroom.put(1, "paper");
        itemsBathroom.put(2, "cat");
        itemsBathroom.put(3, "soap");
        return itemsBathroom;
    }

public static void goToBathroom(Player player, Hashtable yourItems, Scanner scanner, Hashtable itemsBedroom, Hashtable itemsBathroom, File file)
{
    boolean  rooms=true;
    while (rooms){

        System.out.println("the noise is coming from the bathroom");
        String getLine=scanner.nextLine();

        Room.getUserInputToTXT(file, getLine);
        switch (getLine) {
            case "go to bathroom":
                player.move(getLine);
               rooms= wasTheCat(player,yourItems, scanner,  itemsBathroom);

                break;

            case "look":
                Room.getHashtableItems(itemsBedroom);
                break;
            case "my items":
                Room.getHashtableItems(yourItems);
                break;
            default:
                System.out.println("I'm sorry I cannot understand you");
                break;
        }}
}

private static boolean wasTheCat( Player player, Hashtable yourItems, Scanner scanner, Hashtable itemsBathroom)
{
    boolean rooms=true;
    System.out.println("look or grab sth");
    String getLine=scanner.nextLine();

    switch (getLine) {
        case "look":
            Room.getHashtableItems(itemsBathroom);
            break;

        default:
            System.out.println("Sorry, I don't understand you");
            break;

    }
    return rooms;
}

 private static int petCat(Player player,Hashtable yourItems,Hashtable itemsBathroom)
 {
     int points=0;


     return  points;
 }

}


class Bedroom
{
    public static Hashtable initBedroomRoomItems()
    {
        Hashtable<Integer, String> itemsBedroom= new Hashtable<>(10);
        itemsBedroom.put(1, "diary");
        itemsBedroom.put(2, "phone");
        itemsBedroom.put(3, "bag");
        return itemsBedroom;
    }

public static Hashtable findPhone(Player player, Hashtable yourItems, Scanner scanner, Hashtable itemsBedroom, ZorkController controller, File file)
{
    boolean rooms=true;
    while (rooms){
        System.out.println("you should find your phone");
        String getLine=scanner.nextLine();

        Room.getUserInputToTXT(file, getLine);
        switch (getLine) {
            case "hide":
                System.out.println("you hear a noise");
                break;
            case "my items":
                Room.getHashtableItems(yourItems);
                break;
            case "look":
                Room.getHashtableItems(itemsBedroom);
                break;
            case "grab diary":
                controller.setObjects("diary");
                itemsBedroom.remove(1);
                yourItems.put(3, "diary");
                rooms=false;
                System.out.println("you can now write your feeling away");
                break;
            case "grab bag":
                controller.setObjects("bag");
                itemsBedroom.remove(3);
                yourItems.put(4, "bag");
                rooms=false;
                System.out.println("you can now be stylish while getting killed I guess");
                break;
            case "grab phone":
               controller.setObjects("phone");
                itemsBedroom.remove(2);
                yourItems.put(2, "phone");
                rooms=false;
                System.out.println("you can now call for help");
                rooms=false;
                break;
            default:
                System.out.println("I'm sorry I cannot understand you");
                break;
        }}

    return  yourItems;
}



}



class Kitchen
{
    public static Hashtable initKitchenRoomItems()
    {
        Hashtable<Integer, String> itemsKitchen= new Hashtable<>(10);
        itemsKitchen.put(1, "knife");
        itemsKitchen.put(2, "banana");
        itemsKitchen.put(3, "trash");
        return itemsKitchen;
    }
  public static Hashtable grabKnife( Hashtable yourItems, Scanner scanner, Hashtable itemsKitchen, File file)
  {
      boolean rooms=true;
      while (rooms){
          System.out.println("quick look for sth that can protect you");
          String getLine=scanner.nextLine();
          Room.getUserInputToTXT(file, getLine);
          switch (getLine) {
              case ("grab knife"):
                  yourItems.put(1,"knife");
                  itemsKitchen.remove(1);
                  System.out.println("you hear his footsteps, quick go hide in the bedroom");
                  rooms=false;
                  break;
              case ("grab banana"):
                  yourItems.put(5,"banana");
                  itemsKitchen.remove(2);
                  System.out.println("you can eat the banana");
                 break;
              case ("grab trash"):
                  yourItems.put(6,"trash");
                  itemsKitchen.remove(3);
                  System.out.println("damn they smell kinda bad");
                  break;
              case ("eat banana"):
                  if (yourItems.contains("banana")){
                  yourItems.remove(5, "banana");
                  System.out.println("yummy");}
                  break;
              case  ("look"):
                  Room.getHashtableItems(itemsKitchen);
                 break;
              case ("check backpack"):
                  Room.getHashtableItems(yourItems);
                  break;
              default:
                  System.out.println("you should look for sth that can cause damage");
                  break;
          }}
      return yourItems;
  }



  public static void kitchenToLivingRoom( Hashtable yourItems, Scanner scanner, Player player, File file)
  {
boolean rooms=true;

      while (rooms){
          System.out.println("you should hide in the bedroom");
          String getLine=scanner.nextLine();
          Room.getUserInputToTXT(file, getLine);
          switch (getLine) {
              case "go to bedroom":
                  player.move(getLine);
                  rooms=false;
                  break;
              case "go to bathroom":
                  player.move(getLine);

                  break;
              case "go to kitchen":
                  player.move(getLine);

                  break;
              case "go to livingroom":
                  player.move(getLine);

                  break;
              case "my items":
                  Room.getHashtableItems(yourItems);
                  break;
              default:
                  System.out.println("I'm sorry I cannot understand you");
                  break;
          }}
  }
}

