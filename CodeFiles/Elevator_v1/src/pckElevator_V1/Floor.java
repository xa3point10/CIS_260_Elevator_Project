package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;

public class Floor implements IElement {

    private String label;

    private ArrayList< IVisitor> floorVisitor;
    private int numberOfVisitors = 0;
    
    // Constructor ********************
    public Floor ( String label ) {
        floorVisitor = new ArrayList<IVisitor>();       //added
        this.numberOfVisitors = floorVisitor.size();    //added
        this.label = label;   //from original CODE    
    }
    //***************
    // To: do
    // Create the callElevatorButton()
    
    public  void callElevatorButton( Floor floor ){}
    
    @Override
    public void accept( IVisitor visitor ) {
        visitor.visit( this );
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
