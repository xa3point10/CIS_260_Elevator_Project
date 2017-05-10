package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class VisitorShopper implements /*extends*/ IVisitor {

    Random rand = new Random();
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    private static final int WAITING = 4;
    private static final int ARRIVING = 5;

    // internal floor index
    private int floorInt = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing, waiting
    private String myFloorLabel;   // from ElevatorBank updateConfiguration()
    private int maxFloor;
    private int currentFloor;
    //private int myFloorIndex = 0;
    private int desiredFloorIndex = 0;
    private int nextDesiredFloor;

    private ArrayList<Integer> visitorAgenda;
    private ArrayList<Integer> myFloorHistory;
    private ArrayList<IElement> bldElements;
    private ArrayList<Elevator> elevators;
    private ArrayList<Floor> floors;

    // Constructor
    public VisitorShopper() {
        this.visitorAgenda = new ArrayList<>();
        this.myFloorHistory = new ArrayList<>();
        this.bldElements = new ArrayList<>();
        elevators = ElevatorBank.GetInstance().getElevators();
        floors = ElevatorBank.GetInstance().getFloors();
    }

    @Override
    public void configVisitorRoutine() {
        // When This is createed, 
        // They know how many floors are in the building
        System.out.println("DEBUG: VsitorShoper MaxFloor check = " + maxFloor);
        // They know the order they want to visit the floors
        visitorAgenda.add(1);       // They begin on this floor #
        myFloorHistory.add(1);      // add to the visit history
        visitorAgenda.add(2);       //and then they want to visit here
        //visitorAgenda.add(randFloorPicker(maxFloor)); //and visit here
        visitorAgenda.add(0);       // End back in the garage
        // begin in visiting state 
        state = VISITING;
        // visitor gets passed to the first floor in the agenda
        currentFloor = visitorAgenda.get(desiredFloorIndex);  // begin in index 0
        System.out.println("DEBUG: thisVisitors\t\t CurrentFloor = " + currentFloor);
        desiredFloorIndex++; // increment the endex
        this.nextDesiredFloor = visitorAgenda.get(desiredFloorIndex);
        // begin this visitors life on the first currentFloor
        ElevatorBank.GetInstance().getFloor(currentFloor).accept(this);
        state = VISITING;
        //System.out.println("DEBUG: Visitor: configVisRoutine Fully configured on current Floor: " + currentFloor);
        beginAgendaProtocol();
    } // configVisitorRoutine()        

    @Override
    public void wakeUpVisitor() {
        System.out.println("DEBUG: Visitor:\t************wakeUpVisitor(): preCheck state = " + state);
        beginAgendaProtocol();
    }

    public void beginAgendaProtocol() {
        elevators = ElevatorBank.GetInstance().getElevators();
        floors = ElevatorBank.GetInstance().getFloors();
        switch (state) {
            case CALLING:   // = 1
                // check to see if elevator is arrived in first element in Agenda list
                System.out.println("DEBUG: this.visitor is calling on " + currentFloor);
                //System.out.println("DEBUG: this.visitor is calling on " + floorInt);
                for (Elevator elevator : elevators) {
                    if (elevator.getDoorsAreOpen() == true) {
                        elevator.accept(this);
                        System.out.println("DEBUG: this.visitor" + this.getClass().toString()+" was accepted to elevator" + elevator.toString());
                
                    }
                }
                for (Floor floor : floors) {
                    if (floor.getThisFloorsNumber() == currentFloor) {
                        floor.release(this);
                    }
                }
                // get the next element in the visitor agenda
                state = RIDING;         // update the state 
                break;

            case RIDING:    // = 2
                //check if the elevator has reached nextDesiredFloor
//                for (Elevator elevator : elevators) {
//                    if (elevator.getFloor() == this.floorInt) {
//                        elevator.release(this);
//                    }
//                }
//                for (Floor floor : floors) {
//                    if (floor.getThisFloorsNumber() == this.nextDesiredFloor) {
//                        floor.accept(this);
//                    }
//                }
//                state = VISITING;  // set state equal to waiting/visiting
                break;
                
            case VISITING:  // = 3
                state = CALLING;  // visit this floor one time 
                // then push the call button
                for (Floor floor : floors) {
                    if (floor.getThisFloorsNumber() == currentFloor) {
                        floor.setCallElevator(Boolean.TRUE);
                    }
                }
                break;
                
            case WAITING:    // = 4;
                state = CALLING;  // lives one cycle in the waiting state
                break;
                
            case ARRIVING:    // = 5;
                //update to next desired Floor
                Iterator<Integer> it = visitorAgenda.iterator();
                for (; it.hasNext();) {
                    Integer itFloor = it.next();
                    // compare both
                    if (itFloor == this.nextDesiredFloor ) {
                        desiredFloorIndex++;
                        continue;
                    }
                }
                this.nextDesiredFloor = visitorAgenda.get(desiredFloorIndex);
                state = CALLING;
                break;
        }
    }

    // randomize the order of floor visit and add it to OrderOfVisits Array
    public int randFloorPicker(int maxFloor) {
        int floorFloorToVisit = rand.nextInt(maxFloor);
        return floorFloorToVisit;
    }

    // Operations
    @Override
    public void unboard(Floor floor) {          // arrived at floor
        // check each elevator for this visitor
//        for (int idx = 0; idx <= ElevatorBank.GetInstance().getElevators().size(); ++idx){
//            if (ElevatorBank.GetInstance().getElevator(idx).);
//            
//        }
    }// unboard()

    @Override
    public void boardElevator(Elevator elevator, Floor floor) {          // arrived at floor
        // this is called if the elvator has arrived to the floor
        // then this is used to swap from flooor to elevator
        ElevatorBank.GetInstance().getFloors().get(0).release(this);
    }

    @Override
    public void setState(int state) {
        this.state = state;
        System.out.println("DEBUG: \tState changed!\t = " + this.state);
    }

    public int getState() {
        return this.state;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public String getMyFloorLabel() {
        return myFloorLabel;
    }

    public void setMyFloorLabel(String myFloorLabel) {
        this.myFloorLabel = myFloorLabel;
    }

    @Override
    public int getNextDesiredFloorIndex() {
        return desiredFloorIndex;
    }

    @Override
    public ArrayList<Integer> getVisitorAgenda() {
        return visitorAgenda;
    }

    public void setBldElements(ArrayList<IElement> bldElements) {
        this.bldElements = bldElements;
    }

    @Override
    public void setFloorInt(int floorNumber) {
        this.floorInt = floorNumber;
    }

}// class VisitorShopper
