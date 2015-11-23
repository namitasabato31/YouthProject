package youth.in2it.com.youthproject;

/**
 * Created by IN2IT on 11/18/2015.
 */
public class UserData {


    String name = null;
    boolean selected = false;

    public UserData(String name, boolean selected) {
        super();

        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

