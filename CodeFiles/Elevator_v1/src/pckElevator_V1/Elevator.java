package pckElevator_V1;

public class Elevator {
    // data
    //------------------------------
    public static int maxFloor = 0;
    public static int minFloor = 0;
    
    private static final int directionUp = +1;
    private static final int directionDown = -1;
    
    //  -1..Down +1..Up
    private int direction = directionUp;

    // internal floor index
    private int floor = 0; // in range 0, 1, 2, ..., maxFloor-1

    private int state = 0; // moving up, moving down, standing
    private String label;

    public String getLabel() {
        return label;
    }
    //------------------------------
    // constructors
    //------------------------------
    public Elevator( String label, int floor )
    {
        this.label = label;
        this.floor = floor;
    }//Elevator
    
    //------------------------------
    // operations
    //------------------------------
    public void move()
    {
        int newFloor = this.floor + direction;
        if ( newFloor >= maxFloor ) {
            this.floor = maxFloor - 1;
            this.direction = directionDown;

        } else if ( newFloor <= minFloor ) {
            this.floor = minFloor;
            this.direction = directionUp;

        } else {
            this.floor = newFloor;
        }
    }//move

    public int getFloor() {
        return floor;
    }//getFloor
    
    
    
}//class Elevator
