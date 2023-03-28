package Floor;

public class Event {

    private String time;
    private int floorNumber;
    private String direction;
    private int carButton;

    /**
     * contructor that initializes parameters
     * @param time
     * @param floorNumber
     * @param direction
     * @param carButton
     */
    public Event(String time, int floorNumber, String direction, int carButton){
        this.time = time;
        this.floorNumber = floorNumber;
        this.direction = direction;
        this.carButton = carButton;
    }

    /**
     * returns the floor number
     * @return a integer that represents the floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * returns a integer CarButton
     * @return a integer that represents what floor to go to
     */
    public int getCarButton() {
        return carButton;
    }

    /**
     * returns a String that represents the direction
     * @return String direction that represents where the elevator goes
     */
    public String getDirection() {
        return direction;
    }

    /**
     * returns the time
     * @return a integer that represents the time
     */
    public String getTime() {
        return time;
    }
}

