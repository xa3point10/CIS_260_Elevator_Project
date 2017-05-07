package pckElevator_V1;
import java.util.ArrayList;
import java.util.Iterator;


public interface IElement {
    // Elements can accapt & release Visitors
    void accept( IVisitor visitor );
    //public void accept( IVisitor visitor, int DesiredFloor );
    void release( IVisitor visitor );
    
    //public  void elevatorCallButton( IElement floor, IVisitor visitor );
    public int getNumberOfVisitors();
    
    //------------------------------
    // NEW operations! from Merge File
    //--------------b----------------
    //public String getLabel();
    //public void move();
    //public int getFloor();

    
}// interface IElement
