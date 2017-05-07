package pckElevator_V1;

import java.util.ArrayList;

public class ScenarioCustom implements IScenario{
    private ArrayList< IElement> visitables;    // Added
    private ArrayList< IVisitor> visitors;      // Added
    
    private int numberOfFloors = 0;
    private int numberOfElevators = 0;
    private int numberOfVisitors = 0;
    // Construct the visitor types!
    private VisitorShopper shopper = new VisitorShopper(); 
  
    //****************************
    // Constructor
    //****************************
    public ScenarioCustom() {
        this.visitables = new ArrayList<>(); 
        this.visitors = new ArrayList<>();
    }
    
    //****************************
    // operations
    //****************************
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
            visitors.add(shopper);              // put in array
            this.shopper.configVisitorRoutine();// tell to config itself
        }
    }
    
    @Override
    public ArrayList< IElement > getVisitables() {  
        return visitables;
    }
    
    @Override
    public int  getNumberOfVisitors(){ return numberOfVisitors;}
    
    @Override
    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
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
        shopper.setMaxFloor(numberOfFloors);
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
