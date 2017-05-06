package pckElevator_V1;
import java.util.ArrayList;
import java.util.Iterator;


public interface IElement {
    // Elements can accapt & release Visitors
    void accept( IVisitor visitor );
    void release( IVisitor visitor );
    
    //***************
    // To: do
    // Create the callElevatorButton()
    void callElevatorButton( Floor floor );

    
    //------------------------------
    // NEW operations! from Merge File
    //--------------b----------------
    //public String getLabel();
    //public void move();
    //public int getFloor();

    
}// interface IElement
