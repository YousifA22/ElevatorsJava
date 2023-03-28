# Sysc3303Project

## Responsibilities
**Christopher Fagan-Maber**: Floor subsystem  
**Waheeb Hashmi**: Unit testing  
**Filip Galic**: Sequence Diagrams, Class diagrams, State Machines Diagrams  
**Yousif Al-Sendi**: Elevator subsystem
**Abdurahman Jama**: Scheduler subsystem  

## Files
**FloorSubSystem.java:** Runs a thread that reads events from an event.txt file and sends them to the Scheduler and receives a response when an event has been proccessed. Uses classes Floor.java, FloorButton.java, FloorLamp.java, Event.java. Added logging.
**Scheduler.java:** Runs a thread that receives events from the FloorSubSystem and adds them to a request queue that the Elevator takes from. The Elevator then sends a response back to the Scheduler to add to a response queue that the FloorSubSystem takes from to be notified that the event was received. Added States and logging.
**Elevator.java:** Runs a thread that receives requests, then proccesses them (move floors, load/unload) and sends a response back to the Scheduler indicating that the event was proccessed. Uses classes ElevatorButton.java, ElevatorDoorStatus.java, ElevatorMotor.java. Added states and logging.

## Set Up Instructions
To Run System: Run Main.java  
  
To Run Test: Run SystemTest.java  

