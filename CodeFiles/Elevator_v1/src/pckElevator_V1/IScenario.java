package pckElevator_V1;

import java.util.ArrayList;

public interface IScenario {
    // Scenario can take arraylist of visitos and 
    // the Visitable glocations
    public ArrayList< IElement >  getVisitables();
    public ArrayList< IVisitor >  getVisitors();

    //******** From Oroginal MVC DEMO ******
    public int  getNumberOfVisitors();
    public void setNumberOfVisitors(int numberOfVisitors);
    
    public int getNumberOfFloors();
    public void setNumberOfFloors( int numberOfFloors );
    
    public int getNumberOfElevators();
    public void setNumberOfElevators( int numberOfElevators );
    
}// interface IScenario
