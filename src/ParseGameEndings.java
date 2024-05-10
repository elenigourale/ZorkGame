import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Enumeration;
import java.util.Hashtable;

public class ParseGameEndings {



    public static class GameEnding {
        private static String condition;
        private static String description;
        private boolean isWin;
        private static Hashtable <Integer, String> EndingItems;

        public GameEnding(String condition, String description, boolean isWin) {
            this.isWin=isWin;
            setCondition(condition);
            setDescription(description);
        }

        public static void setDescription(String descreption) {
            description = descreption;
        }
        public static  void setCondition(String condition1) {condition = condition1;}


        public static  String getCondition() {return condition;}

        public String getDescription() {return description;}

        public boolean getIsWin() {
            return isWin;
        }

        public static Hashtable<Integer, String> getEndingItems() {
            return EndingItems;
        }

        public static Hashtable <Integer,String> setEndingItems(String[] parts) {
           // GameEnding ending = new GameEnding(condition, description, isWin);
            String inputString = getCondition();
            parts = inputString.split(",");
            Hashtable<Integer, String> endingItems = null;
            endingItems.put(1,parts[0]);
            endingItems.put(2, parts[1]);
          return endingItems;
        }


    }


    public static void parseEndings(String filePath, Hashtable<String, GameEnding> endingItems) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filePath);

        doc.getDocumentElement().normalize();

        NodeList endingNodes = doc.getElementsByTagName("ending");  // Adjust tag name if different

        for (int i = 0; i < endingNodes.getLength(); i++) {
            Node endingNode = endingNodes.item(i);

            if (endingNode.getNodeType() == Node.ELEMENT_NODE) {
                Element endingElement = (Element) endingNode;

                // Extract data from XML elements (assuming structure)
               String condition = getElementValue(endingElement, "condition");
               String description = getElementValue(endingElement, "description");
                boolean isWin = Boolean.parseBoolean(getElementValue(endingElement, "isWin")); // Adjust if data type differs

                GameEnding.setDescription(description);
                GameEnding.setCondition(condition);

                // Create GameEnding object and add to Hashtable

                GameEnding ending = new GameEnding(condition, description, isWin);
                endingItems.put(condition,ending);
            }
        }
    }

    // Helper method to get text content of child elements
    private static String getElementValue(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }


    public static String[] getEndingItemsXML() throws Exception {
        String filePath = "C:\\Users\\30698\\Desktopww\\ZorkGame\\src\\gameEndings.xml";
        Hashtable<String, ParseGameEndings.GameEnding> endingItems = new Hashtable<>();
        ParseGameEndings.parseEndings(filePath, endingItems);

        String[] parts=new String[10];
// Access endingItems
        if (!endingItems.isEmpty()) {
            Enumeration<String> keys = endingItems.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                ParseGameEndings.GameEnding ending = endingItems.get(key);
                System.out.println("Condition (" + key + "): " + ending.getCondition());
                parts = key.split(",");
                System.out.println("Description: " + ending.getDescription());
            }
        }

     //   System.out.println(parts[0]);
    return parts;
    }

}