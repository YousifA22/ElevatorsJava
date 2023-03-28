package Elevator;

/**
 * @author waheebhashmi
 * ElevatorButton class for the elevator's buttons and its lamps inside
 */
public class ElevatorButton {
	private boolean elevatorButtonLamp;

	/**
	 * ElevatorButton to initialize the lamps to off 
	 */
	public ElevatorButton() {
		this.elevatorButtonLamp = false;
	}

	/**
	 * Getter method for elevator's lamp light status (on = true)
	 * @return boolean
	 */
	public boolean getElevatorLampLight() {
		return this.elevatorButtonLamp;
	}

	/**
	 * Setter method for elevator's lamp light status
	 * @param elevatorButtonLamp
	 */
	public void setElevatorLampLight(boolean elevatorButtonLamp) {
		this.elevatorButtonLamp = elevatorButtonLamp;
	}

}
