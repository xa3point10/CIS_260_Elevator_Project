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
//        // pass in the visitable locations 
//        visitables.add(e1); //creates Elevator 1 
//        visitables.add(e2); //creates Elevator 2 
//        visitables.add(b1); //creates Floor 1 
//        visitables.add(f1); //creates Floor 1 
//        visitables.add(f2); //creates Floor 2 
//        visitables.add(f3); //creates Floor 3 
//
//        // pass in the visitors to Arraylist 
//        visitors.add(employee);
//        visitors.add(guest);
//        visitors.add(Ixe);
//        getVisitables();
//        getVisitors();
//    ElevatorBank.GetInstance().updateConfiguration(
//               getNumberOfFloors(), getNumberOfElevators(), getNumberOfVisitors()
//        );
    }
    
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
    
}// class ScenarioOne
