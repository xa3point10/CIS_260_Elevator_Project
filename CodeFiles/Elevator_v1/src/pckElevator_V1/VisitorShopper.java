package pckElevator_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class VisitorShopper implements /*extends*/ IVisitor {

    Random rand = new Random();
    private static final int CALLING = 1;
    private static final int RIDING = 2;
    private static final int VISITING = 3;
    private static final int LOITERING = 4;         //6;
    private static final int FINALDESTINATION = 5;  // 7
    
    private static final int NEXTDESIREDFLOORidx = 1;
    private static final int CURRENTFLOORidx = 0;

    // internal floor index
    private int floorInt = 0; // in range 0, 1, 2, ..., maxFloor-1
    private int state = 0; // moving up, moving down, standing, waiting
    private String myFloorLabel;   // from ElevatorBank updateConfiguration()
    private int maxFloor;
    private int currentFloor;
    private String currentElevator = "";

    private int nextDesiredFloor;
    private boolean tempDoorCheck = false;
    private int numOfCallingVisitors = 0;

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
        //visitorAgenda.add(3);       //and then they want to visit here
        //visitorAgenda.add(1);
        //visitorAgenda.add(3);
        //visitorAgenda.add(4);
        //visitorAgenda.add(1);
        visitorAgenda.add(randFloorPicker(maxFloor)); //and visit here
        visitorAgenda.add(randFloorPicker(maxFloor));
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
        nextDesiredFloor = visitorAgenda.get(NEXTDESIREDFLOORidx);
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
                riding(elevators, floors);
                break;

            case VISITING:  // = 3
                visiting(/*floors*/); // change to LOITERING
                break;

            case LOITERING:  // = 6
                loitering(floors); // state change to calling, callLight = TRUE
                break;
            case FINALDESTINATION:  // = 6
                //loitering(floors); // state change to calling, callLight = TRUE
                System.out.println("DEBUG: visitor() switch(): STATE = FINALDESTINATION for Visitor: "+ this.toString() + "**\tHOME**\t**HOME\t**\t**\t");
                break;
        }
    }// beginAgendaProtocol();

    public void calling(ArrayList<Elevator> elevators, ArrayList<Floor> floors) {
        System.out.println("DEBUG: Visitor: calling()A***************  is calling on " + currentFloor+"& nowchecking each elevator,nextDesiredFloor= "+nextDesiredFloor+"NEXTDESIRED FLOOR INDEX ="+ NEXTDESIREDFLOORidx);
        // Attempt to board and leave floor
        for (Elevator elevator : elevators) {
            System.out.println("DEBUG: Visitor: calling()B********************************* Elevator "+elevator.getLabel()+" Doors are = " + elevator.getDoorsAreOpen());
            if ((elevator.getDoorsAreOpen() == true) && (elevator.getFloor() == currentFloor)&& elevator.checkElevatorAvailability()) {
                tempDoorCheck = true;   // update the temp door check
                elevator.accept(this);
                this.currentElevator = elevator.getLabel();
                System.out.println("DEBUG: Visitor calling()C: this visitor was accepted by elevator" + elevator.getLabel() + " + nextDesiredFloor =" + nextDesiredFloor);
                // give this elevator the next floor
                elevator.setRequestedFloor(nextDesiredFloor);
                elevator.setState(3);   // Elevator shouldbegin departing (3)
            }
        }
        for (Floor floor : floors) {
            if ((floor.getThisFloorsNumber() == currentFloor) && (tempDoorCheck == true)) {
                floor.setCallElevator(false);  //floor call light = off
                floor.release(this);
                System.out.println("DEBUG: Visitor: calling(): Floor has release and calllight is: " + floor.getBoolCallElevator().toString());
                this.state = RIDING;     // Visitor is now in riding state 
                
            }
        }
        tempDoorCheck = false;  // This visitor is done checking if door is open
        System.out.println("DEBUG: Visitor: calling()END********************************* EXIT with  State = " + this.state);
    }

    public void riding(ArrayList<Elevator> elevators, ArrayList<Floor> floors) {
        
        System.out.println("DEBUG: Visitor Riding()Beg: visitor waiting to arrive on DesiredFloor: " +nextDesiredFloor +" & currentFloor is "+ currentFloor);
        Boolean confirmRelease = false;
        for (Elevator elevator : elevators) {
            if ((elevator.getDoorsAreOpen() == true) && (elevator.getFloor() == nextDesiredFloor)) {
                System.out.println("***DEBUG: Visitor: Riding(): Visitor is arriving! ELEVATOR doors are" + elevator.getDoorsAreOpen());
                currentFloor = elevator.getFloor(); // tell visitor where they are
                elevator.release(this);
                System.out.println("* *DEBUG: Riding():this.visitor" + this.getClass().toString() + " was RELEASED onto floor =" + this.nextDesiredFloor+ ",= VALIDATE currentFloor = "+currentFloor);
                confirmRelease = true;                
            }
        }
        for (Floor floor : floors) {
            //if ((floor.getThisFloorsNumber() == this.nextDesiredFloor)&& ((confirmRelease)){   
            if ((floor.getThisFloorsNumber() == currentFloor)&& (floor.getThisFloorsNumber()== nextDesiredFloor)&&(confirmRelease)){           //(confirmRelease)) {        
                floor.accept(this);
                currentFloor = floor.getThisFloorsNumber();
                System.out.println(" **DEBUG: Riding(): this.visitor" + this.getClass().toString() + " was ACCEPTED onto floor = " + floor.getThisFloorsNumber());
                state = VISITING; // update STATE to Visiting
                updateAgenda();   // check Override STATE to FINALDESTINATION 
            }
        }
        
        confirmRelease = false; // reset for next pass
        
        System.out.println("DEBUG: Visitor Riding()End: this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);

    }// riding()
    
    public void loitering(ArrayList<Floor> floors){
        System.out.println("DEBUG: Visitor loitering(): this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);
            for (Floor floor : floors) {
            if (floor.getThisFloorsNumber() == currentFloor) {
                floor.setCallElevator(Boolean.TRUE);
            }
        }
        state = CALLING;
    }// loitering()
    
    public void visiting(/*ArrayList<Floor> floors*/) {
        System.out.println("DEBUG: Visitor visiting(): this.visitor" + this.getClass().toString() + " + currentFloor =" + currentFloor);
        state = LOITERING;  // visit this floor one time 
    }// visiting()

    public void updateAgenda(){
        if (visitorAgenda.size() > 2) { //if more than 2 items in agenda
            System.out.println("DEBUG: visitor() updateAgenda():  nextDesiredFloor = " + nextDesiredFloor);
            this.visitorAgenda.remove(CURRENTFLOORidx/*0*/);    // remove the first index in agenda
            this.nextDesiredFloor = this.visitorAgenda.get(NEXTDESIREDFLOORidx); //index = 1
            System.out.println("DEBUG: visitor() updateAgenda():  nextDesiredFloor = " + nextDesiredFloor);
        }        
        // if this is the last floor in the visitorAgenda then dont remove
        else {
            state = FINALDESTINATION;
            currentFloor = nextDesiredFloor;
            System.out.println("DEBUG: visitor() switch(): STATE = Arriving; VisitorAgenda updated = "+visitorAgenda.size()+" currentfloor = " + currentFloor +" nextDesiredFloor = " + nextDesiredFloor);
        }
    }// updateAgenda
    
    
    // randomize the order of floor visit and add it to OrderOfVisits Array
    public int randFloorPicker(int maxFloor) {
        int floorFloorToVisit = rand.nextInt(maxFloor);
        return floorFloorToVisit;
    }// randFloorPicker



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
