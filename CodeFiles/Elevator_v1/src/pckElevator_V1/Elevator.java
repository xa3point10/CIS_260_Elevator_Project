package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;

public class Elevator implements IElement {

    // data
    //------------------------------
    public static int maxFloor = 0;
    public static int minFloor = 0;

    private static final int DIRECTIONUP = +1;
    private static final int DIRECTIONDOWN = -1;
    private static final int STOP = 0;
    private static final int MOVING = 1;
    private static final int ARRIVED = 2;
    private static final int DEPARTING = 3;

    //  -1..Down +1..Up
    private int direction = STOP;

    // internal floor index
    private int currentFloor; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing
    private String label;   // from ElevatorBank updateConfiguration()

    private ArrayList< Boolean > ebDispatchList = new ArrayList<>();
    private ArrayList< IVisitor> riders;
    private ArrayList< IElement> bldElements;
    private ArrayList< Integer> visitList;
    private int numberOfRiders = 0;
    private int requestedFloor;         // passed in by each visitor
    private static int elevatorsNumber;
    private IVisitor tempBoardingIndidual;
    private Boolean doorsAreOpen = false;
    private Boolean callLightPickup = false;
    private int callLightFloor;
    private Boolean elevatorAvailable = true; // all elevators begin available
    
    //------------------------------
    // constructors
    //------------------------------
    public Elevator(String label, int floor, int elevatorNumber) {
        this.ebDispatchList = new ArrayList<>();
        this.riders = new ArrayList<>();
        this.visitList = new ArrayList<>();
        this.bldElements = new ArrayList<>();
        this.visitList.add(-1);
        this.label = label;
        this.numberOfRiders = riders.size();
        this.elevatorsNumber = elevatorNumber;
        this.currentFloor = floor;
        this.state = STOP;
        this.elevatorAvailable = true;
        System.out.println("this elevator's name = " +this.label+" : "+ this.toString());
    }

    //------------------------------
    // operations
    //------------------------------
    public void elevatorWakeUp() {
        // update the ElevatorBank list of existing floor visits
        ebDispatchList = ElevatorBank.GetInstance().getEbDispatchList();
        // Each cycle through the tread checks for a call
        System.out.println("Elevator: "+this.getLabel()+" -> validateRequest-> STATE = "+this.state+" floor = "+this.getFloor() + ", riders= " + numberOfRiders
                +", Confirm! [ElevatorBank] floorDispatchList = "+ebDispatchList.size()+" VisitList = "+visitList.size());
        validateRequest();
    }

    public void validateRequest() {
        // check this elevator. if it's available, allow to check calls

    switch (state) {
        case STOP:   // = 0   check state of this elevator        
            System.out.println("DEBUG: ELEVATOR switchstatement: STATE = [ STOP ] , visitList size = " + visitList.size() + ", callLightFloor = "+ callLightFloor);
            elevatorCloseDoor();
            // validate: if available check for requests
            if (checkElevatorAvailability() == true) elevatorCallRequested();  
            break;
        
        case MOVING: 
            System.out.println("DEBUG: ELEVATOR switchstatement: STATE = [ MOVING ] , visitList size = " + visitList.size() + ", callLightFloor = "+ callLightFloor);
            setMoveDirection();
            move();
            break;
            
        case ARRIVED:  // arriving for pickup
            System.out.println("DEBUG: ELEVATOR switchstatement: STATE = [ ARRIVED] , visitList size = " + visitList.size() + ", callLightFloor = "+ callLightFloor);
            arrived();
            break;
            
        case DEPARTING:
            this.requestedFloor = visitList.get(1); // next floor 
            elevatorCloseDoor();           // close the foors
            setMoveDirection();
            this.state = MOVING;
            break;
            
        }// switch

    }// validateRequest()

    public void elevatorCallRequested() {
        // update the elvatorBank Tempcall list  
        System.out.println("DEBUG: Elevator: elevatorCallRequested()this." + label + " inside ebDispatchList size= " + ebDispatchList.size());
        // Check each floor
        for (int idx = 0; idx < maxFloor; ++idx) {
            System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1:");
            // check each floor for a call light
            Boolean value = ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator();
            System.out.println("DEBUG: elevatorCabllRequested(): Hurdle 1a: not added yet floor: " + currentFloor + " calling?: " + value +", & ebDispatchList = "+ebDispatchList.get(idx));
            if (ElevatorBank.GetInstance().getFloors().get(idx).getBoolCallElevator()) {  // if TRUE
                System.out.println("DEBUG: elevatorCallRequested(): Hurdle 1b: checking floor " + idx );
                // check if this floor is already on the list
                if (ebDispatchList.get(idx).equals(Boolean.FALSE)) { // i
                    // starting position for the reference list
                    this.callLightPickup = true;            // this is CallLight Pickup
                    this.elevatorAvailable = false; 
                    this.callLightFloor = idx;    // Replaced if Elevator Visitlist.empty
                    this.visitList.add(idx);                // for this elevator
                    this.ebDispatchList.set(idx, true);     // for ElevatorBank 
                    state = MOVING;         //chenge elevator to moving
                    ElevatorBank.GetInstance().setEbDispatchList(ebDispatchList); //update ElevatorBank
                    System.out.println("DEBUG: elevatorCallRequested():****\t\tHurdle 1c: UPDATE visitList.Size() = " + visitList.size());
                    System.out.println("DEBUG: elevatorCallRequested():****\t\tELEVATORBANk dispatchList Size = " + ElevatorBank.GetInstance().getFloorDispatchList().size());
                }
            }
        } // for

    } // elevatorCallRequested()

    public void setMoveDirection() {
        System.out.println("DEBUG: setMoveDirection(): Hurdle 2  CurrentFloor: " + currentFloor);
        if (visitList.get(1) > currentFloor) {
            direction = DIRECTIONUP;
            System.out.println("DEBUG: setMoveDirection(): Hurdle 2 Set the Direction = UP! CurrentFloor: " + currentFloor);
        } else if (visitList.get(1) < currentFloor) {
            direction = DIRECTIONDOWN;
            System.out.println("DEBUG: setMoveDirection(): Hurdle 2 Set the Direction = down! CurrentFloor: " + currentFloor);
        } else if (visitList.get(1) == currentFloor) {
            direction = STOP;
            System.out.println("DEBUG: setMoveDirection(): Hurdle 2 Set the Direction = STOP  requestedFlloor = CurrentFloor-> " + currentFloor);
        }
    }// setMoveDirection()

    public void move() {
        System.out.println("DEBUG: move(): Hurdle 3 on the move() towards floor direction:" + visitList.get(1));
        if (currentFloor == visitList.get(1)) {
            this.state = ARRIVED;
            this.direction = STOP;
            System.out.println("DEBUG: move(): Hurdle 3 ARRIVED to " + currentFloor + " and stopped! state = Arrived");
        }
        // Where should the Elevator move?
        int newFloor = this.currentFloor + direction;
        if (newFloor >= maxFloor) {
            this.currentFloor = maxFloor - 1;
            this.direction = DIRECTIONDOWN;
            System.out.println("DEBUG: move(): DIRECTIONDOWN down; this.floorInt = maxFloor - 1; and currentFloor = " + currentFloor);
        } else if (newFloor <= minFloor) {
            this.currentFloor = minFloor;
            this.direction = DIRECTIONUP;
            System.out.println("DEBUG: move(): DIRECTIONUP down; this.floorInt = minFloor-1 and currentFloor = " + currentFloor);
        } else {
            this.currentFloor = newFloor; // else it moved up once 
            System.out.println("DEBUG: move(): DIRECTIONUP = UP; Hurdle 3 moved to!" + currentFloor + " , state = "+this.state);
        }
    }//move()

    public void arrived() {
        System.out.println("DEBUG: arrived():****\t Elevator " + label + " arrived():******\t on Current floor = [ " + currentFloor +" ] ");
        // if this dropoff was from a Visitor inside the Elevator, update
        if (callLightPickup == false){
            System.out.println("DEBUG: arrived(): (callLightPickup == FALSE) BEGINING");
            visitList.remove(1);
            checkElevatorAvailability();
            state = STOP;
        }
        // if this dropoff was from a Floor light, update
        else if (callLightPickup == true){
            System.out.println("DEBUG: arrived(): (callLightPickup == true) BEGINING");
            //int floor = this.getFloor();
            ebDispatchList.set(currentFloor, false); //update ElevatorBank DispatchList
            visitList.remove(1); // in this instance, always element 1
            ElevatorBank.GetInstance().setEbDispatchList(ebDispatchList);
            callLightPickup = false; // Floor light pickup Complete
            checkElevatorAvailability(); 
            System.out.println("DEBUG: arrived(): (callLightPickup == true) END");
            state = STOP;
        }  
        // turn off the floor call light
        ElevatorBank.GetInstance().getFloor(currentFloor).setCallElevator(Boolean.FALSE);
        // open the elevator doors
        elevatorOpenDoor(); 
        System.out.println("DEBUG: arrived():****\t Elevator " + label + " arrived():******\t on Current floor = [ " + currentFloor +" ] \t*** END of Arrived()");   
    } // arrived()

    @Override
    public void accept(IVisitor boardingParty) {
        // tell first Elevator to accept this visitor
        riders.add(boardingParty);
        this.numberOfRiders = riders.size();
        System.out.println("DEBUG: Elevator: " + label + " accept(): riders: " + boardingParty.toString());

    }
    
    public void setRequestedFloor(int requestedFloor) {
        if (!visitList.contains(requestedFloor)){
            visitList.add(requestedFloor);
            this.requestedFloor = requestedFloor;
            System.out.println("DEBUG:Elevator: setRequestedFloor(): visitlist added: " + requestedFloor);
        }
    }
    
    public void updateVisitList(int floorToRemove){
        if (callLightPickup == false){
            System.out.println("DEBUG: arrived(): (callLightPickup == FALSE) BEGINING");
            // else update only the local visitList 
            visitList.remove(floorToRemove);
            checkElevatorAvailability();
        }
    }
    public void validateCloseDoors(IVisitor visitor){
        if (currentFloor != visitor.getVisitorAgenda().get(1)){
            elevatorCloseDoor();
        }     
    }
    
    @Override
    public void release(IVisitor visitor) {
        riders.remove(visitor);
        this.numberOfRiders = riders.size();
    }
    
    public Boolean checkElevatorAvailability(){
        if (visitList.size()==1) {      // if no visit list
            this.elevatorAvailable = true;
            System.out.println("DEBUG: arrived(): checkElevatorAvailability(): "+this.getLabel()+ " is elevatorAvailable! = " + elevatorAvailable.toString());
        }
        return elevatorAvailable;
    }
    
    public void elevatorOpenDoor() {
        this.doorsAreOpen = true;     // this elevator opens door
        System.out.println("DEBUG: ElevatorDoors are open!\n");
    }

    public void elevatorCloseDoor() {
        this.doorsAreOpen = false;     // this elevator opens door
        System.out.println("\nDEBUG: ElevatorDoors are CLOSED!\n");
    }

    public String getLabel() {
        return label;
    }

    public int getFloor() {
        return currentFloor;
    }

    public int getState() {
        return state;
    }

    public static void setMaxFloor(int maxFloor) {
        Elevator.maxFloor = maxFloor;
    }

    public void setElevatorRiders(int ElevatorRiders) {
        this.numberOfRiders = ElevatorRiders;
    }

    public int getElevatorRiders() {
        return this.numberOfRiders = riders.size();
    }
    
    @Override
    public int getNumberOfRiders() {
        return this.numberOfRiders = riders.size();
    }

    public Boolean getDoorsAreOpen() {
        return doorsAreOpen ;
    }

    public void setDoorsAreOpen(Boolean doorsAreOpen) {
        this.doorsAreOpen = doorsAreOpen;
    }

    public void setState(int state) {
        this.state = state;
    }

}//class Elevator

