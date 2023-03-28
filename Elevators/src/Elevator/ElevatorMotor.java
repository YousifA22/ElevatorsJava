package Elevator;

/**
 * @author waheebhashmi
 * ElevatorMotor class for the elevator's motor 
 */
public class ElevatorMotor {
	private ElevatorState motorState;

	/**
	 * ElevatorMotor constructor to initialize the motor to be off at the start
	 */
	public ElevatorMotor() {
		this.motorState = ElevatorState.STOPPED;
	}

	/**
	 * Getter method for the elevator's motor state (running, stopped, or stopping)
	 * @return ElevatorState
	 */
	public ElevatorState getMotorState() {
		return this.motorState;
	}

	/**
	 * Setter method for the elevator's motor state (running, stopped, or stopping)
	 * @param state
	 */
	public void setMotorState(ElevatorState state) {
		this.motorState = state;
	}
	
	//for testing purposes
	public String getMotorStateString() {
		return this.motorState.name();
	}

}