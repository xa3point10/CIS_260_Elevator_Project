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
    private int myFloorIndex = 0;
    private ArrayList<Integer> visitorAgenda;
    private ArrayList<Floor> myFloorHistory;
    private int nextDesiredFloorIndex = 0;
    

    // Constructor
    public VisitorShopper() {
        visitorAgenda = new ArrayList<>();
    }
        
    @Override
    public void configVisitorRoutine(){
        // When This is createed, 
        // They know how many floors are in the building
        System.out.println("DEBUG: VsitorShoper MaxFloor check = " + maxFloor);
        // They know the order they want to visit the floors
        visitorAgenda.add(1);       // They begin on this floor #
        visitorAgenda.add(2);       //and then they want to visit here
        //visitorAgenda.add(randFloorPicker(maxFloor)); //and visit here
        visitorAgenda.add(0);       // End back in the garage
        // begin in visiting state 
        state = VISITING;
        // visitor gets passed to the first floor in the agenda
        currentFloor = visitorAgenda.get(myFloorIndex);  // begin in index 0
        ElevatorBank.GetInstance().getFloor(currentFloor).accept(this);
        System.out.println("DEBUG: Visitor: configVisRoutine CurrentFloor: " + currentFloor );
        // Set the next desited floor 
        if (nextDesiredFloorIndex <= visitorAgenda.size()){
            ++nextDesiredFloorIndex;
        }
    }         
                
    public void beginAgendaProtocol(){
        if (state == VISITING){
            
        }
    }
    
    // randomize the order of floor visit and add it to OrderOfVisits Array
    public int randFloorPicker(int maxFloor){
        int floorFloorToVisit =  rand.nextInt(maxFloor);
        return floorFloorToVisit;
    }

    // Operations
    @Override
    public void unboard (Floor floor){          // arrived at floor
        
    }
    @Override
    public void boardElevator (Elevator elevator, Floor floor){          // arrived at floor
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
/** // void Visit   
    @Override
    public void visit( Floor floor ) {
        //place this visitor inside the floor it belongs
        
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
*/
    
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
        return nextDesiredFloorIndex;
    }
    @Override
    public ArrayList<Integer> getVisitorAgenda() {
        return visitorAgenda;
    }
    
}// class VisitorShopper
