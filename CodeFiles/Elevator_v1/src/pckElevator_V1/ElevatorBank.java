package pckElevator_V1;

import java.util.ArrayList;

public class ElevatorBank {

    // singleton design support
    //------------------------------
    private static Mutex mMutex = new Mutex();
    private static ElevatorBank mInstance = null;

    // data
    //------------------------------
    //private IScenario scenario;
    // getscenario
    // setScenario  
    private ArrayList< Elevator> elevators;
    private ArrayList< Floor> floors;

    // constructors
    //------------------------------
    public ElevatorBank() {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
    }//ElevatorBank()

    //------------------------------
    // operations
    //------------------------------
    // Only allow Access through the mutex Lock
    public static ElevatorBank GetInstance() { // Access through the mutex Lock
        Lock lock = new Lock(mMutex); // automatically locks

        if (mInstance == null) {
            mInstance = new ElevatorBank();
        }
        lock.release(); // don't forget to unlock

        return mInstance;
    }//GetInstance

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }//addElevator

    public void updateConfiguration(int numberOfFloors, int numberOfElevators, int numberOfVisitors) {
        elevators.clear();  // use Arraylist Clear() method
        floors.clear();     // use Arraylist Clear() method
        
        // populate the floors
        for (int idx = 0; idx < numberOfFloors; ++idx) {
            Floor floor = null;
            if (idx == 0) {
                floor = new Floor("P");
            } else if (idx == 1) {
                floor = new Floor("L");
            } else {
                floor = new Floor(Integer.toString(idx));
            }
            addFloor(floor);
        }
        // populate the elevators
        for (int idx = 0; idx < numberOfElevators; ++idx) {
            Elevator elevator = null; // construct an Elevator Object
            char elevatorLabel = 'A';
            elevatorLabel += (char) idx;
            //Fill that Elevator object
            elevator = new Elevator(Character.toString(elevatorLabel), idx);
            addElevator(elevator);
        }
    }//updateConfiguration

    public Elevator getElevator(int elevatorSeqNumber) {
        return elevators.get(elevatorSeqNumber);
    }//getElevator

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }//getElevators

    public void addFloor(Floor floor) {
        floors.add(floor);
    }//addFloor

    public Floor getFloor(int floorSeqNumber) {
        return floors.get(floorSeqNumber);
    }//getFloor

    public ArrayList<Floor> getFloors() {
        return floors;
    }//getFloors

}//class ElevatorBank
