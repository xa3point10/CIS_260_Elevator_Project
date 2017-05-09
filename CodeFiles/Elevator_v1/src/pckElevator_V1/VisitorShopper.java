package pckElevator_V1;

import java.util.ArrayList;
import java.util.Random;

public class VisitorShopper implements /*extends*/ IVisitor {

    Random rand = new Random();
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    private static final int WAITING = 4;

    // internal floor index
    private int floor = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing, waiting
    private String myFloorLabel;   // from ElevatorBank updateConfiguration()
    private int maxFloor;
    private int currentFloor;
    //private int myFloorIndex = 0;
    private int desiredFloorIndex = 0;
    
    private ArrayList<Integer> visitorAgenda;
    private ArrayList<Integer> myFloorHistory;
    private ArrayList<IElement> bldElements;

    // Constructor
    public VisitorShopper() {
        this.visitorAgenda = new ArrayList<>();
        this.myFloorHistory = new ArrayList<>();
        this.bldElements = new ArrayList<>();
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
        ElevatorBank.GetInstance().getFloor(currentFloor).accept(this);
        state = WAITING;
        //System.out.println("DEBUG: Visitor: configVisRoutine Fully configured on current Floor: " + currentFloor);
        beginAgendaProtocol();
    } // configVisitorRoutine()        
    
    @Override
    public void wakeUpVisitor() {
        //beginAgendaProtocol();
    }
    
    public void beginAgendaProtocol() {
        ArrayList<Elevator> elevators = ElevatorBank.GetInstance().getElevators();
        switch (state) {
            case CALLING:   // = 1
                // check to see if elevator is arrived in first element in Agenda list
                for (Elevator elevator : elevators) {
                    if (elevator.getFloor() == visitorAgenda.get(desiredFloorIndex)) {
                        elevator.accept(this);
                        ElevatorBank.GetInstance().getFloor(desiredFloorIndex).release(this);
                        state = RIDING;
                    }
                }
                break;
            case RIDING:    // = 2
                
                //++desiredFloorIndex;
                break;
            case VISITING:  // = 3
                break;
            case WAITING:    // = 4;
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

    public void setState(int state) {
        this.state = state;
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

}// class VisitorShopper
