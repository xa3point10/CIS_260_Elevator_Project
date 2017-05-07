package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;

public class Floor implements IElement {

    private String label;   // the label This current floor

    private ArrayList< IVisitor> floorVisitor;
    private int numberOfVisitors = 0;
    
    // Constructor ********************
    public Floor ( String label ) {
        System.out.println("DEBUG: Floor: Constructor: this floor's Label: " + label);
        floorVisitor = new ArrayList<>();       //added
        this.label = label;   //from original CODE    
    }

    //Operations
    public  void FloorCallButton( IElement floor, IVisitor visitor ){
        // How do we decide a visitor presses the call Button?
        //if ()
        // Visitor looks at their array
        // Visitor visits that floor for a call cycle
        // Next cycle visitor pushes the call button. 
        // elevator makes it way to called floor
        // Then there is a hand off from floor to Elevator
        // the elator gets a +1 in their array
        // Elevator gets destination floor and proceeds to floor
    }
    
    @Override
    public void accept( IVisitor visitor) {
        // tell first Elevator to accept this visitor
        this.floorVisitor.add(visitor);
        this.numberOfVisitors = floorVisitor.size();
        System.out.println("DEBUG: Floor: " + label + " accept(): numberOfVisitors: " + numberOfVisitors );
    }
    
    // ***** recomended add from class conversation
    public void arrived( int floor ){ 
        // needs to be finished
    }
    
    @Override
    public void release(IVisitor visitor) {
        // remembers how to 
        Iterator<IVisitor> it = floorVisitor.iterator();
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
    
}// class Floor
