package pckElevator_V1;
import java.util.ArrayList;
import java.util.Iterator;


public interface IElement {

    void accept( IVisitor visitor );
    void release( IVisitor visitor );
    

    public int getNumberOfRiders(); // for WindowMainApp

}// interface IElement
