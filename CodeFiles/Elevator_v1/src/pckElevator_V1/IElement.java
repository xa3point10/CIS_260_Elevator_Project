package pckElevator_V1;
import java.util.ArrayList;
import java.util.Iterator;


public interface IElement {
    // Elements can accapt & release Visitors
    void accept( IVisitor visitor );
    void release( IVisitor visitor );
    
    //public  void elevatorCallButton( IElement floor, IVisitor visitor );
    public int getNumberOfVisitors();
//    public void setThisFloorsNumber(int thisFloorsNumber);
//    public void setThisElevatorsNumber(int ThisElevatorsNumber);
            
    //------------------------------
    // NEW operations! from Merge File
    //--------------b----------------
    //public String getLabel();
    //public void move();
    //public int getFloor();

}// interface IElement
