package pckElevator_V1;

import java.util.ArrayList;

public class ScenarioOne implements IScenario {
    private ArrayList< IElement> visitables;    // Added
    private ArrayList< IVisitor> visitors;      // Added
    
    private int numberOfFloors ;
    private int numberOfElevators;
    private int numberOfVisitors;
    private VisitorShopper shopper;         // on? include in populateVisitorsArray()
    private VisitorTopDown topDownVisitor;  // on? include in populateVisitorsArray()
    
    //****************************
    // Constructor
    //****************************
    public ScenarioOne() {
        this.visitables = new ArrayList<>(); 
        this.visitors = new ArrayList<>();
        this.numberOfFloors = 10;        // hardset numberOfFloors!
        this.numberOfElevators = 1;     // hardset numberOfElevators!
        this.numberOfVisitors = 1;      // hardset numberOfVisitors!
    }

    //****************************
    // operations
    //**************************
    @Override 
    public void wakeUpScenario() {
        for (int idx = 0; idx <= numberOfVisitors-1; ++idx){
            this.visitors.get(idx).wakeUpVisitor();
        }
    }
    
    @Override
    public void setAllIElements() {
        // add all elevators into the array
        ArrayList<Elevator> elevators = ElevatorBank.GetInstance().getElevators();
        for (Elevator elevator : elevators) {
            visitables.add(elevator);
            System.out.println("DEBUG: Scenario: SetAllElements(): Visitables size = "+visitables.size());
        }
        // add all floors into the array
        ArrayList<Floor> floors = ElevatorBank.GetInstance().getFloors();
        for (Floor floor : floors) {
            visitables.add(floor);
            System.out.println("DEBUG: Scenario: SetAllElements(): Visitables size = "+visitables.size());
        }
        
    }// setAllIElements()
    
    @Override
    public void populateVisitorsArray(){
        setAllIElements();
        for (int idx = 0; idx < numberOfVisitors; ++idx){
            this.topDownVisitor = new VisitorTopDown();     // create unique visitors
            this.topDownVisitor.setMaxFloor(numberOfFloors);
            this.topDownVisitor.configVisitorRoutine();// tell to config itself
            this.visitors.add(topDownVisitor);         // add to thisVisitors Array
            this.topDownVisitor.setBldElements(this.visitables);   //pass bldElements
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
        return this.numberOfFloors;
    }//getNumberOfFloors
    
    @Override
    public void setNumberOfFloors( int numberOfFloors ){
        Elevator.maxFloor = numberOfFloors;     // tell Elevator how many floors
        this.numberOfFloors = numberOfFloors;   // tell this Scenario how many floors
    }//setNumberOfFloors
    
    @Override
    public int getNumberOfElevators() {
        return this.numberOfElevators;
    }//getNumberOfElevators

    @Override
    public void setNumberOfElevators( int numberOfElevators ) {
        this.numberOfElevators = numberOfElevators;
    }//setNumberOfElevators
        
}// class ScenarioOne 
