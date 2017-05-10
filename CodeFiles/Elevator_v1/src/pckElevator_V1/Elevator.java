package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;

public class Elevator implements IElement {

    // data
    //------------------------------
    public static int maxFloor = 0;
    public static int minFloor = 0;

    private static final int DIRECTIONUP = +1;
    private static final int DIRECTIONDOWN = -1;
    private static final int STOP = 0;
    private static final int MOVING = 1;
    private static final int ARRIVED = 2;

    //  -1..Down +1..Up
    private int direction = STOP;

    // internal floor index
    private int STATE;
    private int currentFloor; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String label;   // from ElevatorBank updateConfiguration()

    private ArrayList< Integer > tempEBCallList = new ArrayList<>();
    private ArrayList< IVisitor > riders;
    private ArrayList< IElement > bldElements;
    private ArrayList< Integer > visitList;
    private int numberOfVisitors = 0;
    private int requestedFloor;
    private static int elevatorsNumber;
    private IVisitor tempBoardingIndidual;
    private Boolean doorsAreOpen = false;
    
    //------------------------------
    // constructors
    //------------------------------
    public Elevator(String label, int floor, int elevatorNumber ) {
        this.tempEBCallList = new ArrayList<>();
        this.riders = new ArrayList<>();
        this.visitList = new ArrayList<>();
        this.bldElements = new ArrayList<>();
        this.visitList.add(-1);
        this.label = label;
        this.numberOfVisitors = riders.size();
        this.elevatorsNumber = elevatorNumber;
        this.currentFloor = floor;
        this.tempEBCallList = ElevatorBank.GetInstance().getFloorDispatchList();
        this.state = STOP;
        System.out.println("this elevator's name = " + this.toString());
    }
    
    //------------------------------
    // operations
    //------------------------------
    public  void  elevatorWakeUp() {
        // update the ElevatorBank list of existing floor visits
        tempEBCallList = ElevatorBank.GetInstance().getFloorDispatchList();
        // Each cycle through the tread checks for a call
         validateRequest();
    }
    public void validateRequest(){
        // check this elevator. if it's available/ stoped, allow to check calls
        System.out.println("\tElevator: validateRequest: PRECHeck ElevatorBank floorDispatchList = " +tempEBCallList.size());
        if (this.state == STOP ) {    // check state of this elevator
            // if this elevator is available/stoped, allow to pick up visitor 
            elevatorCallRequested();  // this calls the move if asked
        }
        if (this.state == MOVING ){
            move();
        }
        if (this.state == ARRIVED ) {
            arrived();
        }
    }// validateRequest()
    
    
    public void elevatorCallRequested() {
        // update the elvatorBank Tempcall list  
        System.out.println("this."+label+" inside floorDispatchList size= " +tempEBCallList.size());
        // Check each floor
        for (int idx = 0; idx < maxFloor ; ++idx) {
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1:");
            // if there is a floor that is requesting an elevator
            int testFloor = ElevatorBank.GetInstance().getFloors().get(idx).getThisFloorsNumber();
            Boolean value = ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator();
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1a: not added yet floor: " + testFloor+" is "+value);
            if (ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator()) {  // if TRUE
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1b: checking floor " + testFloor);    
                // check if this floor is already on the list
                if (!tempEBCallList.contains(idx)){
                    visitList.add(idx);         // for this elevator
                    tempEBCallList.add(idx);    // for the ElevatorBank
                    state = MOVING;         //chenge elevator to moving
                    ElevatorBank.GetInstance().setFloorDispatchList(tempEBCallList); //update ElevatorBank
                    System.out.println("DEBUG: elevatorCallRequested():****\t\tHurdle 1c: added Floor: " + visitList.get(idx));
                    System.out.println("DEBUG: elevatorCallRequested():****\t\tELEVATORBAN FLOOR CALLS! calls = " +ElevatorBank.GetInstance().getFloorDispatchList().size());
                }
            }
        } // for
       
        if(state == MOVING){
            setMoveDirection();
       }
    } // elevatorCallRequested()
    
    public void setMoveDirection(){
        if (visitList.get(1)> currentFloor) {
            direction = DIRECTIONUP;
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 2 Set the Direction = UP!");
        }
        else if (visitList.get(1)< currentFloor) {
            direction = DIRECTIONDOWN;
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 2 Set the Direction = UP!");
        }
        else if ( visitList.get(1)== currentFloor){
            direction = STOP;
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 2 requestedFlloor = CurrentFloor: "+ currentFloor);
        }
    }// setMoveDirection()
            
    public void move() {
        System.out.println("DEBUG: move(): Hurdle 3 on the move()\n");
        // Where should the Elevator move?
        int newFloor = this.currentFloor + direction;  
        if (newFloor >= maxFloor) {
            this.currentFloor = maxFloor - 1;
            this.direction = DIRECTIONDOWN;
            System.out.println("DIRECTIONDOWN down; this.floorInt = maxFloor - 1;");
        } else if (newFloor <= minFloor) {
            this.currentFloor = minFloor;
            this.direction = DIRECTIONUP;
            System.out.println("DIRECTIONUP down; this.floorInt = minFloor;;");
        } else {
            this.currentFloor = newFloor;
            this.direction = STOP;
            this.state = ARRIVED;
            // update the floor to shut off it's floor light
            System.out.println("DEBUG: move(): Hurdle 3 set to Arrived to floor !" + currentFloor +" and stopped!");
        }
        System.out.println("DEBUG move():***\t***\tUpdatedFloorInt = " +label+" = "+currentFloor);
    }//move()
    
    public void arrived() {
        elevatorOpenDoor(); // open the elevator doors
        System.out.println("DEBUG arrived():***\t***\tCurrent floor = " +label+" = "+currentFloor);
        ElevatorBank.GetInstance().getFloor(currentFloor).setCallElevator(Boolean.FALSE);
        //update riders that they have arrived to the desired floor
        for (int idx = 0; idx <= riders.size()-1; ++idx){
            riders.get(idx).setFloorInt(currentFloor);
        }

    } // arrived()
    
    @Override
    public void accept( ArrayList<IVisitor> boardingParty) {
        // tell first Elevator to accept this visitor
        this.riders = boardingParty;
        numberOfVisitors = this.riders.size();
        System.out.println("DEBUG: Elevator: " + label + " accept(): riders: " + riders.toString() );
//        for (int idx = 0; idx <= riders.size(); ++idx){
//            riders.get(idx).configVisitorRoutine();
//        }
    }
    @Override
    public void accept( IVisitor boardingParty) {
        // tell first Elevator to accept this visitor
        riders.add(boardingParty);
        numberOfVisitors = riders.size();
        System.out.println("DEBUG: Elevator: " + label + " accept(): riders: " + boardingParty.toString() );
    }
    
    @Override
    public void release(IVisitor visitor) {
        // to do: create a check for elevator state
        assert (state == state) :
                "Bad attempt to release visitor from the elevator";
        // it is an iterator. 
        Iterator<IVisitor> it = riders.iterator();
        for (; it.hasNext();) {
            IVisitor rider = it.next();
            // compare both
            if (rider == visitor) {

                it.remove();
                continue;
            }
        }
    }
    public void elevatorOpenDoor(){
        this.doorsAreOpen = true;     // this elevator opens door
        System.out.println("DEBUG: ElevatorDoors are open!\n");
    }
    public void elevatorCloseDoor(){
        this.doorsAreOpen = false;     // this elevator opens door
    }
    public String getLabel() {
        //System.out.println("DEBUG: Elevator: getLabel: " + label);
        return label;
    }

    public int getFloor() {
        //System.out.println("DEBUG: Elevator: getFloor: " + floor);
        return currentFloor;
    }

    public int getState() {
        return state;
    }

    public static void setMaxFloor(int maxFloor) {
        Elevator.maxFloor = maxFloor;
    }

    public int getElevatorRiders() {
        return numberOfVisitors;
    }

    public void setElevatorRiders(int ElevatorRiders) {
        this.numberOfVisitors = ElevatorRiders;
    }

    @Override
    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void setRequestedFloor(int requestedFloor) {
        this.requestedFloor = requestedFloor;
    }

    public Boolean getDoorsAreOpen() {
        return doorsAreOpen;
    }
    
}//class Elevator

