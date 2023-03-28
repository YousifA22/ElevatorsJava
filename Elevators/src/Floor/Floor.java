package Floor;

/**
 * Floor class
 * @author chrisfm
 *
 */
public class Floor {
    private int floorNumber;
    private FloorButton upButton;
    private FloorButton downButton;

   /**
    * contructor that initializes variables/parameters
    * @param floorNumber
    * @param up
    * @param down
    * @param upLamp
    * @param downLamp
    */
    public Floor(int floorNumber, FloorButton up, FloorButton down, FloorLamp upLamp, FloorLamp downLamp){
        this.floorNumber = floorNumber;
        if(up != null) {
        	this.upButton = up;
        }
        if(upLamp != null) {
        	up.addLamp(upLamp);
        }
        if(down != null) {
        	this.downButton = down;
        }
        if(downLamp != null) {
        	down.addLamp(downLamp);
        }
    }

    /*
     * gets the floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * gets the upButton
     * @return a FloorButton
     */
    public FloorButton getUpButton(){
        return upButton;
    }

    /**
     * gets the downButton
     * @return a FloorButton
     */
    public FloorButton getDownButton(){
        return downButton;
    }


}
