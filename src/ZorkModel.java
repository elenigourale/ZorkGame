import java.util.ArrayList;
import java.util.List;

public class ZorkModel {
    private static int points=0;
    private String  yourName="des";

    List<String> objects = new ArrayList<>();
    List<String> commands = new ArrayList<>();
    public static int getYourPoints()
    {
        return points;

    }
    public int initialisePoints()
    {
        this.points=0;
        return this.points;

    }
    public  void setYourPoints(int points)
    {
        this.points=points+this.points;
    }


public void newObjects(String newObject)
{
    objects.add(newObject);
}


    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }


}
