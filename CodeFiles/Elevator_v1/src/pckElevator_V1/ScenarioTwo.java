package pckElevator_V1;

import java.util.ArrayList;

public class ScenarioTwo implements IScenario{
    // scenario Data // Maybe this shouldnt be in here
    private ArrayList< IElement> visitables; // Added from Visitors DEmo
    private ArrayList< IVisitor> visitors;   // Added from Visitors DEmo
    private VisitorShopper shopper = new VisitorShopper();
    private int numberOfFloors = 0;     
    private int numberOfElevators = 0;  
    private int numberOfVisitors = 0;
    

    // Constroctor 
    public ScenarioTwo() {}
    

    // operations
    @Override
    public void setIElementElevator(Elevator elevator){
            visitables.add(elevator);
    }
    @Override
    public void setIElementFloor(Floor floor){
        for (int idx = 0; idx <= numberOfVisitors -1; ++idx){
            visitables.add(floor);
        }
    }
    
    @Override
    public void populateVisitorsArray(){
        for (int idx = 0; idx <= numberOfVisitors -1; ++idx){
            visitors.add(shopper);
        }      
    }
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
    public int getNumberOfFloors()
    {
        return numberOfFloors;
    }//getNumberOfFloors
    
    @Override
    public void setNumberOfFloors( int numberOfFloors )
    {
        Elevator.maxFloor = numberOfFloors;
        this.numberOfFloors = numberOfFloors;
    }//setNumberOfFloors
    
    @Override
    public int getNumberOfElevators()
    {
        return numberOfElevators;
    }//getNumberOfElevators

    @Override
    public void setNumberOfElevators( int numberOfElevators ) {
        this.numberOfElevators = numberOfElevators;
    }//setNumberOfElevators
    
}// class ScenarioTwo
    
