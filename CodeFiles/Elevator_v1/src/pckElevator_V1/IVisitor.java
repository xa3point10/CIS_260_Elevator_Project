package pckElevator_V1;

import java.util.ArrayList;

public interface IVisitor {
    
    public void unboard (Floor floor);
    public void boardElevator (Elevator elevator, Floor floor);
    

    public void configVisitorRoutine();
    public int getNextDesiredFloorIndex();
    public ArrayList<Integer> getVisitorAgenda();

    
}// interface IVisitor
