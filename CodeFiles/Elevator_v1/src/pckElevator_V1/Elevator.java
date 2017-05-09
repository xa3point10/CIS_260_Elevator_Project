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
    private int direction = 0;

    // internal floor index
    private int STATE;
    private int floor; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String label;   // from ElevatorBank updateConfiguration()


    private ArrayList< IVisitor > riders;
    private ArrayList< IElement > bldElements;
    private ArrayList< Integer > visitList;
    private int numberOfVisitors = 0;
    private int requestedFloor;
    private int elevatorsNumber;
    
    //------------------------------
    // constructors
    //------------------------------
    public Elevator(String label, int floor, int elevatorNumber) {
        this.riders = new ArrayList<>();
        this.visitList = new ArrayList<>();
        this.bldElements = new ArrayList<>();
        this.visitList.add(-1);
        this.label = label;
        this.numberOfVisitors = riders.size();
        this.elevatorsNumber = elevatorNumber;
        this.floor = floor;
    }

    //------------------------------
    // operations
    //------------------------------
    @Override
    public void accept( IVisitor boardingParty) {
        // tell first Elevator to accept this visitor
        this.riders.add(boardingParty);
        this.numberOfVisitors = riders.size();
        System.out.println("DEBUG: Elevator: " + label + " accept(): riders: " + numberOfVisitors );
    }

    @Override
    public void release(IVisitor visitor) {
        // to do: create a check for elevator state
        assert (state == state) :
                "Bad attempt to release visitor from the elevator";
        // it is an iterator. 
        // remembers how to 
        Iterator<IVisitor> it = riders.iterator();
        // could be done in a while loop it.hasNext()
        for (; it.hasNext();) {
            IVisitor rider = it.next();
            // compare both
            if (rider == visitor) {
                //System.out.print( "DEBUG: removing " );
                //System.out.println( element );
                // iterator allows stable loop while removing the content
                it.remove();
                continue;
            }
        }
    }

    public void elevatorWakeUp() {
        // Each cycle through the tread checks for a call
        elevatorCallRequested();  // this calls the move if asked
    }
    
    public void elevatorCallRequested() {
        // Check each floor
        for (int idx = 0; idx <= maxFloor - 1; ++idx) {
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1:");
            // if there is a floor that is requesting an elevator
            int testFloor = ElevatorBank.GetInstance().getFloors().get(idx).getThisFloorsNumber();
            Boolean value = ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator();
            System.out.println("DEBUG: elevatorCallRequested(): 1: not added yet floor: " + testFloor+" is "+value);
            if (ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator()) {
            System.out.println("DEBUG: elevatorCallRequested(): 1: checking floor " + testFloor);    
                // check if this floor is already on the list
                if (!visitList.contains(idx)) {
                    visitList.add(idx);
                    System.out.println("DEBUG: elevatorCallRequested(): added Floor: " + visitList.get(idx));
                    System.out.println("DEBUG: elevatorCallRequested(): added RequestedFloor " + visitList.get(testFloor));
                }
            }
        } // for
        System.out.println("\n\nDEBUG: elevatorCallRequested(): Hurdle 2 requestedFlloor = "+visitList.get(1));
        if (!visitList.get(1).equals(null)) {
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 2 requestedFlloor: CurrentFloor: "+ this.floor);
            int newFloor = this.floor + direction;
            // set the direction of the move()
            if (newFloor > this.visitList.get(1)) {
                System.out.println("DEBUG: elevatorCallRequested(): 2a: " + state);
                this.direction = DIRECTIONDOWN;
                this.state = MOVING;
            } else if (newFloor < this.visitList.get(1)) {
                System.out.println("DEBUG: elevatorCallRequested(): 2b : state = "+state);
                this.direction = DIRECTIONUP;
                this.state = MOVING;
            } else if (this.floor == this.visitList.get(1)) {
                this.state = STOP;
                this.state = ARRIVED;
                System.out.println("DEBUG: elevatorCallRequested(): 2c : state = "+state);
                //arrived();
                System.out.println("DEBUG: elevatorCallRequested(): state should be stop!!!!!!!!!!!!! state= " + state);
            }
        }
        System.out.println("DEBUG: elevatorCallRequested(): Hurdle 3 on the move()\n");
        move();
    } // elevatorCallRequested()

    public void move() {
        // Where should the Elevator move?
        int newFloor = this.floor + direction;  //constantly up +1
        if (newFloor >= maxFloor) {
            this.floor = maxFloor - 1;
            this.direction = DIRECTIONDOWN;

        } else if (newFloor <= minFloor) {
            this.floor = minFloor;
            this.direction = DIRECTIONUP;
        } else {
            this.floor = newFloor;
            //arrived();
        }
    }//move
    
    public void arrived() {
        Floor floor = ElevatorBank.GetInstance().getFloor(this.floor);
        IVisitor tempBoardingParty = floor.elevatorIsHere();
        accept(tempBoardingParty);
    }

    public String getLabel() {
        System.out.println("DEBUG: Elevator: getLabel: " + label);
        return label;
    }

    public int getFloor() {
        System.out.println("DEBUG: Elevator: getFloor: " + floor);
        return floor;
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

}//class Elevator

