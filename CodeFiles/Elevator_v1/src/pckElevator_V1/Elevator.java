package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;
import javax.lang.model.element.Element;

public class Elevator implements IElement{
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
    @Override
    public void accept( IVisitor visitor ){
        visitor.visit( this );
    }
    // ***** recomended add from class conversation
    @Override
    public void release( IVisitor visitor ){
//        ArrayList< Element > elements = new ArrayList<>();
//
//        visitor.getClass().getTypeName();
//        
//        Element temp = new Element();
//        elements.add( temp );
//        elements.add( new IVisitor() );
//        elements.add( new IVisitor() );
//        
//        // for ( Element element : elements ) {...}
//        // it is an iterator. 
//        // remembers how to 
//        Iterator<Element> it = elements.iterator();
//          // could be done in a while loop it.hasNext()
//        for ( ; it.hasNext(); ) {
//            Element element = it.next();
//          // compare both
//            if ( element == temp ) {
//                System.out.print( "removing " );
//                System.out.println( element );
//                // iterator allows stable loop while removing the content
//                it.remove();
//                continue;
//            }
//            System.out.println( element );
//        }
    }
    
    
    public void arrived( int floor ){ 
        // needs to be finished
    }
    
    //@Override
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
    
    public String getLabel() { return label; }
    

    public int getFloor() { 
        System.out.println("DEBUG Class Elevator; Floor: " + floor);
        return floor; 
    }
    
    
    
}//class Elevator
