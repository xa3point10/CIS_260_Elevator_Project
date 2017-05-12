package pckElevator_V1;

import java.util.ArrayList;

public interface IScenario {
    // Scenario can take arraylist of visitos and 
    // the Visitable glocations
    public ArrayList< IElement >  getVisitables();
    public ArrayList< IVisitor >  getVisitors();
    

    public void wakeUpScenario();
    public void setAllIElements();
    public void populateVisitorsArray();
    //******** From Oroginal MVC DEMO ******
    public int  getNumberOfVisitors();
    public void setNumberOfVisitors(int numberOfVisitors);
    //public int  getElevatorRiders();
    
    public int getNumberOfFloors();
    public void setNumberOfFloors( int numberOfFloors );
    
    public int getNumberOfElevators();
    public void setNumberOfElevators( int numberOfElevators );
    
}// interface IScenario
