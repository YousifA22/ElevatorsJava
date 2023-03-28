package Elevator;

import java.util.logging.Level;
import java.util.logging.Logger;

import Floor.Event;
import System.Scheduler;

/**
 * 
 * @author waheebhashmi Elevator class in order to manage all movement of the
 *         cars, load/unload passengers, and communicate with the Scheduler
 * 
 */
public class Elevator implements Runnable {
	private int currentFloor;
	private int destinationFloor;
	private String elevatorDirection;
	private ElevatorDoorState elevatorDoorStatus;
	private ElevatorMotor elevatorMotor;
	private ElevatorButton[] elevatorButtons;
	private Scheduler scheduler;
	private int totalFloors;
	private double sampleLoadUnloadTime;
	private double sampleOneFloorTime;
	private Logger logger = Logger.getLogger(Elevator.class.getName());

	/**
	 * Elevator constructor to initialize all variables inside the elevator
	 * 
	 * @param scheduler
	 */
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
		this.currentFloor = 0;
		this.destinationFloor = 0;
		this.elevatorDirection = "";
		this.elevatorDoorStatus = ElevatorDoorState.CLOSED;
		this.elevatorMotor = new ElevatorMotor();
		this.elevatorButtons = new ElevatorButton[totalFloors];
		for (int i = 0; i < totalFloors; i++) {
			elevatorButtons[i] = new ElevatorButton();
		}
		this.sampleLoadUnloadTime = 3.5;
		this.sampleOneFloorTime = 2.43;
		this.totalFloors = 22;
	}

	/**
	 * Getter method for elevator's current floor
	 * 
	 * @return int
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Setter method for elevator's current floor
	 * 
	 * @param currentFloor
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * Getter method for elevator's button by passing in floor number
	 * 
	 * @param floorNumber
	 * @return ElevatorButton
	 */
	public ElevatorButton getButton(int floorNumber) {
		return this.elevatorButtons[floorNumber];
	}

	/**
	 * Getter method for elevator's destination floor
	 * 
	 * @return int
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * Setter method for elevator's destination floor
	 * 
	 * @param destinationFloor
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	/**
	 * Getter method for elevator's door status
	 * 
	 * @return ElevatorDoorStatus
	 */
	public ElevatorDoorState getElevatorDoorStatus() {
		return elevatorDoorStatus;
	}

	/**
	 * Setter method for elevator's door status
	 * 
	 * @param elevatorDoorStatus
	 */
	public void setElevatorDoorStatus(ElevatorDoorState elevatorDoorStatus) {
		this.elevatorDoorStatus = elevatorDoorStatus;
	}

	/**
	 * Getter method for elevator's direction
	 * 
	 * @return String
	 */
	public String getElevatorDirection() {
		return elevatorDirection;
	}

	/**
	 * Setter method for elevator's direction
	 * 
	 * @param elevatorDirection
	 */
	public void setElevatorDirection(String elevatorDirection) {
		this.elevatorDirection = elevatorDirection;
	}

	/**
	 * Method in order to move the elevator, depending on the time it takes to go
	 * from one floor to the next, current floor, destination floor, and direction
	 * 
	 * @param currentFloor
	 * @param destinationFloor
	 * @param elevatorDirection
	 */
	public void moveToFloor(int currentFloor, int destinationFloor, String elevatorDirection) {
		this.elevatorMotor.setMotorState(ElevatorState.ACCELERATING);
		//System.out.println("Elevator going from " + currentFloor + " to " + destinationFloor + ", direction: "
				//+ elevatorDirection);
        logger.log(Level.INFO, "Elevator accelerating: Going from " + currentFloor + " to " + destinationFloor); 

		int remainingFloors = Math.abs(destinationFloor - currentFloor);
		this.elevatorMotor.setMotorState(ElevatorState.CRUISING);
        logger.log(Level.INFO, "Elevator cruising..."); 
        
		try {
			Thread.sleep((((int) (sampleOneFloorTime * remainingFloors)) * 1000));
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		
		this.elevatorMotor.setMotorState(ElevatorState.DECELERATING);
        logger.log(Level.INFO, "Elevator decelerating..."); 
		this.setCurrentFloor(destinationFloor);
		
		//System.out.println("Elevator has reached floor " + destinationFloor);
        logger.log(Level.INFO, "Reached floor number: " + destinationFloor); 

		this.elevatorMotor.setMotorState(ElevatorState.STOPPED);
	}

	/**
	 * methods for testing purposes 
	 */
	
	public void moveToFloorTesting(int currentFloor, int destinationFloor, String elevatorDirection) {
		
		this.elevatorMotor.setMotorState(ElevatorState.ACCELERATING);
		//System.out.println("Elevator going from " + currentFloor + " to " + destinationFloor + ", direction: "
				//+ elevatorDirection);
        logger.log(Level.INFO, "Elevator accelerating: Going from " + currentFloor + " to " + destinationFloor); 

		int remainingFloors = Math.abs(destinationFloor - currentFloor);
		this.elevatorMotor.setMotorState(ElevatorState.CRUISING);
        logger.log(Level.INFO, "Elevator cruising..."); 
        
		try {
			Thread.sleep((((int) (sampleOneFloorTime * remainingFloors)) * 1000));
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		
		this.elevatorMotor.setMotorState(ElevatorState.DECELERATING);
        logger.log(Level.INFO, "Elevator decelerating..."); 
		this.setCurrentFloor(destinationFloor);
		
		//System.out.println("Elevator has reached floor " + destinationFloor);
        logger.log(Level.INFO, "Reached floor number: " + destinationFloor); 

		this.elevatorMotor.setMotorState(ElevatorState.STOPPED);
		doorOpenAction();
		doorCloseAction();
	}
	
	
	public void changeFloors() {
		Event elevatorRequest = scheduler.getRequest();
		int currentFloor = elevatorRequest.getFloorNumber();
		int destinationFloor = elevatorRequest.getCarButton();
		String elevatorDirection = elevatorRequest.getDirection();
		//System.out.println("Scheduler sending Event to Elevator");
		moveToFloor(currentFloor, destinationFloor, elevatorDirection);
		//System.out.println("Elevator sending Event back to Scheduler");
	}
	
	

	
	public void MotorStateCrusing() {
		this.elevatorMotor.setMotorState(ElevatorState.CRUISING);
	}
	public String getElevatorDoorState() {
		
		return elevatorDoorStatus.toString();
	}
	
	public String getMotorState() {
		
		return elevatorMotor.getMotorStateString();
	}
	

	


	/**
	 * Method for the loading/unloading process in order to open the elevator's door
	 */
	public void doorOpenAction() {
		setElevatorDoorStatus(ElevatorDoorState.OPENING);
        logger.log(Level.INFO, "Elevator doors opening..."); 
		try {
			Thread.sleep((((int) (sampleLoadUnloadTime)) * 1000));
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		setElevatorDoorStatus(ElevatorDoorState.OPEN);
        logger.log(Level.INFO, "Elevator doors opened"); 
	}

	/**
	 * Method for the loading/unloading process in order to close the elevator's
	 * door
	 */
	public void doorCloseAction() {
        logger.log(Level.INFO, "Elevator loading/unloading passengers..."); 
		try {
			Thread.sleep((((int) (sampleLoadUnloadTime)) * 1000));
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		setElevatorDoorStatus(ElevatorDoorState.CLOSING);
        logger.log(Level.INFO, "Elevator doors closing..."); 
		try {
			Thread.sleep((((int) (sampleLoadUnloadTime)) * 1000));
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		setElevatorDoorStatus(ElevatorDoorState.CLOSED);
        logger.log(Level.INFO, "Elevator doors closed"); 
	}

	/**
	 * Run method in order to receive request from Scheduler, move elevator, and
	 * notify Scheduler of its completion
	 */
	@Override
	public void run() {
		while (true) {
			Event elevatorRequest = scheduler.getRequest();
	        logger.log(Level.INFO, "Elevator received request from Scheduler"); 
			doorOpenAction();
			int currentFloor = elevatorRequest.getFloorNumber();
			int destinationFloor = elevatorRequest.getCarButton();
			String elevatorDirection = elevatorRequest.getDirection();
			if (currentFloor == 0) {
				//System.out.println("Elevator Thread has completed");
		        logger.log(Level.INFO, "Elevator thread has completed"); 
				scheduler.notifyResponse(elevatorRequest);
				break;
			}
			doorCloseAction();
			//System.out.println("Scheduler sending Event to Elevator");
	        logger.log(Level.INFO, "Scheduler sending Event to Elevator"); 
			moveToFloor(currentFloor, destinationFloor, elevatorDirection);
			//System.out.println("Elevator sending Event back to Scheduler");
	        logger.log(Level.INFO, "Elevator sending Event back to Scheduler"); 
			scheduler.notifyResponse(elevatorRequest);
		}
	}

}
