
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

    public static void look(List<String> roomItems) {

        for (String item : roomItems) {
            System.out.println(item);
        }

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

    public List<String> getObjects() {
        return objects;
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
        private static List[] objects;

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

        public List[] getObjects() {
            return objects;
        }
    }

    class Room {

        //public static Room livingRoom;
        private String description;
        private Map<String, Room> exits;
        private List<String> items;

        public static void look(List<String> roomItems) {

            for (String item : roomItems) {
                System.out.println(item);
            }

        }


        public Room(String description) {
            this.description = description;
            exits = new HashMap<>();
        }

        public List<String> getItems() {
            return items;
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
    public static  void goToLivingroom(boolean rooms, Player player, Scanner scanner)
    {

        while (rooms) {
            System.out.print("Where do you want to go next: ");
            String direction = scanner.nextLine();
            player.move(direction);

            switch (direction)
            {
                case "go to kitchen":
                    rooms=false;
                    break;
                default:
                    rooms=true;
                    System.out.println("he is getting closer to you");
                    break;
            }
        }
    }


        public static void getHashtableItems(Hashtable itemsKitchen) {
            for (Object key : itemsKitchen.keySet()) {
                System.out.println(key.hashCode() + "\t" + key + "\t" + itemsKitchen.get(key));

            }
        }
        public static Hashtable endGame(boolean rooms, Hashtable yourItems, Scanner scanner)
        {
           while(rooms)

        {
            System.out.println("it s your only chance now");
            String getLine = scanner.nextLine();

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


    public static void goToLivingRoomFinal(boolean rooms, Player player, Hashtable yourItems, Scanner scanner)
    {

        while (rooms){

            System.out.println("go in the living room");
            String getLine=scanner.nextLine();

            switch (getLine) {
                case "go to living room":
                    player.move(getLine);
                    System.out.println("stab him and call 911");
                    rooms=false;
                    break;
                case "look":
                    System.out.println("he is with his back facing you, do sth quick!!");
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
            if (yourBackPack.containsKey("knife") && yourBackPack.containsKey("phone")) {
          check=true;
            } else {
              check=false;
            }
            return check;
        }
          public static Hashtable initLivingRoomItems()
    {
        Hashtable<Integer, String> itemsBedroom= new Hashtable<>(10);
        itemsBedroom.put(1, "diary");
        itemsBedroom.put(2, "phone");
        itemsBedroom.put(3, "bag");
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

public static void goToBathroom(boolean rooms, Player player, Hashtable yourItems, Scanner scanner, Hashtable itemsBedroom)
{
    while (rooms){

        System.out.println("the noise is coming from the bathroom");
        String getLine=scanner.nextLine();

        switch (getLine) {
            case "go to bathroom":
                player.move(getLine);
                System.out.println("it s the cat,you need to stab the intruder in the living room AND call for help");
                rooms=false;
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

public static Hashtable findPhone(boolean rooms, Player player, Hashtable yourItems, Scanner scanner, Hashtable itemsBedroom, ZorkController controller)
{
    while (rooms){
        System.out.println("you should find your phone");
        String getLine=scanner.nextLine();

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
  public static Hashtable grabKnife(boolean rooms, Hashtable yourItems, Scanner scanner, Hashtable itemsKitchen)
  {
      while (rooms){
          System.out.println("quick look for sth that can protect you");
          String getLine=scanner.nextLine();
          switch (getLine) {
              case ("grab knife"):
                  yourItems.put(1,"knife");
                  itemsKitchen.remove(1);
                  System.out.println("you hear his footsteps, quick go hide in the bedroom");
                  rooms=false;
                  break;
              case  ("look"):
                  Room.getHashtableItems(itemsKitchen);

              default:
                  System.out.println("you should look for sth that can cause damage");
                  break;
          }}
      return yourItems;
  }



  public static void kitchenToLivingRoom(boolean rooms, Hashtable yourItems, Scanner scanner, Player player)
  {

      while (rooms){
          System.out.println("you should hide in the bedroom");
          String getLine=scanner.nextLine();

          switch (getLine) {
              case "go to bedroom":
                  player.move(getLine);
                  rooms=false;
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

