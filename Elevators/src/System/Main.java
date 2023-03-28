package System;
import java.util.logging.Level;
import java.util.logging.Logger;

import Elevator.Elevator;

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Logger logger = Logger.getLogger(Main.class.getName());
		Scheduler scheduler = new Scheduler();
		Thread elevatorThread = new Thread(new Elevator(scheduler));
		Thread floorThread = new Thread(new FloorSubsystem(scheduler));
		Thread schedulerThread = new Thread(scheduler);
		schedulerThread.start();
		floorThread.start();
		elevatorThread.start();	
		
		schedulerThread.join();
		floorThread.join();
		elevatorThread.join();
		
		logger.log(Level.INFO, "Main Thread has completed ");
	}
}
