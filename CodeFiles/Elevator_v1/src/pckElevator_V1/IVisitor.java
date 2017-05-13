package pckElevator_V1;

import java.util.ArrayList;

public interface IVisitor {
    
    public void wakeUpVisitor();
    public void boardElevator (Elevator elevator, Floor floor);
    

    public void setFloorInt(int floorNumber);
    public void setCurrentFloor(int floorNumber);
    public void setState(int state);
    public void configVisitorRoutine();
    public int getNextDesiredFloorIndex();
    public ArrayList<Integer> getVisitorAgenda();

    
}// interface IVisitor
