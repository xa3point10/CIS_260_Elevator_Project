package pckElevator_V1;

import java.util.ArrayList;

public class ScenarioCustom implements IScenario{
    private ArrayList< IElement> visitables;    // Added
    private ArrayList< IVisitor> visitors;      // Added
    
    private int numberOfFloors = 0;
    private int numberOfElevators = 0;
    private int numberOfVisitors = 0;
    
    // constructor
    public ScenarioCustom() {
        // no specific constructor instructions
    }
    
     // operations
    @Override
    public int  getNumberOfVisitors(){ return numberOfVisitors;}
    
    @Override
    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }
    
    @Override
    public ArrayList< IElement > getVisitables() {  
        return visitables;
    }
    
    @Override
    public ArrayList< IVisitor >  getVisitors(){ return visitors;}

    //********** FROM ORGINAL DEMO **************
    @Override
    public int getNumberOfFloors(){
        return numberOfFloors;
    }//getNumberOfFloors
    
    @Override
    public void setNumberOfFloors( int numberOfFloors ){
        Elevator.maxFloor = numberOfFloors;
        this.numberOfFloors = numberOfFloors;
    }//setNumberOfFloors
    
    @Override
    public int getNumberOfElevators() {
        return numberOfElevators;
    }//getNumberOfElevators

    @Override
    public void setNumberOfElevators( int numberOfElevators ) {
        this.numberOfElevators = numberOfElevators;
    }//setNumberOfElevators
        
}// class ScenarioCustom 