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
    // needs an array list of visitors
    //private ArrayList< IVisitor> riders = new ArrayList<IVisitor>();
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
    public  void callElevatorButton( Floor floor ){}

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    // ***** recomended add from class conversation
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
            //System.out.println( element );
//        }
        }
    }

    public void arrived(int floor) {
        // needs to be finished
    }

    //@Override
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
        System.out.println("DEBUG: Elevator getLabel: " + label );
        return label;
    }

    public int getFloor() {
        System.out.println("DEBUG: Elevator getFloor: " + floor );
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
