package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class VisitorShopper implements /*extends*/ IVisitor {

    Random rand = new Random();
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    private static final int WAITING = 4;
    private static final int ARRIVING = 5;
    private static final int LOITERING = 6;
    private static final int NEXTDESIREDFLOORidx = 1;
    private static final int CURRENTFLOORidx = 0;

    // internal floor index
    private int floorInt = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing, waiting
    private String myFloorLabel;   // from ElevatorBank updateConfiguration()
    private int maxFloor;
    private int currentFloor;
    //private int myFloorIndex = 0;             // candidate for delete
    //private int desiredFloorIndex = 0;        // candidate for delete
    private int nextDesiredFloor;
    private boolean tempDoorCheck = false;

    private ArrayList<Integer> visitorAgenda;
    private ArrayList<IElement> bldElements;    // candidate for delete
    private ArrayList<Elevator> elevators;
    private ArrayList<Floor> floors;

    // Constructor
    public VisitorShopper() {
        this.visitorAgenda = new ArrayList<>();
        this.bldElements = new ArrayList<>();
        elevators = ElevatorBank.GetInstance().getElevators();
        floors = ElevatorBank.GetInstance().getFloors();
    }

    @Override
    public void configVisitorRoutine() {
        // When This is createed, 
        // They know how many floors are in the building
        System.out.println("DEBUG: VsitorShoper MaxFloor check = " + maxFloor);
        // They know the order they want to visit the floors
        visitorAgenda.add(1);       // They begin on this floor #
        visitorAgenda.add(3);       //and then they want to visit here
        visitorAgenda.add(1);
        //visitorAgenda.add(3);
        //visitorAgenda.add(randFloorPicker(maxFloor)); //and visit here
        //visitorAgenda.add(randFloorPicker(maxFloor));
        visitorAgenda.add(0);       // End back in the garage
        // begin in visiting state 
        state = VISITING;
        // visitor gets passed to the first floor in the agenda
        currentFloor = visitorAgenda.get(CURRENTFLOORidx);  // begin in index 0
        System.out.println("DEBUG: thisVisitors\t\t CurrentFloor = " + currentFloor);
        //this.desiredFloorIndex++; // increment the index
        this.nextDesiredFloor = visitorAgenda.get(NEXTDESIREDFLOORidx);
        // begin this visitors life on the first currentFloor
        ElevatorBank.GetInstance().getFloor(currentFloor).accept(this);
        state = VISITING;
        //System.out.println("DEBUG: Visitor: configVisRoutine Fully configured on current Floor: " + currentFloor);
        beginAgendaProtocol();
    } // configVisitorRoutine()        

    @Override
    public void wakeUpVisitor() {
        beginAgendaProtocol();
    }

    public void beginAgendaProtocol() {
        System.out.println("\nVISITOR: ***NEW CYCLE Visitor ***\nbeginAgendaProtocol(): STATE = ["+state+"],PRECHeck currentFloor = " +currentFloor);
        elevators = ElevatorBank.GetInstance().getElevators();
        floors = ElevatorBank.GetInstance().getFloors();
        switch (state) {
            case CALLING:   // = 1
                calling(elevators, floors);
                break;

            case RIDING:    // = 2
                riding(elevators);
                break;

            case VISITING:  // = 3
                visiting(/*floors*/);
                break;

            case WAITING:    // = 4;
                for (Elevator elevator : elevators) {
                    if ((elevator.getState() == 2/*ARRIVED*/) && (elevator.getFloor() == nextDesiredFloor)) {
                        this.state = ARRIVING;
                    } else { this.state = CALLING; }  // lives one cycle in the waiting state
                }
                break;

            case ARRIVING:    // = 5; droppoff from elevator
                System.out.println("DEBUG: visitor() switch(): STATE = Arriving  BEGINGING"); 
                arriving(elevators, floors); // transfer from elevator to floor
                // update this visitors agenda! as long as its not last floot    
                if (visitorAgenda.size() > 2) { //if more than 1 item in agenda
                    System.out.println("DEBUG: visitor() switch(): STATE = Arriving, visitorAgenda about to get modified " + visitorAgenda.size() +" nextDesiredFloor = " + nextDesiredFloor);
                    this.visitorAgenda.remove(CURRENTFLOORidx/*0*/);    // remove the first index in agenda
                    this.nextDesiredFloor = this.visitorAgenda.get(NEXTDESIREDFLOORidx); //index = 1
                    System.out.println("DEBUG: visitor() switch(): STATE = Arriving, visitorAgenda now was modified" + visitorAgenda.size()+" nextDesiredFloor = " + nextDesiredFloor);
                }
                
                // if this is the last floor in the visitorAgenda then dont remove
                else {
                    nextDesiredFloor = visitorAgenda.get(0); // else your already there 0
                    currentFloor = nextDesiredFloor;
                    System.out.println("DEBUG: visitor() switch(): STATE = Arriving; VisitorAgenda updated = "+visitorAgenda.size()+" currentfloor = " + currentFloor +" nextDesiredFloor = " + nextDesiredFloor);
                }
                state = VISITING;
                //arriving(elevators, floors);
                System.out.println("DEBUG: visitor() switch(): STATE = Arriving  end of call on " + currentFloor);
                break;
            case LOITERING:  // = 6
                loitering(floors); // state change to calling, callLight = TRUE
                break;
        }
    }// beginAgendaProtocol();

    public void calling(ArrayList<Elevator> elevators, ArrayList<Floor> floors) {
        System.out.println("DEBUG: Visitor: calling()A***************  is calling on " + currentFloor+"& nowchecking each elevator,nextDesiredFloor= "+nextDesiredFloor+"NEXTDESIRED FLOOR INDEX ="+ NEXTDESIREDFLOORidx);
        for (Elevator elevator : elevators) {
            System.out.println("DEBUG: Visitor: calling()B********************************* Elevator Doors are = " + elevator.getDoorsAreOpen());
            if ((elevator.getDoorsAreOpen() == true) && (elevator.getFloor() == currentFloor)) {
                tempDoorCheck = true;   // update the temp door check
                elevator.accept(this);
                System.out.println("DEBUG: Visitor calling()C: this visitor was accepted by elevator" + elevator.getLabel() + " + nextDesiredFloor =" + nextDesiredFloor);
                elevator.setRequestedFloor(nextDesiredFloor);// tell elevator nextdesiredFloor
                // give this elevator the next floor
                elevator.setRequestedFloor(nextDesiredFloor);
                elevator.setState(3);   // set Elevator state = DEPARTING(3)
            }
        }
        for (Floor floor : floors) {
            if ((floor.getThisFloorsNumber() == currentFloor) && (tempDoorCheck == true)) {
                floor.release(this);
                System.out.println("DEBUG: Visitor: calling()D:" + floor.getBoolCallElevator().toString());
                //floor.setCallElevator(true);
                 this.state = RIDING;     // update the state 
                // Give the Destination Floor = nextDesiredFloor to turn on it's call light
                //ElevatorBank.GetInstance().getFloor(nextDesiredFloor-1).setCallElevator(Boolean.TRUE);
            }
        }
        tempDoorCheck = false;  // This visitor is done checking DoorCheck
        System.out.println("DEBUG: Visitor: calling()END********************************* EXIT with  State = " + this.state);
    }

    public void riding(ArrayList<Elevator> elevators) {
        System.out.println("DEBUG: Visitor riding()Beg: this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);

        for (Elevator elevator : elevators) {
            if ((elevator.getDoorsAreOpen() == true) && (elevator.getFloor() == currentFloor)) {
                System.out.println("DEBUG: this.visitor" + this.getClass().toString() + " is riding inside ELEVATOR: " + elevator.toString() + " + nextDesiredFloor =" + this.nextDesiredFloor);

                elevator.setRequestedFloor(nextDesiredFloor);// give next floor
                //elevator.setDoorsAreOpen(Boolean.FALSE);     // should close door on its own
                elevator.setState(3); // DEPARTING
            }
            this.state = WAITING;
        }
        System.out.println("DEBUG: Visitor riding()Bend: this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);

    }// riding()
    public void loitering(ArrayList<Floor> floors){
        System.out.println("DEBUG: Visitor loitering(): this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);
            for (Floor floor : floors) {
            if (floor.getThisFloorsNumber() == currentFloor) {
                floor.setCallElevator(Boolean.TRUE);
            }
        }
        state = CALLING;
    }
    
    public void visiting(/*ArrayList<Floor> floors*/) {
        System.out.println("DEBUG: Visitor visiting(): this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);
        state = LOITERING;  // visit this floor one time 
        // then push the call button
//        for (Floor floor : floors) {
//            if (floor.getThisFloorsNumber() == currentFloor) {
//                floor.setCallElevator(Boolean.TRUE);
//            }
//        }
    }// visiting()

    public void arriving(ArrayList<Elevator> elevators, ArrayList<Floor> floors) {
        System.out.println("DEBUG: Visitor Arriving(): this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);
        
        for (Elevator elevator : elevators) {
            System.out.println("***DEBUG: Visitor: Arriving()" + this.getClass().toString() + " is almost arriving! ELEVATOR doors are" + elevator.getDoorsAreOpen());
            if ((elevator.getDoorsAreOpen() == true) && (elevator.getFloor() == visitorAgenda.get(1))) {
                System.out.println("* *DEBUG: Arriving(): this.visitor" + this.getClass().toString() + " is arriving! by ELEVATOR: " + elevator.toString() + " + nextDesiredFloor =" + this.nextDesiredFloor);
                ///this.currentFloor = elevator.getFloor();
                elevator.release(this);
                System.out.println("* *DEBUG: Arriving():this.visitor" + this.getClass().toString() + " was RELEASED onto floor =" + this.nextDesiredFloor);
                // if this is the last visitor, close the doors
                if (elevator.getNumberOfVisitors() ==0) {
                    // if this is the last rider; close doors
                    elevator.elevatorCloseDoor();
                }
            }
        }
        for (Floor floor : floors) {
            if (floor.getThisFloorsNumber() == this.nextDesiredFloor) {
                floor.accept(this);
                this.currentFloor = floor.getThisFloorsNumber();
                System.out.println(" **DEBUG: Arriving(): this.visitor" + this.getClass().toString() + " was ACCEPTED onto floor = " + floor.getThisFloorsNumber());
            }
        }
       System.out.println("DEBUG: Arriving():  currentFloor = " + currentFloor +" + END of CALL");
    }

    // randomize the order of floor visit and add it to OrderOfVisits Array
    public int randFloorPicker(int maxFloor) {
        int floorFloorToVisit = rand.nextInt(maxFloor);
        return floorFloorToVisit;
    }

    // Operations
    @Override
    public void unboard(Floor floor) {          // arrived at floor
        // check each elevator for this visitor
//        for (int idx = 0; idx <= ElevatorBank.GetInstance().getElevators().size(); ++idx){
//            if (ElevatorBank.GetInstance().getElevator(idx).);
//            
//        }
    }// unboard()

    @Override
    public void boardElevator(Elevator elevator, Floor floor) {          // arrived at floor
        // this is called if the elvator has arrived to the floor
        // then this is used to swap from flooor to elevator
        ElevatorBank.GetInstance().getFloors().get(0).release(this);
    }

    @Override
    public void setState(int state) {
        this.state = state;
        System.out.println("DEBUG: \tState changed!\t = " + this.state);
    }

    public int getState() {
        return this.state;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public String getMyFloorLabel() {
        return myFloorLabel;
    }

    public void setMyFloorLabel(String myFloorLabel) {
        this.myFloorLabel = myFloorLabel;
    }

    @Override
    public int getNextDesiredFloorIndex() {
        return NEXTDESIREDFLOORidx;
    }

    @Override
    public ArrayList<Integer> getVisitorAgenda() {
        return visitorAgenda;
    }

    public void setBldElements(ArrayList<IElement> bldElements) {
        this.bldElements = bldElements;
    }

    @Override
    public void setFloorInt(int floorNumber) {
        this.floorInt = floorNumber;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

}// class VisitorShopper
