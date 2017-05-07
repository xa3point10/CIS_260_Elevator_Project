package pckElevator_V1;

import java.util.ArrayList;
import java.util.Random;

public class VisitorShopper  implements /*extends*/ IVisitor {
    Random rand = new Random();
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    private static final int WAITING = 4;
    
    // internal floor index
    private int floor = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String myFloorLabel;   // from ElevatorBank updateConfiguration()
    private int maxFloor;
    private int currentFloor;
    private ArrayList<Integer> visitorAgenda;
    

    // Constructor
    public VisitorShopper() {
        visitorAgenda = new ArrayList<>();
    }
    
    @Override
    public void configVisitorRoutine(){
        // When This is createed, 
        // They knows how many floors are in the building
        System.out.println("DEBUG: VsitorShoper MaxFloor = " + maxFloor);
        //maxFloor = ElevatorBank.GetInstance().getMaxFloor();
       //this.maxFloor = maxFloorValue;
        // They know the order they want to visit the floors
        visitorAgenda.add(0);       // They begin in Parking Garage 
        visitorAgenda.add(1);       //and then they want to visit here
        visitorAgenda.add(randFloorPicker(maxFloor)); //and visit here
        visitorAgenda.add(randFloorPicker(maxFloor)); //and visit here
        // begin in visiting state in Parking garage floor 0
        state = VISITING;
        currentFloor = 0;  // They are in the parking garage
    }                 
    // randomize the order of floor visit and add it to OrderOfVisits Array
    public int randFloorPicker(int maxFloor){
        int floorFloorToVisit =  rand.nextInt(maxFloor);
        return floorFloorToVisit;
    }

    // Operations
    public void unboard (Floor floor){          // arrived at floor
        
    }
    public void arrived (Elevator elevator){    // boarding elevator
        
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
    
    @Override
    public void visit( Floor floor ) {
        System.out.println(
                "Visitor:"
                + this.toString()
                + " visiting Floor:"+floor.hashCode()
                );
    }//visit
    
    @Override
    public void visit( Elevator elevator ) {
        System.out.println(
                "Visitor:"
                + this.toString()
                + " visiting Elevator:"
                + elevator.hashCode()
                );
    }//visit

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public String getMyFloorLabel() {
        return myFloorLabel;
    }
    public void setMyFloorLabel(String myFloorLabel) {
        this.myFloorLabel = myFloorLabel;
    }    
}// class VisitorShopper
