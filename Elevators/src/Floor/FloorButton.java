package Floor;

/**
 * FloorButton class
 * @author chrisfm
 *
 */
public class FloorButton {
    private String name;
    private FloorLamp lamp;

    /**
     * constructor that initializes the objects 
     * @param String name that represents the type of button
     */
    public FloorButton(String name){
        this.name = name;
    }

    /**
     * gets the name of the button
     * @return String name 
     */
    public String getName() {
        return name;
    }

    /**
     * adds a lamp to the button
     * @param lamp 
     */
    public void addLamp(FloorLamp lamp){
        this.lamp = lamp;
    }

    /**
     * toggles the button
     */
    public void pressButton(){
        lamp.toggle();
    }

    /**
     * checks if the button was pressed
     * @return a boolean that represents if the button is on
     */
    private boolean isPressed(){
        return lamp.lampStatus();
    }
}

