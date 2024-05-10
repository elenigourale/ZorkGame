import java.io.File;
import java.util.*;


public class ZorkGame {
    static ZorkModel model = new ZorkModel();
    static ZorkView view = new ZorkView();
    private static ZorkController controller = new ZorkController(model, view);
    private static Player player;
      static File file = new File("C:\\Users\\30698\\Desktopww\\ZorkGame\\zork_log.txt");

    static Hashtable<Integer, String> yourItems= new Hashtable<>(10);

    public static void main(String[] args) {
        System.out.println("Welcome to our game do you want to continue or start a new one?");

        Scanner scanner = new Scanner(System.in);


                player = controller.makeHomeArchitecture();
                Room livingRoom = new Room("living room");
                Room kitchen = new Room("kitchen");
                Room bedroom = new Room("bedroom");
                Room bathroom = new Room("bathroom");
                Hashtable<Integer, String> itemsKitchen = Kitchen.initKitchenRoomItems();
                Hashtable<Integer, String> itemsBedroom = Bedroom.initBedroomRoomItems();
                Hashtable<Integer, String> itemsBathroom = Bathroom.initBathroomItems();
                Hashtable<Integer, String> itemsLivingroom = LivingRoom.initLivingRoomItems();
                // Room.getHashtableItems(itemsKitchen);
                // getHashtableItems(itemsBedroom);

                // kitchen.getHashtableItems(itemsKitchen);
                // Έναρξη παιχνιδιού
        switch (scanner.nextLine()) {
            case ("new one"):


                //Scanner scanner = new Scanner(System.in);
                System.out.println("somebody is in your living room, quick go find sth in the kitchen");

                System.out.println("You are now in: " + livingRoom.getDescription());
                //int hisThesis;

                LivingRoom.lookForBackpack(player, scanner, file);

                //living room to kitchen
                LivingRoom.goToLivingroom(player, scanner, file);
// kitchen to grab knife

                yourItems = Kitchen.grabKnife(yourItems, scanner, itemsKitchen, file);

//kitchen to bedroom

                Kitchen.kitchenToLivingRoom(yourItems, scanner, player, file);
//bedroom to hide and grab phone

                yourItems = Bedroom.findPhone(player, yourItems, scanner, itemsBedroom, controller, file);
//go to bathroom

                Bathroom.goToBathroom(player, yourItems, scanner, itemsBedroom, itemsBathroom, file);


//go to living room and stab and call for help


                LivingRoom.goToLivingRoomFinal(player, yourItems, scanner, itemsLivingroom, file);
                boolean rooms;
                rooms = LivingRoom.checkBackpack(yourItems);

                //stab + call = end
//System.out.println(rooms);
                yourItems = LivingRoom.endGame(rooms, yourItems, scanner, file);

                break;

            case ("old one"):

               Room.getUserInputToTXT(file, scanner.nextLine() );

                break;
                default:

                    break;
            }
        }





    }
