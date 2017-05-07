package pckElevator_V1;

import java.util.ArrayList;

public class ScenarioOne implements IScenario {
    // scenario Data // Maybe this shouldnt be in here
    private ArrayList< IElement> visitables; // Added from Visitors DEmo
    private ArrayList< IVisitor> visitors;   // Added from Visitors DEmo
    private VisitorShopper shopper = new VisitorShopper();
    private int numberOfFloors = 0;     
    private int numberOfElevators = 0;  
    private int numberOfVisitors = 0;
    

//    private Elevator e1 = new Elevator();
//    private Elevator e2 = new Elevator();
//    private Floor b1 = new Floor();
//    private Floor f1 = new Floor();
//    private Floor f2 = new Floor();
//    private Floor f3 = new Floor();

    // Construct the visitor types 
//    private VisitorEmployee employee = new VisitorEmployee();
//    private VisitorGuest guest = new VisitorGuest();
//    private Visitor_Ixe Ixe = new Visitor_Ixe();

    // Constroctor 
    public ScenarioOne() {
        this.visitors = new ArrayList<>();
        this.visitables = new ArrayList<>();
    }
    
    @Override
    public void setAllIElements(){
        for (int idxE = 0; idxE <= this.numberOfElevators; ++ idxE){
        visitables.add(ElevatorBank.GetInstance().getElevator(idxE));
        }
        for (int idxF = 0; idxF <= this.numberOfFloors; ++ idxF){
        visitables.add(ElevatorBank.GetInstance().getFloor(idxF));
        }
    System.out.println("DEBUG: ScenarioCustom: setAllElements: Visitables size " 
            + visitables.size() );
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
    
}// class ScenarioOne
