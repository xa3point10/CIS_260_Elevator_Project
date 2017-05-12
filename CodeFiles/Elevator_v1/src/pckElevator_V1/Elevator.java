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
    private static final int DEPARTING = 3;
    private static final int STARTDROPOFF = 4;
    private static final int DONEDROPOFF = 5;

    //  -1..Down +1..Up
    private int direction = STOP;

    // internal floor index
    private int currentFloor; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String label;   // from ElevatorBank updateConfiguration()

    private ArrayList< Integer> elevatorBankCallList = new ArrayList<>();
    private ArrayList< IVisitor> riders;
    private ArrayList< IElement> bldElements;
    private ArrayList< Integer> visitList;
    private int numberOfVisitors = 0;
    private int requestedFloor;         // passed in by each visitor
    private static int elevatorsNumber;
    private IVisitor tempBoardingIndidual;
    private Boolean doorsAreOpen = false;
    private int tempEBCallListIdx = 0;
    private int tempEBindexListSize = 0;

    //------------------------------
    // constructors
    //------------------------------
    public Elevator(String label, int floor, int elevatorNumber) {
        this.elevatorBankCallList = new ArrayList<>();
        this.riders = new ArrayList<>();
        this.visitList = new ArrayList<>();
        this.bldElements = new ArrayList<>();
        this.visitList.add(-1);
        this.label = label;
        this.numberOfVisitors = riders.size();
        this.elevatorsNumber = elevatorNumber;
        this.currentFloor = floor;
        this.state = STOP;
        System.out.println("this elevator's name = " + this.toString());
    }

    //------------------------------
    // operations
    //------------------------------
    public void elevatorWakeUp() {
        // update the ElevatorBank list of existing floor visits
        elevatorBankCallList = ElevatorBank.GetInstance().getFloorDispatchList();
        // Each cycle through the tread checks for a call
        validateRequest();
    }

    public void validateRequest() {
        // check this elevator. if it's available/ stoped, allow to check calls
        System.out.println("Elevator: "+this.getLabel()+" -> validateRequest-> STATE = "+this.state+" floor = "+this.getFloor()+", confirm [ElevatorBank] floorDispatchList = " + elevatorBankCallList.size());
        if (this.state == STOP) {    // check state of this elevator
            // if this elevator is available/stoped, allow to pick up visitor 
            elevatorCallRequested();  // this calls the move if asked
        }
        if (this.state == MOVING) {
            setMoveDirection();
            move();
        }
        if (this.state == ARRIVED) { // arriving for pickup
            arrived();
        }
        if (this.state == DEPARTING) {
            System.out.println("*******\t*******\t*******\t*******\t*******\t*******\t*******\t*******\t");
            System.out.print("DEBUG: ELEVATOR switchstatement: STATE = Departing, visitList size = " + visitList.size() + " destination ALL floors = ");
            for (int idx = 0; idx <= visitList.size() - 1; ++idx) {
                System.out.print(visitList.get(idx).toString() + ", ");
            }
            System.out.println("*******\t*******\t*******\t*******\t*******\t*******\t*******\t*******\t");
            visitList.remove(tempEBCallListIdx);    // remove old destination
            System.out.println("DEBUG: ELEVATOR switchstatement: STATE = DEPARTING, visitList size = [" + visitList.size() + " element 0 = " + visitList.get(0).toString());
            visitList.add(requestedFloor); // add new destimation
            System.out.println("DEBUG: ELEVATOR switchstatement: STATE = DEPARTING, visitList size = [" + visitList.size() + " adjusted] element 1 for next desiredFloor  = " + visitList.get(1));
            System.out.println("*******\t*******\t*******\t*******\t*******\t*******\t*******\t*******\n\n\n");
            setMoveDirection();
            elevatorCloseDoor();           // close the foors
            this.state = MOVING;
        }
        if (this.state == STARTDROPOFF) {// arriving for droppOff
            this.state = DONEDROPOFF;
        }
        if (this.state == DONEDROPOFF) {
            this.state = STOP;
            elevatorCloseDoor();
        }

    }// validateRequest()

    public void elevatorCallRequested() {
        // update the elvatorBank Tempcall list  
        System.out.println("DEBUG: Elevator: elevatorCallRequested()this." + label + " inside floorDispatchList size= " + elevatorBankCallList.size());
        // Check each floor
        for (int idx = 0; idx < maxFloor; ++idx) {
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1:");
            // if there is a floor that is requesting an elevator
            int testFloor = ElevatorBank.GetInstance().getFloors().get(idx).getThisFloorsNumber();
            Boolean value = ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator();
            System.out.println("DEBUG: elevatorCabllRequested(): Hurdle 1a: not added yet floor: " + testFloor + " is " + value);
            if (ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator()) {  // if TRUE
                System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1b: checking floor " + testFloor);
                // check if this floor is already on the list
                if (!elevatorBankCallList.contains(idx)) {
                    // starting position for the reference list
                    this.visitList.add(idx);                // for this elevator
                    this.elevatorBankCallList.add(idx);     // for the ElevatorBank 
                    this.tempEBCallListIdx = elevatorBankCallList.size() - 1;
                    this.tempEBindexListSize = elevatorBankCallList.size(); //for the EB
                    state = MOVING;         //chenge elevator to moving
                    ElevatorBank.GetInstance().setFloorDispatchList(elevatorBankCallList); //update ElevatorBank
                    System.out.println("DEBUG: elevatorCallRequested():****\t\tHurdle 1c: added Floor: " + visitList.get(idx));
                    System.out.println("DEBUG: elevatorCallRequested():****\t\tELEVATORBANk dispatchList Size = " + ElevatorBank.GetInstance().getFloorDispatchList().size());
                }
            }
        } // for

    } // elevatorCallRequested()

    public void setMoveDirection() {
        System.out.println("DEBUG: setMoveDirection(): Hurdle 2 requestedFlloor = CurrentFloor: " + currentFloor);
        if (visitList.get(1) > currentFloor) {
            direction = DIRECTIONUP;
            System.out.println("DEBUG: setMoveDirection(): Hurdle 2 Set the Direction = UP! CurrentFloor: " + currentFloor);
        } else if (visitList.get(1) < currentFloor) {
            direction = DIRECTIONDOWN;
            System.out.println("DEBUG: setMoveDirection(): Hurdle 2 Set the Direction = UP! CurrentFloor: " + currentFloor);
        } else if (visitList.get(1) == currentFloor) {
            direction = STOP;
            System.out.println("DEBUG: setMoveDirection(): Hurdle 2 requestedFlloor = CurrentFloor: " + currentFloor);
        }
    }// setMoveDirection()

    public void move() {
        System.out.println("DEBUG: move(): Hurdle 3 on the move() to floor:");
        if (currentFloor == visitList.get(1)) {
            this.state = ARRIVED;
            this.direction = STOP;
            System.out.println("DEBUG: move(): Hurdle 3 ARRIVED to " + currentFloor + " and stopped! state = Arrived");
        }
        // Where should the Elevator move?
        int newFloor = this.currentFloor + direction;
        if (newFloor >= maxFloor) {
            this.currentFloor = maxFloor - 1;
            this.direction = DIRECTIONDOWN;
            System.out.println("DEBUG: move(): DIRECTIONDOWN down; this.floorInt = maxFloor - 1; and currentFloor = " + currentFloor);
        } else if (newFloor <= minFloor) {
            this.currentFloor = minFloor;
            this.direction = DIRECTIONUP;
            System.out.println("DEBUG: move(): DIRECTIONUP down; this.floorInt = minFloor-1 and currentFloor = " + currentFloor);
        } else {
            this.currentFloor = newFloor; // else it moved up once
            //this.direction = STOP;
            //this.state = ARRIVED;
            // update the floor to shut off it's floor light
            System.out.println("DEBUG: move(): DIRECTIONUP = UP; Hurdle 3 moved to!" + currentFloor + " , state = "+this.state);
        }
    }//move()

    public void arrived() {
        System.out.println("DEBUG: arrived():****\t Elevator " + label + " arrived():******\t on Current floor = [ " + currentFloor +" ] and oppened doors and EBindex = " + tempEBindexListSize);
        // open the elevator doors
        elevatorOpenDoor(); 
        // turn off the floor call light
        ElevatorBank.GetInstance().getFloor(currentFloor).setCallElevator(Boolean.FALSE);
        //ElevatorBank.GetInstance().updateFloorDispatchList(tempEBindexListSize-1); // remove this floor from the list
////        //this.tempEBCallList.clear();    // update with freshList
////        System.out.println("DEBUG: arrived():****\t\tELEVATORBANk dispatchList Size 0 = " +tempEBCallList.size());
////        tempEBCallList = ElevatorBank.GetInstance().getFloorDispatchList();
////        System.out.println("DEBUG: arrived():****\t\tELEVATORBANk dispatchList tempEBCallListIdx = " +tempEBCallListIdx);
////        //tempEBCallListIdx;
////        tempEBCallList.remove( tempEBCallListIdx);
////        System.out.println("DEBUG: arrived():****\t\tELEVATORBANk dispatchList Size = " +ElevatorBank.GetInstance().getFloorDispatchList().size());
////        
////        ElevatorBank.GetInstance().setFloorDispatchList(tempEBCallList); //update ElevatorBank
////        System.out.println("DEBUG: arrived():****\t\tELEVATORBANk dispatchList Size = " +ElevatorBank.GetInstance().getFloorDispatchList().size());
////     
        System.out.println("DEBUG: arrived():****\t Elevator " + label + " arrived():******\t on Current floor = [ " + currentFloor +" ] \t*** END of Arrived()");
        
    } // arrived()

    @Override
    public void accept(IVisitor boardingParty) {
        // tell first Elevator to accept this visitor
        riders.add(boardingParty);
        this.numberOfVisitors = riders.size();
        System.out.println("DEBUG: Elevator: " + label + " accept(): riders: " + boardingParty.toString());

    }

    @Override
    public void release(IVisitor visitor) {
        //visitor.setFloorInt(currentFloor);
        visitor.setCurrentFloor(currentFloor);
        riders.remove(visitor);
        this.numberOfVisitors = riders.size();
    }

    public void elevatorOpenDoor() {
        this.doorsAreOpen = true;     // this elevator opens door
        System.out.println("DEBUG: ElevatorDoors are open!\n");
    }

    public void elevatorCloseDoor() {
        this.doorsAreOpen = false;     // this elevator opens door
        System.out.println("\nDEBUG: ElevatorDoors are CLOSED!\n");
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
        return numberOfVisitors = riders.size();
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

    public void setDoorsAreOpen(Boolean doorsAreOpen) {
        this.doorsAreOpen = doorsAreOpen;
    }

    public void setState(int state) {
        this.state = state;
    }

}//class Elevator

