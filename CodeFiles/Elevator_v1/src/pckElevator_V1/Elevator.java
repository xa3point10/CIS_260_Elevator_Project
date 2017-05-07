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

    //  -1..Down +1..Up
    private int direction = DIRECTIONUP;

    // internal floor index
    private int floor = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String label;   // from ElevatorBank updateConfiguration()

    //***************
    // To: do
    // Create the callElevatorButton()
    private ArrayList< IVisitor> riders;
    private int numberOfVisitors = 0;
    
    
    //------------------------------
    // constructors
    //------------------------------
    public Elevator(String label, int floor, int passengers) {
        this.riders = new ArrayList<IVisitor>();
        this.label = label;
        this.numberOfVisitors = riders.size();
    }//Elevator

    //------------------------------
    // operations
    //------------------------------
    
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
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

    public void elevatorWakeUp(){
        // Needs to run a check
    }
    
    public  void elevatorCallButton( IElement floor, IVisitor visitor ){
        // How do we decide a visitor presses the call Button?
        //if (visitor == 3 )
        // Visitor looks at their array
        // Visitor visits that floor for a call cycle
        // Next cycle visitor pushes the call button. 
        // elevator makes it way to called floor
        // Then there is a hand off from floor to Elevator
        // the elator gets a +1 in their array
        // Elevator gets destination floor and proceeds to floor
    }
    

    public void arrived(int floor) {
        // Continued from callButton
        // check that destination floor = getFloor()
        // once equal, then handoff release() to floor
        // floor runs the accept() program
    }

    public void move() {
        int newFloor = this.floor + direction;  //constantly up +1
        if (newFloor >= maxFloor) {
            this.floor = maxFloor - 1;
            this.direction = DIRECTIONDOWN;

        } else if (newFloor <= minFloor) {
            this.floor = minFloor;
            this.direction = DIRECTIONUP;

        } else {
            this.floor = newFloor;
        }
    }//move

    public String getLabel() {
        System.out.println("DEBUG: Elevator: getLabel: " + label );
        return label;
    }

    public int getFloor() {
        System.out.println("DEBUG: Elevator: getFloor: " + floor );
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
}//class Elevator
