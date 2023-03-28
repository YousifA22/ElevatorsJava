package System;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.logging.Logger;

import Elevator.Elevator;
import Floor.Event;
import Floor.Floor;
import Floor.FloorButton;
import Floor.FloorLamp;

/**
 * @author chrisfm
 * 
 * FloorSubsystem class 
 *
 */
public class FloorSubsystem implements Runnable {
    private ArrayList<Floor> floors;
    private Scheduler scheduler;
    private Floor onFloor;
    private ArrayList<Event> Queue;
	private Object line;
	private BufferedReader reader;
	private Event event;
	private Logger logger;
    private static final int FLOORS = 22;

    /**
     * FloorSubsystem contructor creates 22 floors and initializes objects
     * @param scheduler
     */
    public FloorSubsystem(Scheduler scheduler){
        floors = new ArrayList<>();
        Queue = new ArrayList<>();
        logger = Logger.getLogger(FloorSubsystem.class.getName());
        this.scheduler = scheduler;
        for(int i = 1; i <= FLOORS; i++){
            if(i == 1) {
                floors.add(new Floor(1, new FloorButton("up"), null, new FloorLamp(), null));
            }
            else if(i == 22){
                floors.add(new Floor(22 , null, new FloorButton("down") , null, new FloorLamp()));
            }
            else{
                floors.add(new Floor(i , new FloorButton("up"), new FloorButton("down"),
                        new FloorLamp(), new FloorLamp()));
            }
        }
    }
    
    /**
     * Gets the file that it reads from
     * @throws FileNotFoundException
     */
    public void getFile() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("event.txt"));
    }
    /**
     * Reads the file (one line)
     * @throws IOException
     */
    public void readFile() throws IOException {
        line = reader.readLine();
        if (line == null){
            return;
        }
            String[] arr = ((String) line).split(" ");
            event = new Event(arr[0], Integer.parseInt(arr[1]), arr[2], Integer.parseInt(arr[3]));

    }
    /**
     * sends the event created to the scheduler
     */
    public void sendToScheduler() {
    	logger.log(Level.INFO, "Floor sending Event to Scheduler");
    	scheduler.notifyRequest(event);
    }
    
    /**
     * Receive the event back from the scheduler 
     */
    public void recieveFromScheduler() {
    	Event e = scheduler.getResponse();
    	logger.log(Level.INFO, "FloorSubsystem got response");
    }


    @Override
    public void run() {
    	try {
			getFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while(true){
            try {
				readFile();
				if(line == null){
					scheduler.notifyRequest(new Event(null, 0, null, 0));
					scheduler.setTrue();
					logger.log(Level.INFO, "FloorSubsystem Thread has completed ");
	                break;
	            }
				sendToScheduler();
				recieveFromScheduler();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        
    }
}

