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
    //private VisitorShopper worker = new VisitorWorker();  // doesnt exist yet
    
    //****************************
    // Constructor
    //****************************
    public ScenarioCustom() {
        this.visitables = new ArrayList<>(); 
        this.visitors = new ArrayList<>();
    }

    //****************************
    // operations
    //**************************
   @Override
    public void setAllIElements(){
        for (int idxE = 0; idxE <= this.numberOfElevators-1; ++ idxE){
        this.visitables.add(ElevatorBank.GetInstance().getElevator(idxE));
        }
        for (int idxF = 0; idxF <= this.numberOfFloors-1; ++ idxF){
        this.visitables.add(ElevatorBank.GetInstance().getFloor(idxF));
        }
    System.out.println("DEBUG: ScenarioCustom: setAllElements: Visitables size " 
            + visitables.size() );
    }
    
    @Override
    public void populateVisitorsArray(){
        for (int idx = 0; idx <= numberOfVisitors -1; ++idx){
            visitors.add(shopper);              // put 1 visitor in array
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
        shopper.setMaxFloor(numberOfFloors);    // tell shoper how many floors
        //worker.setMaxFloor(numberOfFloors);     // tell worker how many floors
        Elevator.maxFloor = numberOfFloors;     // tell Elevator how many floors
        this.numberOfFloors = numberOfFloors;   // tell this Scenario how many floors
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
