package pckElevator_V1;

import java.util.ArrayList;

public class ElevatorBank {

    // singleton design support
    //------------------------------
    private static Mutex mMutex = new Mutex();
    private static ElevatorBank mInstance = null;

    // data
    private ArrayList< Integer> floorDispatchList;
    private ArrayList< Elevator> elevators;
    private ArrayList< Floor> floors;
    private int maxFloor;
    
    // ------------------------------
    // constructors
    // ------------------------------
    public ElevatorBank() {
        this.floorDispatchList = new ArrayList<>();
        this.floorDispatchList.add(-1);
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
    }//ElevatorBank()
    
    // ------------------------------
    // operations
    //------------------------------
    public void animate(){
        ArrayList<Elevator> elevators = ElevatorBank.GetInstance().getElevators();
        for (Elevator elevator : elevators) {
            System.out.println(">>Controller: Floor call list Size!= "+ floorDispatchList.size() );
            elevator.elevatorWakeUp();      
        }
    }// animate()
    
    public void updateConfiguration(int numberOfFloors, int numberOfElevators ) {
        this.maxFloor = numberOfFloors;
        elevators.clear();  // use Arraylist Clear() method
        floors.clear();     // use Arraylist Clear() method 
        // populate the floors
        for (int idx = 0; idx < numberOfFloors; ++idx) {
            Floor floor = null;
            if (idx == 0) {
                floor = new Floor("P");
                floor.setThisFloorsNumber(idx);
            } else if (idx == 1) {
                floor = new Floor("L");
                floor.setThisFloorsNumber(idx);
            } else {
                floor = new Floor(Integer.toString(idx));
                floor.setThisFloorsNumber(idx);
            }
            addFloor(floor);
        }
        // populate the elevators and create the lavels
        for (int idx = 0; idx < numberOfElevators; ++idx) {
            Elevator elevator = null; // construct an Elevator Object
            int elevTableLabel = 1;
            elevTableLabel += idx;
            //Fill that Elevator object
            elevator = new Elevator(
                    "E"+elevTableLabel,       // Label for each Elevator
                    0,                        // starting position before move
                    elevTableLabel  // alt label number  
            );
            addElevator(elevator);
        }
    }//updateConfiguration
       
    // ************ ELEVATORS ************
    public Elevator getElevator(int elevatorSeqNumber) {
        return elevators.get(elevatorSeqNumber);
    }//getElevator

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }//getElevators
    
    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }//addElevator

    // ************ Floors ************
    public int getMaxFloor() {
        return maxFloor;
    }

    public Floor getFloor(int floorSeqNumber) {
        return floors.get( floorSeqNumber );
    } //getFloor

    public ArrayList<Floor> getFloors() {
        return floors;
    }//getFloors
    
    public void addFloor(Floor floor) {
        floors.add(floor);
    }//addFloor
    
    // INSTANCE!
     public static ElevatorBank GetInstance() { // Access through the mutex Lock
        Lock lock = new Lock(mMutex); // automatically locks

        if (mInstance == null) {
            mInstance = new ElevatorBank();
        }
        lock.release(); // don't forget to unlock

        return mInstance;
    }//GetInstance

    public ArrayList<Integer> getFloorDispatchList() {
        return this.floorDispatchList;
    }

    public void setFloorDispatchList(ArrayList<Integer> floorCalls) {
        this.floorDispatchList = floorCalls;
    }
    
}//class ElevatorBank
