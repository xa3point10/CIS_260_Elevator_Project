package pckElevator_V1;

public class Elevator {
    //DEBUG
    public static final int maxFloor = 6;  //should live In floor class
    public static final int minFloor = 1;  //should live In floor class
    
    private static final int directionUp = +1;  // -1: down,  +1: up 
    private static final int directionDown = -1;  // -1: down,  +1: up 
    // +1 Floor
    private int direction = directionUp;
    
    //data
    private int floor = 1 ;
    private int state = 0;  //up, down, standing, visitors
    
    //constructors
    public Elevator (int floor){
        this.floor = floor;
    }
    
    void move(){
        floor = floor + direction;
        if ( floor > maxFloor){
            floor = maxFloor;
            direction = directionDown;
        } else if ( floor < minFloor ) {
            floor = minFloor;
            direction = directionUp;
        }
    }
    void moveDown(){
        floor --;
    }
    
    public int getFloor() {
        return floor;
    }
    
    
}//class Elevator
