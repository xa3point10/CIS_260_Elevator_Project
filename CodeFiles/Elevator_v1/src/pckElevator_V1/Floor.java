package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;

public class Floor implements IElement {

    private String label;   // the label This current floor

    private ArrayList< IVisitor> floorVisitors;
    private int numberOfVisitors = 0;
    private int thisFloorsNumber = 0;
    private Boolean boolCallElevator = false;
    
    // ********************************
    // Constructor 
    // ********************************
    public Floor ( String label ) {
        System.out.println("DEBUG: Floor: Constructor: this floor's Label: " + label);
        this.floorVisitors = new ArrayList<>();       //added
        this.label = label;   //from original CODE    
    }
    
    // ********************************
    // Operations 
    public Boolean FloorCallButton(IVisitor visitor ){
        // If the elevator button is not already called
        if (boolCallElevator == false){
            System.out.println("DEBUG: FloorCallButton "+thisFloorsNumber+": callElevator =" + boolCallElevator);
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
        this.floorVisitors.add(visitor);
        this.numberOfVisitors = floorVisitors.size();
        FloorCallButton(visitor);
        System.out.println("DEBUG: Floor: F" + label + " accept(): has numberOfVisitors: " + numberOfVisitors );
        //visitor.
    }
    
    public IVisitor elevatorIsHere(){
        // release the first visitor in the array
        IVisitor tempBoardingParty = floorVisitors.get(0);
        release(floorVisitors.get(0));
        return tempBoardingParty;
    }
    
    // ***** recomended add from class conversation    
    @Override
    public void release(IVisitor visitor) {
        // remembers how to 
        Iterator<IVisitor> it = floorVisitors.iterator();
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
    @Override
    public int getNumberOfVisitors() {
        return numberOfVisitors;
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
    }

}// class Floor

