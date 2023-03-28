package System;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import Elevator.Elevator;
import Elevator.ElevatorDoorState;
import Floor.Event;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Youisf Alsendi
 * SystemTest in order to test that all three threads are communicating properly and sending correct info
 * 
 */

class SystemTest {


	
	@Test
	void test() throws InterruptedException, IOException {
		
		Scheduler scheduler = new Scheduler();
		
		FloorSubsystem floor= new FloorSubsystem(scheduler);
		
		Elevator elevator = new Elevator(scheduler);
		
		int intialStartingFloor;
		int intialDesFloor;
		int finalStartingFloor;
		int finalDesFloor;
		
		intialStartingFloor= elevator.getCurrentFloor();
		intialDesFloor=elevator.getDestinationFloor();   //getting all initial starting points
		
		floor.getFile();
		floor.readFile();
		floor.sendToScheduler();       //floorsubsystem sends the info to the scheduler
		
 
		scheduler.putRequestTesting();         //Scheduler adds the info to the queue
	
		elevator.changeFloors();       //after the elevator receives the info from the scheduler
		
		
		finalStartingFloor= elevator.getCurrentFloor();
		finalDesFloor=elevator.getDestinationFloor();
		
		
		assertEquals(intialStartingFloor,0);
		
		
		assertEquals(finalStartingFloor,2);
		

		// testing elevatorStatus
		
		System.out.print("Testing Elevator Status\n Motor state: Expected : STOPPED, Actual : " + elevator.getMotorState()+"\n");
		
		assertEquals(elevator.getMotorState(),"STOPPED");
		
		System.out.print("Testing Elevator Status\n Door state: Expected : CLOSED, Actual : " + elevator.getElevatorDoorState()+"\n");
		
		assertEquals(elevator.getElevatorDoorState(),"CLOSED");
		
		//checking if doorState function
		
		elevator.doorOpenAction();
		
		System.out.print("Testing Elevator Status\n Door state when opened: Expected : OPEN, Actual : " + elevator.getElevatorDoorState()+"\n");
		
		assertEquals(elevator.getElevatorDoorState(),"OPEN");
		
		
		//Checking motor state 
		
		elevator.MotorStateCrusing();
		
		System.out.print("Testing Elevator Status\n Motor state when crusing: Expected : CRUISING, Actual : " + elevator.getMotorState()+"\n");
		
		assertEquals(elevator.getMotorState(),"CRUISING");
		
		
		//checking if the whole system works
		
		elevator.moveToFloorTesting(0, 2,"up");
		
		System.out.print("Testing Elevator Status\n Motor state after moving floors: Expected : STOPPED, Actual : " + elevator.getMotorState()+"\n");
		
		assertEquals(elevator.getElevatorDoorState(),"CLOSED");
		
		System.out.print("Testing Elevator Status\n Door state after moving: Expected : CLOSED, Actual : " + elevator.getElevatorDoorState()+"\n");
		
		assertEquals(elevator.getMotorState(),"STOPPED");
		
		
		
		
		
		
		
		//Checking Scheduler States
		scheduler.putRequestTesting();
		
		System.out.print("Testing Scheduler States \n Scheduler state after putting request: Expected : REQUEST_SENT, Actual : " + scheduler.getState()+"\n");
		
		assertEquals(scheduler.getState(),"REQUEST_SENT");
		
		scheduler.putResponseTesting();
		
		System.out.print("Testing Scheduler States \n Scheduler state after putting responce: Expected : RESPONSE_SENT, Actual : " + scheduler.getState()+"\n");
		
		assertEquals(scheduler.getState(),"RESPONSE_SENT");
		
		scheduler.setIdle();
		
		System.out.print("Testing Scheduler States \n Scheduler state aftergoing idle: Expected : IDLE, Actual : " + scheduler.getState()+"\n");
		
		assertEquals(scheduler.getState(),"IDLE");
	
		
		
		
		
		
	}
}




