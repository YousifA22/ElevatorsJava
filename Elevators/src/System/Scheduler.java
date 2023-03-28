package System;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.LinkedList;

import Floor.Event;
/**
 * Scheduler class sends communication between the Floor and Elevator classes.
 * 
 * @author Abdurahman Jama 101162633
 */

public class Scheduler implements Runnable{
	private Queue<Event> requestQueue;
	private Queue<Event> responseQueue;
	private SchedulerState schedulerState;
	private Logger logger;
	private Event event = null;
	private boolean end = false;
	
	public Scheduler() {
		this.requestQueue = new LinkedList<Event>();
		this.responseQueue = new LinkedList<Event>();
		this.logger = Logger.getLogger(Scheduler.class.getName());
		this.schedulerState = SchedulerState.IDLE;
	}
	
	/*
	 * Notifies the scheduler of event to put into the request queue.
	 * 
	 * @param request The request the scheduler is being notified to add to request queue.
	 */
	public synchronized void notifyRequest(Event request) {
		while (event != null) { 
			try {
				wait();
			}
			catch (InterruptedException e) {
				return;
			}
		}
		event = request;
		this.schedulerState = SchedulerState.RECEIVED_REQUEST;
		notifyAll();
	}
	
	
	/**
	 * Puts a request into the request queue.
	 */
	public synchronized void putRequest() {
		while (event == null) {
			try {
				wait();
				
			}
			catch (InterruptedException e) {
				return;
			}
		}
		requestQueue.add(event);
		event = null;
		this.schedulerState = SchedulerState.REQUEST_SENT;
		notifyAll();
	}
	
	/*
	 * Gets the first element in request Queue
	 * 
	 * @return The Event that is in the request queue.
	 */
	public synchronized Event getRequest() {
		while (requestQueue.isEmpty()) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				return null;
			}
		}
		notifyAll();
		return requestQueue.remove();
	}
	
	/*
	 * Notifies the scheduler of response to event.
	 * 
	 * @param response The response being notified to the scheduler.
	 */
	public synchronized void notifyResponse(Event response) {
		while (event != null) { 
			try {
				wait();
			}
			catch (InterruptedException e) {
				return;
			}
		}
		event = response;
		this.schedulerState = SchedulerState.RESPONSE_RECEIVED;
		notifyAll();
	}
	
	/**
	 * Puts event in response queue.
	 */
	public synchronized void putResponse() {
		while (event == null) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				return;
			}
		}
		responseQueue.add(event);
		event = null;
		this.schedulerState = SchedulerState.RESPONSE_SENT;
		notifyAll();
	}
	
	public synchronized void notifyEnd() {
		notifyAll();
	}
	
	/*
	 * Gets the first element in response Queue
	 * 
	 * @return The event that is in the response queue.
	 */
	public synchronized Event getResponse() {
		while (responseQueue.isEmpty()) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				return null;
			}
		}
		notifyAll();
		return responseQueue.remove();
	}
	
	public void setTrue() {
		end = true;
	}
	
	//TESTING METHODS
	
	public String getState() {
		return schedulerState.toString();
		
	}
	
	public synchronized void putRequestTesting() {
		while (event == null) {
			try {
				wait();
				
			}
			catch (InterruptedException e) {
				return;
			}
		}
		requestQueue.add(event);
	
		this.schedulerState = SchedulerState.REQUEST_SENT;
		notifyAll();
	}
	
	public synchronized void putResponseTesting() {
		while (event == null) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				return;
			}
		}
		responseQueue.add(event);
	
		this.schedulerState = SchedulerState.RESPONSE_SENT;
		notifyAll();
	}
	
	public void setIdle() {
		this.schedulerState = SchedulerState.IDLE;
	}
	
	/**
	 * Run method puts requests in request queue and puts reponses into response queue to 
	 * communicate between floor and elevator subsystems.
	 */
	@Override
	public void run() {
		while (true) {
			if(end == true) {
				logger.log(Level.INFO, "Scheduler Thread has completed");
				//System.out.println("Scheduler Thread has completed");
				break;
			}
			putRequest();
			putResponse();
			this.schedulerState = SchedulerState.IDLE;
		}
	}

}
