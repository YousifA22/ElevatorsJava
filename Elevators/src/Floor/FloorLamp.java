package Floor;

/**
 * FloorLamp class
 * @author chrisfm
 *
 */
public class FloorLamp {
    private boolean lamp;

    /**
     * constructor initializes variables
     */
    public FloorLamp(){
        lamp = false;
    }

    /**
     * turns the lamp on
     */
    public void toggle(){
        lamp = true;
    }

    /**
     * check the state of the lamp
     * @return boolean that represents the lamps state
     */
    public boolean lampStatus(){
        return lamp;
    }
}

