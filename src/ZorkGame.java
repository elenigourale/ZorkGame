import java.util.*;


public class ZorkGame {
    static ZorkModel model = new ZorkModel();
    static ZorkView view = new ZorkView();
    private static ZorkController controller = new ZorkController(model, view);
    private static Player player;

    static Hashtable<Integer, String> yourItems= new Hashtable<>(10);

    public static void main(String[] args) {
    player=controller.makeHomeArchitecture();
        Room livingRoom = new Room("living room");
        Room kitchen = new Room("kitchen");
        Room bedroom = new Room("bedroom");
        Room bathroom = new Room("bathroom");
        Hashtable<Integer, String> itemsKitchen = Kitchen.initKitchenRoomItems();
        Hashtable<Integer, String> itemsBedroom = Bedroom.initBedroomRoomItems();
        Hashtable<Integer, String> itemsBathroom = Bathroom.initBathroomItems();
        Hashtable<Integer,String>  itemsLivingroom=LivingRoom.initLivingRoomItems();
      //  Room.getHashtableItems(itemsKitchen);
        // getHashtableItems(itemsBedroom);

       // kitchen.getHashtableItems(itemsKitchen);
        // Έναρξη παιχνιδιού
        Scanner scanner = new Scanner(System.in);
        System.out.println("somebody is in your living room, quick go find sth in the kitchen");

        System.out.println("You are now in: " + livingRoom.getDescription());
           //int hisThesis;



        //living room to kitchen
        boolean rooms=true;
        LivingRoom.goToLivingroom( rooms, player, scanner);
// kitchen to grab knife
        rooms=true;
 yourItems=Kitchen.grabKnife(rooms,yourItems,scanner,itemsKitchen);

//kitchen to bedroom

     rooms=true;
        Kitchen.kitchenToLivingRoom( rooms,  yourItems,  scanner,  player);
//bedroom to hide and grab phone
        rooms=true;
        yourItems=  Bedroom.findPhone( rooms, player, yourItems, scanner, itemsBedroom, controller);
//go to bathroom
        rooms=true;
        Bathroom.goToBathroom(rooms, player, yourItems, scanner, itemsBedroom);



//go to living room and stab and call for help


        rooms=true;
        LivingRoom.goToLivingRoomFinal( rooms, player, yourItems, scanner);

       rooms=true;

        //stab + call = end

            yourItems = LivingRoom.endGame(rooms, yourItems, scanner);








    }
}