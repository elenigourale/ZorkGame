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
int hisThesis;
        //living room to kitchen
        boolean rooms=true;

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
// kitchen to grab knife
        rooms=true;
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

//kitchen to bedroom

     rooms=true;

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
//bedroom to hide and grab phone
        rooms=true;
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
                    bedroom.getHashtableItems(itemsBedroom);
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
//go to bathroom
        rooms=true;
        Bathroom.goToBathroom(rooms, player, yourItems, scanner, itemsBedroom);



//go to living room and stab and call for help


        rooms=true;
        LivingRoom.goToLivingRoomFinal( rooms, player, yourItems, scanner);

       rooms=true;

        //stab + call = end
 yourItems=LivingRoom.endGame(rooms, yourItems, scanner);








    }
}