package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;

public class Floor implements IElement {

    private String label;   // the label This current floor

    private ArrayList< IVisitor> floorVisitors;
    private ArrayList< IVisitor> tempBoardingParty;
    private int numberOfVisitors = 0;
    private int thisFloorsNumber = 0;
    private Boolean boolCallElevator = false;
    //private IVisitor tempBoardingIndividual;
    //private IVisitor boardingIndividual;
    
    // ********************************
    // Constructor 
    // ********************************
    public Floor ( String label ) {
        System.out.println("DEBUG: Floor: Constructor: this floor's Label: " + label);
        this.floorVisitors = new ArrayList<>();       //added
        this.tempBoardingParty = new ArrayList<>(); 
        this.label = label;   //from original CODE
    }
    
    // ********************************
    // Operations 
    public Boolean FloorCallButton(IVisitor visitor ){
        // If the elevator button is not already called
        if (boolCallElevator == false){
            System.out.println("DEBUG: FloorCallButton on floor "+thisFloorsNumber+": callElevator =" + boolCallElevator);
            // Check each visitors
            for (int idx = 0; idx <= floorVisitors.size()-1; ++idx) {
                // if this vibsitor's desired floor is not this floor
                if (floorVisitors.get(idx).getNextDesiredFloorIndex() != this.thisFloorsNumber )
                {
                    // call the elevator and tell the elevator to move to thisFloorsNumber
                    ElevatorBank.GetInstance().getElevator(0).setRequestedFloor(thisFloorsNumber);
                    this.boolCallElevator = true;     // Turn on the Call Button
                    System.out.println("DEBUG: FloorCallButton "+thisFloorsNumber+": callElevator =" + boolCallElevator);
                }//if
            }// for
        }//if 
        return boolCallElevator;
    } // FloorCallButton()

    @Override
    public void accept( IVisitor visitor) {
        // tell first Elevator to accept this visitor
        //visitor.setFloorInt(thisFloorsNumber);
        visitor.setCurrentFloor(thisFloorsNumber);
        
        this.floorVisitors.add(visitor);
        System.out.println("\nDEBUG visitors= " + floorVisitors );
        this.numberOfVisitors = floorVisitors.size();
        System.out.println("DEBUG: Floor: F" + label + " accept(): has numberOfVisitors: " + numberOfVisitors );
    }
    public void elevatorIsHere(int elevatorNum){
        // change each visitors state to riding!
        int RIDING = 2;
        for (Iterator<IVisitor> visitors = floorVisitors.iterator(); visitors.hasNext();) {
            IVisitor person = visitors.next();
            person.setState(RIDING);
        }
        //Elevator  elevator = ElevatorBank.GetInstance().getElevator(elevatorNum);
        //elevator.accept(this.floorVisitors);
        
        this.floorVisitors.clear();
//        for(int idx = 0; idx <= 1/*floorVisitors.size()*/ -1; ++idx){
//            System.out.println("Boarding passss = " +(idx+1));
//            release(floorVisitors.get(idx));
//        }    
        // Turn off the floor light
        boolCallElevator = false;
    } // elevatorIsHere()
    
    public IVisitor boardThisVisitor(IVisitor visitor){
        return visitor;
    }  
    @Override
    public void release(IVisitor visitor) {
        System.out.println("DEBUG: Floor: "+this.getLabel()+" releasing visitor: "+visitor.toString());
        // remembers how to 
        Iterator<IVisitor> it = floorVisitors.iterator();
        // could be done in a while loop it.hasNext()
        for (; it.hasNext();) {
            IVisitor rider = it.next();
            // compare both
            if (rider == visitor) {
                // iterator allows stable loop while removing the content
                //boardThisVisitor(visitor);
                it.remove();
                continue;
            }
//        }
        }
    }
    public void updateNumberOfFloorVisitors(){
        floorVisitors.clear();
        numberOfVisitors = floorVisitors.size();
    }
    @Override
    public int getNumberOfVisitors() {
        return numberOfVisitors = floorVisitors.size() ;
    }
    //*************FROM ORIGINAl MVC DEMO ***************
    //@Override
    public String getLabel() {  
        return label;
    }
    
    public void setThisFloorsNumber(int thisFloorsNumber) {
        this.thisFloorsNumber = thisFloorsNumber;
    }

    public int getThisFloorsNumber() {
        return thisFloorsNumber;
    }

    public Boolean getBoolCallElevator() {
        return boolCallElevator;
    }

    public void setCallElevator(Boolean callElevator) {
        this.boolCallElevator = callElevator;
        System.out.println("DEBUG: FLoor: setCallElevator(): was called and set to "+ boolCallElevator.toString());
    }

}// class Floor

